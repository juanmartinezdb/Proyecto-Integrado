package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.ProjectRequest;
import iesbelen.iterpolaris.dto.ProjectResponse;
import iesbelen.iterpolaris.service.ProjectService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final UserService userService;

    public ProjectController(ProjectService projectService,
                             UserService userService) {
        this.projectService = projectService;
        this.userService = userService;
    }

    // GET /projects
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(projectService.getAllProjects(user));
    }

    // GET /projects/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@AuthenticationPrincipal UserDetails userDetails,
                                                          @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(projectService.getProjectById(user, id));
    }

    // POST /projects
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@AuthenticationPrincipal UserDetails userDetails,
                                                         @RequestBody ProjectRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        ProjectResponse response = projectService.createProject(user, request);
        return ResponseEntity.ok(response);
    }

    // PUT /projects/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(@AuthenticationPrincipal UserDetails userDetails,
                                                         @PathVariable Long id,
                                                         @RequestBody ProjectRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        ProjectResponse response = projectService.updateProject(user, id, request);
        return ResponseEntity.ok(response);
    }

    // DELETE /projects/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@AuthenticationPrincipal UserDetails userDetails,
                                              @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        projectService.deleteProject(user, id);
        return ResponseEntity.noContent().build();
    }

    // POST /projects/{id}/complete
    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeProject(@AuthenticationPrincipal UserDetails userDetails,
                                                @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        projectService.completeProject(user, id);
        return ResponseEntity.noContent().build();
    }
}
