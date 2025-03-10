package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.domain.Zone;
import iesbelen.iterpolaris.service.StatsService;
import iesbelen.iterpolaris.service.UserService;
import iesbelen.iterpolaris.service.ZoneService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;
    private final UserService userService;
    private final ZoneService zoneService;

    public StatsController(StatsService statsService, UserService userService, ZoneService zoneService) {
        this.statsService = statsService;
        this.userService = userService;
        this.zoneService = zoneService;
    }

    // GET /stats/user/energy?period=week
    @GetMapping("/user/energy")
    public ResponseEntity<Integer> getUserEnergy(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "week") String period
    ) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        LocalDate fromDate = computeFromDate(period);
        int energy = statsService.calculateUserEnergy(user, fromDate);
        return ResponseEntity.ok(energy);
    }

    // GET /stats/user/xp?period=week
    @GetMapping("/user/xp")
    public ResponseEntity<Integer> getUserXp(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(defaultValue = "week") String period
    ) {
        User user = userService.getUserByUsername(userDetails.getUsername());
        LocalDate fromDate = computeFromDate(period);
        int xp = statsService.calculateUserXp(user, fromDate);
        return ResponseEntity.ok(xp);
    }

    // GET /stats/zone/{zoneId}/energy?period=week
    @GetMapping("/zone/{zoneId}/energy")
    public ResponseEntity<Integer> getZoneEnergy(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long zoneId,
            @RequestParam(defaultValue = "week") String period
    ) {
        Zone zone = zoneService.getZoneEntity(userDetails, zoneId);
        LocalDate fromDate = computeFromDate(period);
        int energy = statsService.calculateZoneEnergy(zone, fromDate);
        return ResponseEntity.ok(energy);
    }

    // GET /stats/zone/{zoneId}/xp?period=week
    @GetMapping("/zone/{zoneId}/xp")
    public ResponseEntity<Integer> getZoneXp(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long zoneId,
            @RequestParam(defaultValue = "week") String period
    ) {
        Zone zone = zoneService.getZoneEntity(userDetails, zoneId);
        LocalDate fromDate = computeFromDate(period);
        int xp = statsService.calculateZoneXp(zone, fromDate);
        return ResponseEntity.ok(xp);
    }

    // Calcula la fecha "desde" según el período
    private LocalDate computeFromDate(String period) {
        LocalDate now = LocalDate.now();
        switch (period.toLowerCase()) {
            case "day":
                return now; // Hoy
            case "month":
                return now.minusDays(30);
            case "week":
            default:
                return now.minusDays(7);
        }
    }
}
