package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.InventoryItemRequest;
import iesbelen.iterpolaris.dto.InventoryItemResponse;
import iesbelen.iterpolaris.service.InventoryService;
import iesbelen.iterpolaris.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryService inventoryService;
    private final UserService userService;

    public InventoryController(InventoryService inventoryService,
                               UserService userService) {
        this.inventoryService = inventoryService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<InventoryItemResponse>> getUserInventory(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(inventoryService.getUserInventory(user));
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<InventoryItemResponse> getItemById(@AuthenticationPrincipal UserDetails userDetails,
                                                             @PathVariable Long itemId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(inventoryService.getItemById(user, itemId));
    }

    @PostMapping
    public ResponseEntity<InventoryItemResponse> addGearToUser(@AuthenticationPrincipal UserDetails userDetails,
                                                               @RequestBody InventoryItemRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        InventoryItemResponse response = inventoryService.addGearToUser(user, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<InventoryItemResponse> updateInventoryItem(@AuthenticationPrincipal UserDetails userDetails,
                                                                     @PathVariable Long itemId,
                                                                     @RequestBody InventoryItemRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        InventoryItemResponse response = inventoryService.updateInventoryItem(user, itemId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Void> deleteInventoryItem(@AuthenticationPrincipal UserDetails userDetails,
                                                    @PathVariable Long itemId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        inventoryService.deleteInventoryItem(user, itemId);
        return ResponseEntity.noContent().build();
    }
}
