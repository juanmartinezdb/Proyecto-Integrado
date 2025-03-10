package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.JournalRequest;
import iesbelen.iterpolaris.dto.JournalResponse;
import iesbelen.iterpolaris.service.JournalService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals")
public class JournalController {

    private final JournalService journalService;
    private final UserService userService;

    public JournalController(JournalService journalService, UserService userService) {
        this.journalService = journalService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<JournalResponse>> getAllJournals(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(journalService.getAllJournals(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JournalResponse> getJournalById(@AuthenticationPrincipal UserDetails userDetails,
                                                          @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(journalService.getJournalById(user, id));
    }

    @PostMapping
    public ResponseEntity<JournalResponse> createJournal(@AuthenticationPrincipal UserDetails userDetails,
                                                         @RequestBody JournalRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        JournalResponse response = journalService.createJournal(user, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JournalResponse> updateJournal(@AuthenticationPrincipal UserDetails userDetails,
                                                         @PathVariable Long id,
                                                         @RequestBody JournalRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        JournalResponse response = journalService.updateJournal(user, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJournal(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        journalService.deleteJournal(user, id);
        return ResponseEntity.noContent().build();
    }
}
