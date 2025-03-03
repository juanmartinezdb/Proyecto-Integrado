package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.*;
import iesbelen.iterpolaris.dto.ZoneRequest;
import iesbelen.iterpolaris.dto.ZoneResponse;
import iesbelen.iterpolaris.repository.LogEntryRepository;
import iesbelen.iterpolaris.repository.ZoneRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final ProjectService projectService; // si quieres llamar a su deleteProject
    private final HabitService habitService;     // ...
    private final LogEntryRepository logEntryRepository;
    private final UserService userService;

    public ZoneService(ZoneRepository zoneRepository,
                       ProjectService projectService,
                       HabitService habitService,
                       LogEntryRepository logEntryRepository, UserService userService) {
        this.zoneRepository = zoneRepository;
        this.projectService = projectService;
        this.habitService = habitService;
        this.logEntryRepository = logEntryRepository;
        this.userService = userService;
    }

    // Crear zona
    public ZoneResponse createZone(User user, ZoneRequest request) {
        Zone zone = Zone.builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .color(request.getColor())
                .energy(0)
                .xp(0)
                .level(1)
                .zoneTypes(request.getZoneTypes())
                .deleted(false)
                .user(user)
                .build();

        Zone saved = zoneRepository.save(zone);
        return mapToResponse(saved);
    }

    // Listar todas las zonas del usuario
    public List<ZoneResponse> getAllZones(User user) {
        return zoneRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Obtener una zona específica
    public ZoneResponse getZoneById(User user, Long zoneId) {
        Zone zone = zoneRepository.findByIdAndDeletedFalse(zoneId)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada"));

        if (!zone.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta zona");
        }

        return mapToResponse(zone);
    }

    // Actualizar zona
    public ZoneResponse updateZone(User user, Long zoneId, ZoneRequest request) {
        Zone zone = zoneRepository.findByIdAndDeletedFalse(zoneId)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada"));

        if (!zone.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta zona");
        }

        zone.setName(request.getName());
        zone.setDescription(request.getDescription());
        zone.setImage(request.getImage());
        zone.setColor(request.getColor());
        zone.setZoneTypes(request.getZoneTypes());

        Zone updated = zoneRepository.save(zone);
        return mapToResponse(updated);
    }

    // Borrado lógico de la zona
    public void deleteZone(User user, Long zoneId) {
        Zone zone = zoneRepository.findByIdAndDeletedFalse(zoneId)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
        if (!zone.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta zona");
        }

        // (A) Marcar LogEntry (relacionados con la zona)
        for (LogEntry le : zone.getLogEntries()) {
            le.setDeleted(true);
        }

        // (B) Marcar Projects (o llamar projectService.deleteProject)
        for (Project p : zone.getProjects()) {
            p.setDeleted(true);
            // Podrías invocar projectService.deleteProject(user, p.getId())
            // si quieres cascada profunda (tasks, etc.)
        }

        // (C) Marcar Habits
        for (Habit h : zone.getHabits()) {
            h.setDeleted(true);
            // habitService.deleteHabit(user, h.getId()) si quieres
        }

        // (D) Finalmente, marcar la zone
        zone.setDeleted(true);
        zoneRepository.save(zone);
    }

    // Método para obtener la entidad sin exponer un response
    public Zone getZoneEntity(UserDetails userDetails, Long zoneId) {
        User user = userService.getUserByUsername(userDetails.getUsername()); // Obtener usuario autenticado
        Zone zone = zoneRepository.findByIdAndDeletedFalse(zoneId)
                .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
        if (!zone.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta zona");
        }
        return zone;
    }


    // Conversión Entity -> DTO
    private ZoneResponse mapToResponse(Zone zone) {
        ZoneResponse response = new ZoneResponse();
        response.setId(zone.getId());
        response.setName(zone.getName());
        response.setDescription(zone.getDescription());
        response.setImage(zone.getImage());
        response.setColor(zone.getColor());
        response.setEnergy(zone.getEnergy());
        response.setXp(zone.getXp());
        response.setLevel(zone.getLevel());
        response.setZoneTypes(zone.getZoneTypes());
        return response;
    }
}
