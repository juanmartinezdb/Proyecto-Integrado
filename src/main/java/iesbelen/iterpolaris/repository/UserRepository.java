package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByIdAndDeletedFalse(Long id);
    Optional<User> findByUsernameAndDeletedFalse(String username);
    List<User> findByDeletedFalse();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
