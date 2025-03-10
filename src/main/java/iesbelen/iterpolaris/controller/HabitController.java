package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.HabitRequest;
import iesbelen.iterpolaris.dto.HabitResponse;
import iesbelen.iterpolaris.service.HabitService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habits")
public class HabitController {

    private final HabitService habitService;
    private final UserService userService;

    public HabitController(HabitService habitService, UserService userService) {
        this.habitService = habitService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<HabitResponse>> getAllHabits(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(habitService.getAllHabits(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabitResponse> getHabitById(@AuthenticationPrincipal UserDetails userDetails,
                                                      @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(habitService.getHabitById(user, id));
    }

    @PostMapping
    public ResponseEntity<HabitResponse> createHabit(@AuthenticationPrincipal UserDetails userDetails,
                                                     @RequestBody HabitRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        HabitResponse response = habitService.createHabit(user, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabitResponse> updateHabit(@AuthenticationPrincipal UserDetails userDetails,
                                                     @PathVariable Long id,
                                                     @RequestBody HabitRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        HabitResponse response = habitService.updateHabit(user, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHabit(@AuthenticationPrincipal UserDetails userDetails,
                                            @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        habitService.deleteHabit(user, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeHabit(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        habitService.completeHabit(user, id);
        return ResponseEntity.noContent().build();
    }
}
