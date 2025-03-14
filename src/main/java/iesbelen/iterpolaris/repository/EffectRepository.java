package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Effect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EffectRepository extends JpaRepository<Effect, Long> {
    Optional<Effect> findByIdAndDeletedFalse(Long id);
    List<Effect> findByDeletedFalse();
}
