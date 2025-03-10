package iesbelen.iterpolaris.dto;

import iesbelen.iterpolaris.domain.ChallengeLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalEntryResponse {
    private Long id;
    private String content;
    private LocalDate editedAt;
    private Integer points;
//    private Integer xp;
@Enumerated(EnumType.STRING)
private ChallengeLevel challengeLevel;
    private Long userId;
    private Long journalId;
}