package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.*;
import iesbelen.iterpolaris.dto.TaskRequest;
import iesbelen.iterpolaris.dto.TaskResponse;
import iesbelen.iterpolaris.repository.LogEntryRepository;
import iesbelen.iterpolaris.repository.ProjectRepository;
import iesbelen.iterpolaris.repository.TaskRepository;
import iesbelen.iterpolaris.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final LogEntryRepository logEntryRepository;
    private final ZoneRepository zoneRepository;
    private final LevelService levelService;

    public TaskService(TaskRepository taskRepository,
                       ProjectRepository projectRepository,
                       LogEntryRepository logEntryRepository,
                       ZoneRepository zoneRepository,
                       LevelService levelService) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.logEntryRepository = logEntryRepository;
        this.zoneRepository = zoneRepository;
        this.levelService = levelService;
    }

    public TaskResponse createTask(User user, TaskRequest request) {
        Project project = null;
        if (request.getProjectId() != null) {
            project = projectRepository.findByIdAndDeletedFalse(request.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
            if (!project.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("No tienes acceso a este proyecto");
            }
        }

        Task parentTask = null;
        if (request.getParentTaskId() != null) {
            parentTask = taskRepository.findByIdAndDeletedFalse(request.getParentTaskId())
                    .orElseThrow(() -> new RuntimeException("Tarea padre no encontrada"));
            if (!parentTask.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("No tienes acceso a la tarea padre");
            }
        }

        Task task = Task.builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .status(request.getStatus())
                .energy(request.getEnergy())
                .points(request.getPoints())
                .priority(request.getPriority())
                .cycle(request.getCycle())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .active(request.getActive())
                .challengeLevel(request.getChallengeLevel()) // Se usa el nivel de desafío en lugar de XP
                .user(user)
                .project(project)
                .parentTask(parentTask)
                .deleted(false)
                .build();

        Task saved = taskRepository.save(task);
        return mapToResponse(saved);
    }

    public List<TaskResponse> getAllTasks(User user) {
        return taskRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public TaskResponse getTaskById(User user, Long taskId) {
        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta tarea");
        }
        return mapToResponse(task);
    }

    public TaskResponse updateTask(User user, Long taskId, TaskRequest request) {
        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta tarea");
        }

        task.setName(request.getName());
        task.setDescription(request.getDescription());
        task.setImage(request.getImage());
        task.setStatus(request.getStatus());
        task.setEnergy(request.getEnergy());
        task.setPoints(request.getPoints());
        task.setPriority(request.getPriority());
        task.setCycle(request.getCycle());
        task.setStartDate(request.getStartDate());
        task.setEndDate(request.getEndDate());
        task.setActive(request.getActive());
        task.setChallengeLevel(request.getChallengeLevel());

        if (request.getProjectId() != null) {
            Project project = projectRepository.findByIdAndDeletedFalse(request.getProjectId())
                    .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
            if (!project.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("No tienes acceso a este proyecto");
            }
            task.setProject(project);
        } else {
            task.setProject(null);
        }

        if (request.getParentTaskId() != null) {
            Task parentTask = taskRepository.findByIdAndDeletedFalse(request.getParentTaskId())
                    .orElseThrow(() -> new RuntimeException("Tarea padre no encontrada"));
            if (!parentTask.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("No tienes acceso a la tarea padre");
            }
            task.setParentTask(parentTask);
        } else {
            task.setParentTask(null);
        }

        Task updated = taskRepository.save(task);
        return mapToResponse(updated);
    }

    public void deleteTask(User user, Long taskId) {
        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta tarea");
        }
        task.setDeleted(true);
        taskRepository.save(task);
    }

    public void completeTask(User user, Long taskId) {
        Task task = taskRepository.findByIdAndDeletedFalse(taskId)
                .orElseThrow(() -> new RuntimeException("Tarea no encontrada"));
        if (!task.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta tarea");
        }

        // Marcar la tarea como completada
        task.setStatus("COMPLETED");
        taskRepository.save(task);

        // Calcular XP basado en ChallengeLevel
        int xpGained = task.getChallengeLevel().getXpValue();
        levelService.addXPToUser(user, xpGained);

        // Registrar la actividad en LogEntry
        LogEntry log = LogEntry.builder()
                .energy(task.getEnergy())
                .challengeLevel(task.getChallengeLevel()) // Se usa ChallengeLevel
                .type("TASK")
                .itemId(task.getId())
                .endTimestamp(LocalDate.now())
                .user(user)
                .zone(task.getProject() != null ? task.getProject().getZone() : getDefaultZone(user))
                .deleted(false)
                .build();
        logEntryRepository.save(log);
    }

    private Zone getDefaultZone(User user) {
        return zoneRepository.findByUserAndDeletedFalse(user)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No hay zona predeterminada para este usuario"));
    }

    private TaskResponse mapToResponse(Task task) {
        return TaskResponse.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .image(task.getImage())
                .status(task.getStatus())
                .energy(task.getEnergy())
                .points(task.getPoints())
                .priority(task.getPriority())
                .cycle(task.getCycle())
                .startDate(task.getStartDate())
                .endDate(task.getEndDate())
                .active(task.getActive())
                .challengeLevel(task.getChallengeLevel()) // Agregado en el response
                .userId(task.getUser().getId())
                .projectId(task.getProject() != null ? task.getProject().getId() : null)
                .parentTaskId(task.getParentTask() != null ? task.getParentTask().getId() : null)
                .subTasksIds(task.getSubTasks().stream().map(Task::getId).collect(Collectors.toSet()))
                .build();
    }
}
