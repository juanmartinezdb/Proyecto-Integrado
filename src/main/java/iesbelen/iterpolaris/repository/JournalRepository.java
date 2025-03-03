package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Journal;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface JournalRepository extends JpaRepository<Journal, Long> {
    Optional<Journal> findByIdAndDeletedFalse(Long id);
    List<Journal> findByUserAndDeletedFalse(User user);
}