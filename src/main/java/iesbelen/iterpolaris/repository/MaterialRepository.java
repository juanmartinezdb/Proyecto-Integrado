package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Material;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface MaterialRepository extends JpaRepository<Material, Long> {
    Optional<Material> findByIdAndDeletedFalse(Long id);
    List<Material> findByUserAndDeletedFalse(User user);
}