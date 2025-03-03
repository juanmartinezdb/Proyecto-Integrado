package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.JournalEntryRequest;
import iesbelen.iterpolaris.dto.JournalEntryResponse;
import iesbelen.iterpolaris.service.JournalEntryService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journals/{journalId}/entries")
public class JournalEntryController {

    private final JournalEntryService journalEntryService;
    private final UserService userService;

    public JournalEntryController(JournalEntryService journalEntryService, UserService userService) {
        this.journalEntryService = journalEntryService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<JournalEntryResponse>> getEntriesByJournal(@AuthenticationPrincipal UserDetails userDetails,
                                                                          @PathVariable Long journalId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(journalEntryService.getEntriesByJournal(user, journalId));
    }

    @GetMapping("/{entryId}")
    public ResponseEntity<JournalEntryResponse> getEntryById(@AuthenticationPrincipal UserDetails userDetails,
                                                             @PathVariable Long entryId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(journalEntryService.getEntryById(user, entryId));
    }

    @PostMapping
    public ResponseEntity<JournalEntryResponse> createEntry(@AuthenticationPrincipal UserDetails userDetails,
                                                            @PathVariable Long journalId,
                                                            @RequestBody JournalEntryRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        JournalEntryResponse response = journalEntryService.createEntry(user, journalId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<JournalEntryResponse> updateEntry(@AuthenticationPrincipal UserDetails userDetails,
                                                            @PathVariable Long entryId,
                                                            @RequestBody JournalEntryRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        JournalEntryResponse response = journalEntryService.updateEntry(user, entryId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<Void> deleteEntry(@AuthenticationPrincipal UserDetails userDetails,
                                            @PathVariable Long entryId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        journalEntryService.deleteEntry(user, entryId);
        return ResponseEntity.noContent().build();
    }
}