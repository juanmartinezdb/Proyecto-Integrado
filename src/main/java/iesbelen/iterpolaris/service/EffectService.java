package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Effect;
import iesbelen.iterpolaris.dto.EffectRequest;
import iesbelen.iterpolaris.dto.EffectResponse;
import iesbelen.iterpolaris.repository.EffectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EffectService {

    private final EffectRepository effectRepository;

    public EffectService(EffectRepository effectRepository) {
        this.effectRepository = effectRepository;
    }

    public EffectResponse createEffect(EffectRequest request) {
        Effect effect = Effect.builder()
                .name(request.getName())
                .logicKey(request.getLogicKey())
                .description(request.getDescription())
                .type(request.getType())
                .targetEntities(request.getTargetEntities())
                .deleted(false)
                .build();

        Effect saved = effectRepository.save(effect);
        return mapToResponse(saved);
    }

    public List<EffectResponse> getAllEffects() {
        return effectRepository.findByDeletedFalse()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public EffectResponse getEffectById(Long effectId) {
        Effect effect = effectRepository.findByIdAndDeletedFalse(effectId)
                .orElseThrow(() -> new RuntimeException("Effect no encontrado"));
        return mapToResponse(effect);
    }

    public EffectResponse updateEffect(Long effectId, EffectRequest request) {
        Effect effect = effectRepository.findByIdAndDeletedFalse(effectId)
                .orElseThrow(() -> new RuntimeException("Effect no encontrado"));

        effect.setName(request.getName());
        effect.setLogicKey(request.getLogicKey());
        effect.setDescription(request.getDescription());
        effect.setType(request.getType());
        effect.setTargetEntities(request.getTargetEntities());

        Effect updated = effectRepository.save(effect);
        return mapToResponse(updated);
    }

    public void deleteEffect(Long effectId) {
        Effect effect = effectRepository.findByIdAndDeletedFalse(effectId)
                .orElseThrow(() -> new RuntimeException("Effect no encontrado"));
        effect.setDeleted(true);
        effectRepository.save(effect);
    }

    private EffectResponse mapToResponse(Effect effect) {
        return EffectResponse.builder()
                .id(effect.getId())
                .name(effect.getName())
                .logicKey(effect.getLogicKey())
                .description(effect.getDescription())
                .type(effect.getType())
                .targetEntities(effect.getTargetEntities())
                .build();
    }
}
