package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.*;
import iesbelen.iterpolaris.dto.ProjectRequest;
import iesbelen.iterpolaris.dto.ProjectResponse;
import iesbelen.iterpolaris.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ZoneRepository zoneRepository;
    private final NotificationService notificationService;
    private final AchievementService achievementService;
    private final JournalRepository journalRepository;
    private final LevelService levelService;

    public ProjectService(ProjectRepository projectRepository,
                          ZoneRepository zoneRepository,
                          JournalRepository journalRepository,
                          NotificationService notificationService,
                          AchievementService achievementService,
                          LevelService levelService) {
        this.projectRepository = projectRepository;
        this.zoneRepository = zoneRepository;
        this.journalRepository = journalRepository;
        this.notificationService = notificationService;
        this.achievementService = achievementService;
        this.levelService = levelService;
    }

    // ====================== CREATE ======================
    public ProjectResponse createProject(User user, ProjectRequest request) {
        Zone zone = null;
        if (request.getZoneId() != null) {
            zone = zoneRepository.findByIdAndDeletedFalse(request.getZoneId())
                    .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
            if (!zone.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("No tienes acceso a la zona especificada");
            }
        }

        Journal journal = Journal.builder()
                .name("Diario de " + request.getName())
                .createdAt(LocalDateTime.now())
                .description("Entradas para el proyecto " + request.getName())
                .user(user)
                .build();
        journalRepository.save(journal);

        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .createdAt(LocalDateTime.now())
                .points(request.getPoints())
                .image(request.getImage())
                .icon(request.getIcon())
                .color(request.getColor())
                .status(request.getStatus())
                .priority(request.getPriority())
                .zone(zone)
                .user(user)
                .journal(journal)
                .challengeLevel(request.getChallengeLevel()) // Se usa ChallengeLevel en lugar de XP
                .deleted(false)
                .build();

        Project savedProject = projectRepository.save(project);

        notificationService.createReminder(user, "¡Nuevo proyecto creado: " + project.getName() + "!");

        return mapToResponse(savedProject);
    }

    // ====================== GET ALL ======================
    public List<ProjectResponse> getAllProjects(User user) {
        return projectRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ====================== GET BY ID ======================
    public ProjectResponse getProjectById(User user, Long projectId) {
        Project project = projectRepository.findByIdAndDeletedFalse(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este proyecto");
        }

        return mapToResponse(project);
    }

    // ====================== UPDATE ======================
    public ProjectResponse updateProject(User user, Long projectId, ProjectRequest request) {
        Project project = projectRepository.findByIdAndDeletedFalse(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este proyecto");
        }

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setImage(request.getImage());
        project.setIcon(request.getIcon());
        project.setColor(request.getColor());
        project.setStatus(request.getStatus());
        project.setPriority(request.getPriority());
        project.setChallengeLevel(request.getChallengeLevel());

        if (request.getZoneId() != null) {
            Zone zone = zoneRepository.findByIdAndDeletedFalse(request.getZoneId())
                    .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
            if (!zone.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("No tienes acceso a la zona especificada");
            }
            project.setZone(zone);
        } else {
            project.setZone(null);
        }

        Project updatedProject = projectRepository.save(project);
        return mapToResponse(updatedProject);
    }

    // ====================== DELETE (Borrado Lógico) ======================
    public void deleteProject(User user, Long projectId) {
        Project project = projectRepository.findByIdAndDeletedFalse(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este proyecto");
        }

        project.setDeleted(true);
        projectRepository.save(project);
    }

    // ====================== COMPLETAR PROYECTO ======================
    public void completeProject(User user, Long projectId) {
        Project project = projectRepository.findByIdAndDeletedFalse(projectId)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        if (!project.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este proyecto");
        }

        project.setStatus("COMPLETED");
        projectRepository.save(project);

        // Asignar XP al usuario
        int xpGained = project.getChallengeLevel().getXpValue();
        levelService.addXPToUser(user, xpGained);

        // 🟢 Enviar notificación de proyecto completado
        notificationService.createReminder(user, "¡Has completado el proyecto: " + project.getName() + "!");

        // 🟢 Revisar si se ha desbloqueado algún logro de proyectos
        long completedProjects = projectRepository.countByUserAndStatus(user, "COMPLETED");
        achievementService.checkAndUnlockAchievement(user, "projects_completed", (int) completedProjects);

    }

    // ====================== Map Entity -> DTO ======================
    private ProjectResponse mapToResponse(Project project) {
        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createdAt(project.getCreatedAt())
                .points(project.getPoints())
                .image(project.getImage())
                .icon(project.getIcon())
                .color(project.getColor())
                .status(project.getStatus())
                .priority(project.getPriority())
                .challengeLevel(project.getChallengeLevel()) // Agregado en la respuesta
                .zoneId(project.getZone() != null ? project.getZone().getId() : null)
                .userId(project.getUser().getId())
                .build();
    }
}
