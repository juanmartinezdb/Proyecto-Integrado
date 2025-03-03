package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Task;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByIdAndDeletedFalse(Long id);
    List<Task> findByUserAndDeletedFalse(User user);
}