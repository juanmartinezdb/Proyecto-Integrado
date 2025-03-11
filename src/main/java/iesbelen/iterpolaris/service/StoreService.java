package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Skill;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.SkillResponse;
import iesbelen.iterpolaris.repository.SkillRepository;
import iesbelen.iterpolaris.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {

    private final SkillRepository skillRepository;
    private final UserRepository userRepository;

    public StoreService(SkillRepository skillRepository, UserRepository userRepository) {
        this.skillRepository = skillRepository;
        this.userRepository = userRepository;
    }


    public List<SkillResponse> getAvailableSkills(User user) {
        return skillRepository.findAll()
                .stream()
                .filter(skill -> skill.getLevelRequired() <= user.getLevel())
                .map(this::mapToResponse) //
                .collect(Collectors.toList());
    }


    public List<SkillResponse> getAvailableItems() {
        return List.of();
    }


    public SkillResponse acquireSkill(User user, Long skillId) {
        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() -> new RuntimeException("Skill no encontrada"));

        if (skill.getLevelRequired() > user.getLevel()) {
            throw new RuntimeException("No tienes el nivel suficiente para esta skill.");
        }

        if (user.getSkills().size() >= user.getLevel()) {
            throw new RuntimeException("Has alcanzado el límite de skills permitidas.");
        }

        user.getSkills().add(skill);
        userRepository.save(user);
        return mapToResponse(skill);
    }

    // 🔹 Comprar un ítem (pendiente de definir los ítems en la tienda)
    public void purchaseItem(User user, Long itemId) {
        throw new UnsupportedOperationException("Sistema de ítems aún no implementado.");
    }

    private SkillResponse mapToResponse(Skill skill) {
        return SkillResponse.builder()
                .id(skill.getId())
                .name(skill.getName())
                .description(skill.getDescription())
                .type(skill.getType())
                .level(skill.getLevelRequired())
                .cost(skill.getCost())
                .mana(skill.getMana())
                .icon(skill.getIcon())
                .effectId(skill.getEffect().getId())
                .build();
    }
}
