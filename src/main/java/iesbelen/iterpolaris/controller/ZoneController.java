package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.ZoneRequest;
import iesbelen.iterpolaris.dto.ZoneResponse;
import iesbelen.iterpolaris.service.UserService;
import iesbelen.iterpolaris.service.ZoneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class ZoneController {

    private final ZoneService zoneService;
    private final UserService userService;

    public ZoneController(ZoneService zoneService, UserService userService) {
        this.zoneService = zoneService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<ZoneResponse>> getAllZones(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(zoneService.getAllZones(user));
    }

    @GetMapping("/{zoneId}")
    public ResponseEntity<ZoneResponse> getZoneById(@AuthenticationPrincipal UserDetails userDetails,
                                                    @PathVariable Long zoneId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        return ResponseEntity.ok(zoneService.getZoneById(user, zoneId));
    }

    @PostMapping
    public ResponseEntity<ZoneResponse> createZone(@AuthenticationPrincipal UserDetails userDetails,
                                                   @RequestBody ZoneRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        ZoneResponse created = zoneService.createZone(user, request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{zoneId}")
    public ResponseEntity<ZoneResponse> updateZone(@AuthenticationPrincipal UserDetails userDetails,
                                                   @PathVariable Long zoneId,
                                                   @RequestBody ZoneRequest request) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        ZoneResponse updated = zoneService.updateZone(user, zoneId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{zoneId}")
    public ResponseEntity<Void> deleteZone(@AuthenticationPrincipal UserDetails userDetails,
                                           @PathVariable Long zoneId) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        zoneService.deleteZone(user, zoneId);
        return ResponseEntity.noContent().build();
    }
}
