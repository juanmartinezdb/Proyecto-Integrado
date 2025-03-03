package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Effect;
import iesbelen.iterpolaris.domain.Skill;
import iesbelen.iterpolaris.dto.SkillRequest;
import iesbelen.iterpolaris.dto.SkillResponse;
import iesbelen.iterpolaris.repository.EffectRepository;
import iesbelen.iterpolaris.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final EffectRepository effectRepository;

    public SkillService(SkillRepository skillRepository, EffectRepository effectRepository) {
        this.skillRepository = skillRepository;
        this.effectRepository = effectRepository;
    }

    public SkillResponse createSkill(SkillRequest request) {
        Effect effect = effectRepository.findByIdAndDeletedFalse(request.getEffectId())
                .orElseThrow(() -> new RuntimeException("Effect no encontrado"));

        Skill skill = Skill.builder()
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .level(request.getLevel())
                .cost(request.getCost())
                .mana(request.getMana())
                .icon(request.getIcon())
                .effect(effect)
                .deleted(false)
                .build();

        Skill saved = skillRepository.save(skill);
        return mapToResponse(saved);
    }

    public List<SkillResponse> getAllSkills() {
        return skillRepository.findByDeletedFalse()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public SkillResponse getSkillById(Long skillId) {
        Skill skill = skillRepository.findByIdAndDeletedFalse(skillId)
                .orElseThrow(() -> new RuntimeException("Skill no encontrado"));
        return mapToResponse(skill);
    }

    public SkillResponse updateSkill(Long skillId, SkillRequest request) {
        Skill skill = skillRepository.findByIdAndDeletedFalse(skillId)
                .orElseThrow(() -> new RuntimeException("Skill no encontrado"));

        if (request.getEffectId() != null) {
            Effect effect = effectRepository.findByIdAndDeletedFalse(request.getEffectId())
                    .orElseThrow(() -> new RuntimeException("Effect no encontrado"));
            skill.setEffect(effect);
        }

        skill.setName(request.getName());
        skill.setDescription(request.getDescription());
        skill.setType(request.getType());
        skill.setLevel(request.getLevel());
        skill.setCost(request.getCost());
        skill.setMana(request.getMana());
        skill.setIcon(request.getIcon());

        Skill updated = skillRepository.save(skill);
        return mapToResponse(updated);
    }

    public void deleteSkill(Long skillId) {
        Skill skill = skillRepository.findByIdAndDeletedFalse(skillId)
                .orElseThrow(() -> new RuntimeException("Skill no encontrado"));
        skill.setDeleted(true);
        skillRepository.save(skill);
    }

    private SkillResponse mapToResponse(Skill skill) {
        return SkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .description(skill.getDescription())
                .type(skill.getType())
                .level(skill.getLevel())
                .cost(skill.getCost())
                .mana(skill.getMana())
                .icon(skill.getIcon())
                .effectId(skill.getEffect().getId())
                .build();
    }
}
