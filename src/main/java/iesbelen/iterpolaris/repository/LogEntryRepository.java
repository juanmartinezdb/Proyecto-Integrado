package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.LogEntry;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
    List<LogEntry> findByUserAndDeletedFalse(User user);
    // Podríamos agregar más métodos, p.ej. filtrar por rango de fechas

        // Filtra por usuario, no borrado y endTimestamp >= una fecha
        List<LogEntry> findByUserAndDeletedFalseAndEndTimestampGreaterThanEqual(User user, LocalDate date);

        // Filtra por zona, no borrado y endTimestamp >= una fecha
        List<LogEntry> findByZoneAndDeletedFalseAndEndTimestampGreaterThanEqual(Zone zone, LocalDate date);

        // Si necesitas filtrar entre dos fechas
        // List<LogEntry> findByUserAndDeletedFalseAndEndTimestampBetween(User user, LocalDate from, LocalDate to);

    }
