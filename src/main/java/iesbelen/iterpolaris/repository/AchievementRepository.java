package iesbelen.iterpolaris.repository;

import iesbelen.iterpolaris.domain.Achievement;
import iesbelen.iterpolaris.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Long> {
    List<Achievement> findByUserAndUnlockedTrue(User user);
    List<Achievement> findByUserAndTypeAndUnlockedFalse(User user, String type);
}
