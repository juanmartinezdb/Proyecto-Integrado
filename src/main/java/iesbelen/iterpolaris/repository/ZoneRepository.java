package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.domain.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Optional<Zone> findByIdAndDeletedFalse(Long id);
    List<Zone> findByUserAndDeletedFalse(User user);
}
