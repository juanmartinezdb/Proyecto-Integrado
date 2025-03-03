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

    // Ejemplo: GET /stats/user/energy?period=week
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

    // Ejemplo: GET /stats/zone/{zoneId}/energy?period=month
    @GetMapping("/zone/{zoneId}/energy")
    public ResponseEntity<Integer> getZoneEnergy(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long zoneId,
            @RequestParam(defaultValue = "week") String period
    ) {
        // Verificamos la zona y usuario
        Zone zone = zoneService.getZoneEntity(userDetails, zoneId); // asumiendo un método interno CREAR EL METODO PARA QUE DEVUELVA LA ENTIDAD! O USAR UNA ZONERESPONSE
        LocalDate fromDate = computeFromDate(period);
        int energy = statsService.calculateZoneEnergy(zone, fromDate);
        return ResponseEntity.ok(energy);
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
