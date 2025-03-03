package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface JournalEntryRepository extends JpaRepository<JournalEntry, Long> {
    Optional<JournalEntry> findByIdAndDeletedFalse(Long id);
}