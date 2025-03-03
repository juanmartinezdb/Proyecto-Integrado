package iesbelen.iterpolaris.controller;

import iesbelen.iterpolaris.dto.EffectRequest;
import iesbelen.iterpolaris.dto.EffectResponse;
import iesbelen.iterpolaris.service.EffectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/effects")
public class EffectController {

    private final EffectService effectService;

    public EffectController(EffectService effectService) {
        this.effectService = effectService;
    }

    @GetMapping
    public ResponseEntity<List<EffectResponse>> getAllEffects() {
        return ResponseEntity.ok(effectService.getAllEffects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EffectResponse> getEffectById(@PathVariable Long id) {
        return ResponseEntity.ok(effectService.getEffectById(id));
    }

    @PostMapping
    public ResponseEntity<EffectResponse> createEffect(@RequestBody EffectRequest request) {
        return ResponseEntity.ok(effectService.createEffect(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EffectResponse> updateEffect(@PathVariable Long id, @RequestBody EffectRequest request) {
        return ResponseEntity.ok(effectService.updateEffect(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEffect(@PathVariable Long id) {
        effectService.deleteEffect(id);
        return ResponseEntity.noContent().build();
    }
}
