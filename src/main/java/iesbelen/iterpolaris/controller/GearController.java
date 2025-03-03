package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.dto.GearRequest;
import iesbelen.iterpolaris.dto.GearResponse;
import iesbelen.iterpolaris.service.GearService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gears")
public class GearController {

    private final GearService gearService;

    public GearController(GearService gearService) {
        this.gearService = gearService;
    }

    @GetMapping
    public ResponseEntity<List<GearResponse>> getAllGears() {
        return ResponseEntity.ok(gearService.getAllGears());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GearResponse> getGearById(@PathVariable Long id) {
        return ResponseEntity.ok(gearService.getGearById(id));
    }

    @PostMapping
    public ResponseEntity<GearResponse> createGear(@RequestBody GearRequest request) {
        return ResponseEntity.ok(gearService.createGear(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GearResponse> updateGear(@PathVariable Long id, @RequestBody GearRequest request) {
        return ResponseEntity.ok(gearService.updateGear(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGear(@PathVariable Long id) {
        gearService.deleteGear(id);
        return ResponseEntity.noContent().build();
    }
}
