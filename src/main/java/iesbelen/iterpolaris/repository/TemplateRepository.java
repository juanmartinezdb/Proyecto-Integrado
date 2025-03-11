package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Template;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Long> {
    List<Template> findByUserAndDeletedFalse(User user);
    Optional<Template> findByIdAndDeletedFalse(Long id);


}
