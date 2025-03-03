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

    public JournalEntryService(JournalEntryRepository journalEntryRepository,
                               JournalRepository journalRepository) {
        this.journalEntryRepository = journalEntryRepository;
        this.journalRepository = journalRepository;
    }

    public JournalEntryResponse createEntry(User user, Long journalId, JournalEntryRequest request) {
        Journal journal = journalRepository.findByIdAndDeletedFalse(journalId)
                .orElseThrow(() -> new RuntimeException("Journal no encontrado"));
        if (!journal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este Journal");
        }

        JournalEntry entry = JournalEntry.builder()
                .content(request.getContent())
                .editedAt(LocalDate.now())
                .points(request.getPoints())
                .xp(request.getXp())
                .deleted(false)
                .journal(journal)
                .user(user)
                .build();

        JournalEntry saved = journalEntryRepository.save(entry);
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
        entry.setXp(request.getXp());
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
                .xp(entry.getXp())
                .userId(entry.getUser().getId())
                .journalId(entry.getJournal().getId())
                .build();
    }
}