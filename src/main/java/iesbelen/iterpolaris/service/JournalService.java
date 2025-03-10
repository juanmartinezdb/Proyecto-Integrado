package iesbelen.iterpolaris.service;

import iesbelen.iterpolaris.domain.Journal;
import iesbelen.iterpolaris.domain.User;
import iesbelen.iterpolaris.dto.JournalRequest;
import iesbelen.iterpolaris.dto.JournalResponse;
import iesbelen.iterpolaris.repository.JournalRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalService {

    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public JournalResponse createJournal(User user, JournalRequest request) {
        Journal journal = Journal.builder()
                .name(request.getName())
                .description(request.getDescription())
                .image(request.getImage())
                .type(request.getType())
                .createdAt(LocalDateTime.now())
                .streak(0) // Inicializamos la racha en 0
                .lastEntryDate(null) // Aún no tiene entradas
                .deleted(false)
                .user(user)
                .build();

        Journal saved = journalRepository.save(journal);
        return mapToResponse(saved);
    }

    public List<JournalResponse> getAllJournals(User user) {
        return journalRepository.findByUserAndDeletedFalse(user)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public JournalResponse getJournalById(User user, Long journalId) {
        Journal journal = journalRepository.findByIdAndDeletedFalse(journalId)
                .orElseThrow(() -> new RuntimeException("Journal no encontrado"));
        if (!journal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este Journal");
        }
        return mapToResponse(journal);
    }

    public JournalResponse updateJournal(User user, Long journalId, JournalRequest request) {
        Journal journal = journalRepository.findByIdAndDeletedFalse(journalId)
                .orElseThrow(() -> new RuntimeException("Journal no encontrado"));
        if (!journal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este Journal");
        }

        journal.setName(request.getName());
        journal.setDescription(request.getDescription());
        journal.setImage(request.getImage());
        journal.setType(request.getType());

        Journal updated = journalRepository.save(journal);
        return mapToResponse(updated);
    }

    public void deleteJournal(User user, Long journalId) {
        Journal journal = journalRepository.findByIdAndDeletedFalse(journalId)
                .orElseThrow(() -> new RuntimeException("Journal no encontrado"));
        if (!journal.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("No tienes acceso a este Journal");
        }

        journal.setDeleted(true);
        journalRepository.save(journal);
    }

    private JournalResponse mapToResponse(Journal journal) {
        return JournalResponse.builder()
                .id(journal.getId())
                .name(journal.getName())
                .description(journal.getDescription())
                .image(journal.getImage())
                .type(journal.getType())
                .createdAt(journal.getCreatedAt())
                .streak(journal.getStreak()) // Agregado para mostrar la racha
                .lastEntryDate(journal.getLastEntryDate()) // Agregado para mostrar la última fecha de entrada
                .userId(journal.getUser().getId())
                .entriesIds(journal.getEntries().stream().map(e -> e.getId()).collect(Collectors.toSet()))
                .build();
    }
}
