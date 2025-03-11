package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Notification;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findByUserAndDeletedFalse(User user);

    Optional<Notification> findByIdAndDeletedFalse(Long id);
}
