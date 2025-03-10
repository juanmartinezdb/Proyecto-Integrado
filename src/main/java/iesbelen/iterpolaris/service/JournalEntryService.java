package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Journal;
import iesbelen.iterpolaris.domain.JournalEntry;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.JournalEntryRequest;
import iesbelen.iterpolaris.dto.JournalEntryResponse;
import iesbelen.iterpolaris.repository.JournalEntryRepository;
import iesbelen.iterpolaris.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final JournalRepository journalRepository;
    private final LevelService levelService;

    public JournalEntryService(JournalEntryRepository journalEntryRepository,
                               JournalRepository journalRepository,
                               LevelService levelService) {
        this.journalEntryRepository = journalEntryRepository;
        this.journalRepository = journalRepository;
        this.levelService = levelService;
    }

    public JournalEntryResponse createEntry(User user, Long journalId, JournalEntryRequest request) {
        Journal journal = journalRepository.findByIdAndDeletedFalse(journalId)
                .orElseThrow(() -> new RuntimeException("Journal no encontrado"));
        if (!journal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este Journal");
        }

        LocalDate today = LocalDate.now();
        LocalDate lastEntryDate = journal.getLastEntryDate();

        // Verificar si la entrada es consecutiva
        if (lastEntryDate != null && lastEntryDate.plusDays(1).equals(today)) {
            journal.setStreak(journal.getStreak() + 1);
        } else if (lastEntryDate == null || !lastEntryDate.equals(today)) {
            journal.setStreak(1); // Reinicia la racha si no es el día siguiente
        }

        journal.setLastEntryDate(today); // Actualizar la última fecha de entrada
        journalRepository.save(journal);

        JournalEntry entry = JournalEntry.builder()
                .content(request.getContent())
                .editedAt(today)
                .points(request.getPoints())
                .challengeLevel(request.getChallengeLevel()) // Se usa ChallengeLevel en lugar de XP
                .deleted(false)
                .journal(journal)
                .user(user)
                .build();

        JournalEntry saved = journalEntryRepository.save(entry);

        // Calcular XP con el multiplicador de racha
        double multiplier = 1.0 + (Math.min(journal.getStreak(), 10) * 0.1); // Máximo x2 con racha de 10
        int xpGained = (int) (entry.getChallengeLevel().getXpValue() * multiplier);

        // Otorgar XP al usuario
        levelService.addXPToUser(user, xpGained);

        return mapToResponse(saved);
    }

    public List<JournalEntryResponse> getEntriesByJournal(User user, Long journalId) {
        Journal journal = journalRepository.findByIdAndDeletedFalse(journalId)
                .orElseThrow(() -> new RuntimeException("Journal no encontrado"));
        if (!journal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este Journal");
        }

        return journal.getEntries()
                .stream()
                .filter(e -> !Boolean.TRUE.equals(e.getDeleted()))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public JournalEntryResponse getEntryById(User user, Long entryId) {
        JournalEntry entry = journalEntryRepository.findByIdAndDeletedFalse(entryId)
                .orElseThrow(() -> new RuntimeException("Entrada de diario no encontrada"));
        if (!entry.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta entrada");
        }
        return mapToResponse(entry);
    }

    public JournalEntryResponse updateEntry(User user, Long entryId, JournalEntryRequest request) {
        JournalEntry entry = journalEntryRepository.findByIdAndDeletedFalse(entryId)
                .orElseThrow(() -> new RuntimeException("Entrada de diario no encontrada"));
        if (!entry.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta entrada");
        }

        entry.setContent(request.getContent());
        entry.setPoints(request.getPoints());
        entry.setChallengeLevel(request.getChallengeLevel());
        entry.setEditedAt(LocalDate.now());

        JournalEntry updated = journalEntryRepository.save(entry);
        return mapToResponse(updated);
    }

    public void deleteEntry(User user, Long entryId) {
        JournalEntry entry = journalEntryRepository.findByIdAndDeletedFalse(entryId)
                .orElseThrow(() -> new RuntimeException("Entrada de diario no encontrada"));
        if (!entry.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a esta entrada");
        }

        entry.setDeleted(true);
        journalEntryRepository.save(entry);
    }

    private JournalEntryResponse mapToResponse(JournalEntry entry) {
        return JournalEntryResponse.builder()
                .id(entry.getId())
                .content(entry.getContent())
                .editedAt(entry.getEditedAt())
                .points(entry.getPoints())
                .challengeLevel(entry.getChallengeLevel()) // Agregado en el response
                .userId(entry.getUser().getId())
                .journalId(entry.getJournal().getId())
                .build();
    }
}
