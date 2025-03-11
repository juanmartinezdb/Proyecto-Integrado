package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.dto.NotificationResponse;
import iesbelen.iterpolaris.service.NotificationService;
import iesbelen.iterpolaris.service.UserService;
import iesbelen.iterpolaris.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;
    private final UserService userService;

    public NotificationController(NotificationService notificationService, UserService userService) {
        this.notificationService = notificationService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<NotificationResponse>> getUserNotifications(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(notificationService.getUserNotifications(user));
    }

    @PutMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        notificationService.markAsRead(id);
        return ResponseEntity.noContent().build();
    }
}
