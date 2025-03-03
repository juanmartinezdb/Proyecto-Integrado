package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Gear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface GearRepository extends JpaRepository<Gear, Long> {
    Optional<Gear> findByIdAndDeletedFalse(Long id);
    List<Gear> findByDeletedFalse();
}
