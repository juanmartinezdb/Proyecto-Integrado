package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.domain.Zone;
import iesbelen.iterpolaris.repository.UserRepository;
import iesbelen.iterpolaris.repository.ZoneRepository;
import org.springframework.stereotype.Service;

@Service
public class LevelService {

    private final UserRepository userRepository;
    private final ZoneRepository zoneRepository;

    public LevelService(UserRepository userRepository, ZoneRepository zoneRepository) {
        this.userRepository = userRepository;
        this.zoneRepository = zoneRepository;
    }

    public void addXPToUser(User user, int xpGained) {
        user.setXp(user.getXp() + xpGained);
        checkLevelUp(user);
    }

    public void addXPToZone(Zone zone, int xpGained) {
        zone.setXp(zone.getXp() + xpGained);
        checkZoneLevelUp(zone);
    }

    private void checkLevelUp(User user) {
        int currentLevel = user.getLevel();
        int xpNeeded = getXpForLevel(currentLevel + 1);
        while (user.getXp() >= xpNeeded) {
            user.setLevel(currentLevel + 1);
            user.setSkillPoints(user.getSkillPoints() + 1); // Otorga un punto de habilidad
            user.setXp(user.getXp() - xpNeeded);
            currentLevel++;
            xpNeeded = getXpForLevel(currentLevel + 1);
        }
        userRepository.save(user);
    }

    private void checkZoneLevelUp(Zone zone) {
        int currentLevel = zone.getLevel();
        int xpNeeded = getXpForLevel(currentLevel + 1);
        while (zone.getXp() >= xpNeeded) {
            zone.setLevel(currentLevel + 1);
            zone.setXp(zone.getXp() - xpNeeded);
            currentLevel++;
            xpNeeded = getXpForLevel(currentLevel + 1);
        }
        zoneRepository.save(zone);
    }

    private int getXpForLevel(int level) {
        return (int) (2000 * Math.pow(1.15, level - 2));
    }
}
