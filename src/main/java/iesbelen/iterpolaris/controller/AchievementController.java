package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.dto.AchievementResponse;
import iesbelen.iterpolaris.service.AchievementService;
import iesbelen.iterpolaris.service.UserService;
import iesbelen.iterpolaris.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/achievements")
public class AchievementController {

    private final AchievementService achievementService;
    private final UserService userService;

    public AchievementController(AchievementService achievementService, UserService userService) {
        this.achievementService = achievementService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<AchievementResponse>> getUserAchievements(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(achievementService.getUserAchievements(user));
    }
}
