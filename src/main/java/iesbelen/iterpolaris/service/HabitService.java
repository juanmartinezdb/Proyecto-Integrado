package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.*;
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
    private final LevelService levelService;

    public HabitService(HabitRepository habitRepository,
                        ZoneRepository zoneRepository,
                        EffectRepository effectRepository,
                        LevelService levelService) {
        this.habitRepository = habitRepository;
        this.zoneRepository = zoneRepository;
        this.effectRepository = effectRepository;
        this.levelService = levelService;
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
            effect = effectRepository.findByIdAndDeletedFalse(request.getEffectId())
                    .orElseThrow(() -> new RuntimeException("Efecto no encontrado"));
        }

        Habit habit = Habit.builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .active(request.getActive())
                .energy(request.getEnergy())
                .points(request.getPoints())
                .frequency(request.getFrequency())
                .streak(0)
                .totalCheck(0)
                .zone(zone)
                .effect(effect)
                .challengeLevel(request.getChallengeLevel()) // Se usa challengeLevel en lugar de XP
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
        habit.setFrequency(request.getFrequency());
        habit.setChallengeLevel(request.getChallengeLevel());

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

    public void completeHabit(User user, Long habitId) {
        Habit habit = habitRepository.findByIdAndDeletedFalse(habitId)
                .orElseThrow(() -> new RuntimeException("Hábito no encontrado"));
        if (!habit.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este hábito");
        }

        // Incrementar la racha y total de veces completado
        habit.setStreak(habit.getStreak() + 1);
        habit.setTotalCheck(habit.getTotalCheck() + 1);
        habitRepository.save(habit);

        // Calcular XP con el multiplicador de racha
        double multiplier = 1.0 + (Math.min(habit.getStreak(), 10) * 0.1); // Máximo x2 con racha de 10
        int xpGained = (int) (habit.getChallengeLevel().getXpValue() * multiplier);

        // Asignar XP al usuario
        levelService.addXPToUser(user, xpGained);
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
                .frequency(habit.getFrequency())
                .streak(habit.getStreak())
                .totalCheck(habit.getTotalCheck())
                .challengeLevel(habit.getChallengeLevel()) // Agregado en el response
                .zoneId(habit.getZone() != null ? habit.getZone().getId() : null)
                .userId(habit.getUser().getId())
                .effectId(habit.getEffect() != null ? habit.getEffect().getId() : null)
                .build();
    }
}
