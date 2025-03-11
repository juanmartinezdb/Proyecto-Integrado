package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Project;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ProjectRepository extends JpaRepository<Project, Long> {

    Optional<Project> findByIdAndDeletedFalse(Long id);

    List<Project> findByUserAndDeletedFalse(User user);

    long countByUserAndStatus(User user, String status);
}
