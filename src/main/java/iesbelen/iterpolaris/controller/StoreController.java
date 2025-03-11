package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.Skill;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.SkillResponse;
import iesbelen.iterpolaris.service.SkillService;
import iesbelen.iterpolaris.service.StoreService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;
    private final SkillService skillService;
    private final UserService userService;

    public StoreController(StoreService storeService, SkillService skillService, UserService userService) {
        this.storeService = storeService;
        this.skillService = skillService;
        this.userService = userService;
    }

    // 🔹 Obtener la lista de skills disponibles según el nivel del usuario
    @GetMapping("/skills")
    public ResponseEntity<List<SkillResponse>> getAvailableSkills(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(storeService.getAvailableSkills(user));
    }

    // 🔹 Obtener la lista de ítems disponibles en la tienda
    @GetMapping("/items")
    public ResponseEntity<List<SkillResponse>> getStoreItems() {
        return ResponseEntity.ok(storeService.getAvailableItems());
    }

    // 🔹 Adquirir una skill (sin costo de XP, solo nivel)
    @PostMapping("/purchase/skill/{skillId}")
    public ResponseEntity<SkillResponse> acquireSkill(@AuthenticationPrincipal UserDetails userDetails,
                                                      @PathVariable Long skillId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(storeService.acquireSkill(user, skillId));
    }

    // 🔹 Comprar un ítem con monedas
    @PostMapping("/purchase/item/{itemId}")
    public ResponseEntity<String> purchaseItem(@AuthenticationPrincipal UserDetails userDetails,
                                               @PathVariable Long itemId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        storeService.purchaseItem(user, itemId);
        return ResponseEntity.ok("Ítem comprado exitosamente.");
    }
}
