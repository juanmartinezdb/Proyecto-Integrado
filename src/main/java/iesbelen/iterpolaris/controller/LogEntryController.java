package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.LogEntryRequest;
import iesbelen.iterpolaris.dto.LogEntryResponse;
import iesbelen.iterpolaris.service.LogEntryService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/log-entries")
public class LogEntryController {

    private final LogEntryService logEntryService;
    private final UserService userService;

    public LogEntryController(LogEntryService logEntryService,
                              UserService userService) {
        this.logEntryService = logEntryService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<LogEntryResponse>> getAllLogEntries(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(logEntryService.getAllLogEntries(user));
    }

    @PostMapping
    public ResponseEntity<LogEntryResponse> createLogEntry(@AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestBody LogEntryRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        LogEntryResponse response = logEntryService.createLogEntry(user, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLogEntry(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        logEntryService.deleteLogEntry(user, id);
        return ResponseEntity.noContent().build();
    }
}
