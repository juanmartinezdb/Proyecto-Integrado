package iesbelen.iterpolaris.service;


import iesbelen.iterpolaris.domain.LogEntry;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.domain.Zone;
import iesbelen.iterpolaris.repository.LogEntryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatsService {

    private final LogEntryRepository logEntryRepository;

    public StatsService(LogEntryRepository logEntryRepository) {
        this.logEntryRepository = logEntryRepository;
    }

    // Calcula energía del usuario en un rango (p.ej. semana)
    public int calculateUserEnergy(User user, LocalDate fromDate) {
        List<LogEntry> entries = logEntryRepository.findByUserAndDeletedFalseAndEndTimestampGreaterThanEqual(user, fromDate);
        return entries.stream().mapToInt(LogEntry::getEnergy).sum();
    }

    // Calcula XP total del usuario en un rango
    public int calculateUserXp(User user, LocalDate fromDate) {
        List<LogEntry> entries = logEntryRepository.findByUserAndDeletedFalseAndEndTimestampGreaterThanEqual(user, fromDate);
        return entries.stream().mapToInt(LogEntry::getPoints).sum();
        // o .mapToInt(LogEntry::getXp) si tuvieras xp en LogEntry
    }

    // Zona
    public int calculateZoneEnergy(Zone zone, LocalDate fromDate) {
        List<LogEntry> entries = logEntryRepository.findByZoneAndDeletedFalseAndEndTimestampGreaterThanEqual(zone, fromDate);
        return entries.stream().mapToInt(LogEntry::getEnergy).sum();
    }

    // O crea un método genérico para "points, xp, etc."
}
