package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Habit;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface HabitRepository extends JpaRepository<Habit, Long> {
    Optional<Habit> findByIdAndDeletedFalse(Long id);
    List<Habit> findByUserAndDeletedFalse(User user);
}