package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Effect;
import iesbelen.iterpolaris.domain.Gear;
import iesbelen.iterpolaris.dto.GearRequest;
import iesbelen.iterpolaris.dto.GearResponse;
import iesbelen.iterpolaris.repository.EffectRepository;
import iesbelen.iterpolaris.repository.GearRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GearService {

    private final GearRepository gearRepository;
    private final EffectRepository effectRepository;

    public GearService(GearRepository gearRepository, EffectRepository effectRepository) {
        this.gearRepository = gearRepository;
        this.effectRepository = effectRepository;
    }

    public GearResponse createGear(GearRequest request) {
        Effect effect = effectRepository.findByIdAndDeletedFalse(request.getEffectId())
                .orElseThrow(() -> new RuntimeException("Effect no encontrado"));

        Gear gear = Gear.builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .type(request.getType())
                .maxUses(request.getMaxUses())
                .cost(request.getCost())
                .consumable(request.getConsumable())
                .rarity(request.getRarity())
                .effect(effect)
                .deleted(false)
                .build();

        Gear saved = gearRepository.save(gear);
        return mapToResponse(saved);
    }

    public List<GearResponse> getAllGears() {
        return gearRepository.findByDeletedFalse()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public GearResponse getGearById(Long gearId) {
        Gear gear = gearRepository.findByIdAndDeletedFalse(gearId)
                .orElseThrow(() -> new RuntimeException("Gear no encontrado"));
        return mapToResponse(gear);
    }

    public GearResponse updateGear(Long gearId, GearRequest request) {
        Gear gear = gearRepository.findByIdAndDeletedFalse(gearId)
                .orElseThrow(() -> new RuntimeException("Gear no encontrado"));

        if (request.getEffectId() != null) {
            Effect effect = effectRepository.findByIdAndDeletedFalse(request.getEffectId())
                    .orElseThrow(() -> new RuntimeException("Effect no encontrado"));
            gear.setEffect(effect);
        }

        gear.setName(request.getName());
        gear.setDescription(request.getDescription());
        gear.setImage(request.getImage());
        gear.setType(request.getType());
        gear.setMaxUses(request.getMaxUses());
        gear.setCost(request.getCost());
        gear.setConsumable(request.getConsumable());
        gear.setRarity(request.getRarity());

        Gear updated = gearRepository.save(gear);
        return mapToResponse(updated);
    }

    public void deleteGear(Long gearId) {
        Gear gear = gearRepository.findByIdAndDeletedFalse(gearId)
                .orElseThrow(() -> new RuntimeException("Gear no encontrado"));
        gear.setDeleted(true);
        gearRepository.save(gear);
    }

    private GearResponse mapToResponse(Gear gear) {
        return GearResponse.builder()
                .id(gear.getId())
                .name(gear.getName())
                .description(gear.getDescription())
                .image(gear.getImage())
                .type(gear.getType())
                .maxUses(gear.getMaxUses())
                .cost(gear.getCost())
                .consumable(gear.getConsumable())
                .rarity(gear.getRarity())
                .effectId(gear.getEffect().getId())
                .build();
    }
}
