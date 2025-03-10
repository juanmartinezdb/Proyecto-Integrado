package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.TaskRequest;
import iesbelen.iterpolaris.dto.TaskResponse;
import iesbelen.iterpolaris.service.TaskService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(taskService.getAllTasks(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@AuthenticationPrincipal UserDetails userDetails,
                                                    @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(taskService.getTaskById(user, id));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestBody TaskRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        TaskResponse response = taskService.createTask(user, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@AuthenticationPrincipal UserDetails userDetails,
                                                   @PathVariable Long id,
                                                   @RequestBody TaskRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        TaskResponse response = taskService.updateTask(user, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        taskService.deleteTask(user, id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<Void> completeTask(@AuthenticationPrincipal UserDetails userDetails,
                                             @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        taskService.completeTask(user, id);
        return ResponseEntity.noContent().build();
    }
}
