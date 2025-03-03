package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.MaterialRequest;
import iesbelen.iterpolaris.dto.MaterialResponse;
import iesbelen.iterpolaris.service.MaterialService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
public class MaterialController {

    private final MaterialService materialService;
    private final UserService userService;

    public MaterialController(MaterialService materialService, UserService userService) {
        this.materialService = materialService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> getAllMaterials(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(materialService.getAllMaterials(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MaterialResponse> getMaterialById(@AuthenticationPrincipal UserDetails userDetails,
                                                            @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(materialService.getMaterialById(user, id));
    }

    @PostMapping
    public ResponseEntity<MaterialResponse> createMaterial(@AuthenticationPrincipal UserDetails userDetails,
                                                           @RequestBody MaterialRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        MaterialResponse response = materialService.createMaterial(user, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> updateMaterial(@AuthenticationPrincipal UserDetails userDetails,
                                                           @PathVariable Long id,
                                                           @RequestBody MaterialRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        MaterialResponse response = materialService.updateMaterial(user, id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable Long id) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        materialService.deleteMaterial(user, id);
        return ResponseEntity.noContent().build();
    }
}