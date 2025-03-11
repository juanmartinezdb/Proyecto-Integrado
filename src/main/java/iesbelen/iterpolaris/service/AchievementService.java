package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Achievement;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.AchievementResponse;
import iesbelen.iterpolaris.repository.AchievementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AchievementService {

    private final AchievementRepository achievementRepository;
    private final NotificationService notificationService; // 🔹 Inyectamos NotificationService

    public AchievementService(AchievementRepository achievementRepository, NotificationService notificationService) {
        this.achievementRepository = achievementRepository;
        this.notificationService = notificationService; // 🔹 Asignamos el servicio inyectado
    }

    public List<AchievementResponse> getUserAchievements(User user) {
        return achievementRepository.findByUserAndUnlockedTrue(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void checkAndUnlockAchievement(User user, String type, int value) {
        List<Achievement> achievements = achievementRepository.findByUserAndTypeAndUnlockedFalse(user, type);
        for (Achievement achievement : achievements) {
            if (value >= achievement.getThreshold()) {
                achievement.setUnlocked(true);
                achievementRepository.save(achievement);
                notificationService.createReminder(user, "¡Logro desbloqueado: " + achievement.getName() + "!");
            }
        }
    }

    private AchievementResponse mapToResponse(Achievement achievement) {
        return AchievementResponse.builder()
                .id(achievement.getId())
                .name(achievement.getName())
                .description(achievement.getDescription())
                .unlocked(achievement.getUnlocked())
                .build();
    }
}

