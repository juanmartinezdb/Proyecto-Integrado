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
public class HabitResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private Boolean active;
    private Integer energy;
    private Integer points;
//    private Integer xp;
@Enumerated(EnumType.STRING)
private ChallengeLevel challengeLevel;
    private String frequency;
    private Integer streak;
    private Integer totalCheck;
    private Long zoneId;
    private Long userId;
    private Long effectId;
}