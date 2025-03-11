package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Notification;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.NotificationResponse;
import iesbelen.iterpolaris.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationResponse> getUserNotifications(User user) {
        return notificationRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public void markAsRead(Long notificationId) {
        Notification notification = notificationRepository.findByIdAndDeletedFalse(notificationId)
                .orElseThrow(() -> new RuntimeException("Notificación no encontrada"));
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }

    public void createReminder(User user, String message) {
        Notification notification = Notification.builder()
                .message(message)
                .type("reminder")
                .isRead(false)
                .createdAt(LocalDateTime.now())
                .user(user)
                .deleted(false)
                .build();
        notificationRepository.save(notification);
    }

    private NotificationResponse mapToResponse(Notification notification) {
        return NotificationResponse.builder()
                .id(notification.getId())
                .message(notification.getMessage())
                .type(notification.getType())
                .read(notification.getIsRead())
                .createdAt(notification.getCreatedAt())
                .build();
    }
}
