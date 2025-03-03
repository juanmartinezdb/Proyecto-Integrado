package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.LogEntry;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.domain.Zone;
import iesbelen.iterpolaris.dto.LogEntryRequest;
import iesbelen.iterpolaris.dto.LogEntryResponse;
import iesbelen.iterpolaris.repository.LogEntryRepository;
import iesbelen.iterpolaris.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogEntryService {

    private final LogEntryRepository logEntryRepository;
    private final ZoneRepository zoneRepository;

    public LogEntryService(LogEntryRepository logEntryRepository,
                           ZoneRepository zoneRepository) {
        this.logEntryRepository = logEntryRepository;
        this.zoneRepository = zoneRepository;
    }

    // Crear registro de actividad
    public LogEntryResponse createLogEntry(User user, LogEntryRequest request) {
        Zone zone = zoneRepository.findByIdAndDeletedFalse(request.getZoneId())
                .orElseThrow(() -> new RuntimeException("Zona no encontrada"));

        if (!zone.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta zona");
        }

        LogEntry entry = LogEntry.builder()
                .points(request.getPoints())
                .type(request.getType())
                .itemId(request.getItemId())
                .endTimestamp(request.getEndTimestamp())
                .energy(request.getEnergy())
                .user(user)
                .zone(zone)
                .deleted(false)
                .build();

        LogEntry saved = logEntryRepository.save(entry);
        return mapToResponse(saved);
    }

    // Listar todos los registros de un usuario
    public List<LogEntryResponse> getAllLogEntries(User user) {
        return logEntryRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // Podríamos agregar métodos para filtrar por fecha, tipo, etc.

    // Borrado lógico
    public void deleteLogEntry(User user, Long logId) {
        LogEntry entry = logEntryRepository.findById(logId)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));
        if (!entry.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este registro");
        }
        entry.setDeleted(true);
        logEntryRepository.save(entry);
    }

    private LogEntryResponse mapToResponse(LogEntry entry) {
        return LogEntryResponse.builder()
                .id(entry.getId())
                .points(entry.getPoints())
                .type(entry.getType())
                .itemId(entry.getItemId())
                .endTimestamp(entry.getEndTimestamp())
                .energy(entry.getEnergy())
                .userId(entry.getUser().getId())
                .zoneId(entry.getZone().getId())
                .build();
    }
}
