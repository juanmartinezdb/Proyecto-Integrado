package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Effect;
import iesbelen.iterpolaris.domain.Habit;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.domain.Zone;
import iesbelen.iterpolaris.dto.HabitRequest;
import iesbelen.iterpolaris.dto.HabitResponse;
import iesbelen.iterpolaris.repository.EffectRepository;
import iesbelen.iterpolaris.repository.HabitRepository;
import iesbelen.iterpolaris.repository.ZoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HabitService {

    private final HabitRepository habitRepository;
    private final ZoneRepository zoneRepository;
    private final EffectRepository effectRepository;

    public HabitService(HabitRepository habitRepository,
                        ZoneRepository zoneRepository,
                        EffectRepository effectRepository) {
        this.habitRepository = habitRepository;
        this.zoneRepository = zoneRepository;
        this.effectRepository = effectRepository;
    }

    public HabitResponse createHabit(User user, HabitRequest request) {
        Zone zone = null;
        if (request.getZoneId() != null) {
            zone = zoneRepository.findByIdAndDeletedFalse(request.getZoneId())
                    .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
            if (!zone.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("No tienes acceso a esta zona");
            }
        }

        Effect effect = null;
        if (request.getEffectId() != null) {
            effect = effectRepository.findById(request.getEffectId())
                    .orElseThrow(() -> new RuntimeException("Efecto no encontrado"));
        }

        Habit habit = Habit.builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .active(request.getActive())
                .energy(request.getEnergy())
                .points(request.getPoints())
                .xp(request.getXp())
                .frequency(request.getFrequency())
                .streak(0)
                .totalCheck(0)
                .zone(zone)
                .effect(effect)
                .deleted(false)
                .user(user)
                .build();

        Habit saved = habitRepository.save(habit);
        return mapToResponse(saved);
    }

    public List<HabitResponse> getAllHabits(User user) {
        return habitRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public HabitResponse getHabitById(User user, Long habitId) {
        Habit habit = habitRepository.findByIdAndDeletedFalse(habitId)
                .orElseThrow(() -> new RuntimeException("Hábito no encontrado"));
        if (!habit.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este hábito");
        }
        return mapToResponse(habit);
    }

    public HabitResponse updateHabit(User user, Long habitId, HabitRequest request) {
        Habit habit = habitRepository.findByIdAndDeletedFalse(habitId)
                .orElseThrow(() -> new RuntimeException("Hábito no encontrado"));
        if (!habit.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este hábito");
        }

        habit.setName(request.getName());
        habit.setDescription(request.getDescription());
        habit.setImage(request.getImage());
        habit.setActive(request.getActive());
        habit.setEnergy(request.getEnergy());
        habit.setPoints(request.getPoints());
        habit.setXp(request.getXp());
        habit.setFrequency(request.getFrequency());

        if (request.getZoneId() != null) {
            Zone zone = zoneRepository.findByIdAndDeletedFalse(request.getZoneId())
                    .orElseThrow(() -> new RuntimeException("Zona no encontrada"));
            if (!zone.getUser().getId().equals(user.getId())) {
                throw new RuntimeException("No tienes acceso a esta zona");
            }
            habit.setZone(zone);
        } else {
            habit.setZone(null);
        }

        if (request.getEffectId() != null) {
            Effect effect = effectRepository.findById(request.getEffectId())
                    .orElseThrow(() -> new RuntimeException("Efecto no encontrado"));
            habit.setEffect(effect);
        } else {
            habit.setEffect(null);
        }

        Habit updated = habitRepository.save(habit);
        return mapToResponse(updated);
    }

    public void deleteHabit(User user, Long habitId) {
        Habit habit = habitRepository.findByIdAndDeletedFalse(habitId)
                .orElseThrow(() -> new RuntimeException("Hábito no encontrado"));
        if (!habit.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este hábito");
        }
        habit.setDeleted(true);
        habitRepository.save(habit);
    }

    private HabitResponse mapToResponse(Habit habit) {
        return HabitResponse.builder()
                .id(habit.getId())
                .name(habit.getName())
                .description(habit.getDescription())
                .image(habit.getImage())
                .active(habit.getActive())
                .energy(habit.getEnergy())
                .points(habit.getPoints())
                .xp(habit.getXp())
                .frequency(habit.getFrequency())
                .streak(habit.getStreak())
                .totalCheck(habit.getTotalCheck())
                .zoneId(habit.getZone() != null ? habit.getZone().getId() : null)
                .userId(habit.getUser().getId())
                .effectId(habit.getEffect() != null ? habit.getEffect().getId() : null)
                .build();
    }
}
