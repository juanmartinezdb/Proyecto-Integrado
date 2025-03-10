package iesbelen.iterpolaris.dto;

import iesbelen.iterpolaris.domain.ChallengeLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogEntryRequest {
    private ChallengeLevel challengeLevel; // Se usa ChallengeLevel en lugar de points
    private String type; // "TASK", "HABIT", "JOURNAL_ENTRY", "PROJECT"
    private Long itemId;
    private LocalDate endTimestamp;
    private Integer energy;
    private Long zoneId;
}
