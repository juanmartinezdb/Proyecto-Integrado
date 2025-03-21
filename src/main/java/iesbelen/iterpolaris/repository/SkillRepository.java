package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface SkillRepository extends JpaRepository<Skill, Long> {
    Optional<Skill> findByIdAndDeletedFalse(Long id);
    List<Skill> findByDeletedFalse();
}
