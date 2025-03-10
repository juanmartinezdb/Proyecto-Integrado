package iesbelen.iterpolaris.dto;

import iesbelen.iterpolaris.domain.ChallengeLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalEntryRequest {
    private String content;
    private Integer points;
//    private Integer xp;
@Enumerated(EnumType.STRING)
private ChallengeLevel challengeLevel;
}