package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.dto.TemplateRequest;
import iesbelen.iterpolaris.dto.TemplateResponse;
import iesbelen.iterpolaris.service.TemplateService;
import iesbelen.iterpolaris.service.UserService;
import iesbelen.iterpolaris.domain.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/templates")
public class TemplateController {

    private final TemplateService templateService;
    private final UserService userService;

    public TemplateController(TemplateService templateService, UserService userService) {
        this.templateService = templateService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<TemplateResponse>> getAllTemplates(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) String category) { // 🔹 Agregamos el parámetro opcional
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(templateService.getAllTemplates(user, category)); // 🔹 Pasamos ambos parámetros
    }

    @PostMapping
    public ResponseEntity<TemplateResponse> createTemplate(@AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestBody TemplateRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(templateService.createTemplate(user, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemplate(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        templateService.deleteTemplate(user, id);
        return ResponseEntity.noContent().build();
    }
}
