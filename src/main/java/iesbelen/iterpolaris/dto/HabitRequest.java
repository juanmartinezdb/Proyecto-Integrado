package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HabitRequest {
    private String name;
    private String description;
    private String image;
    private Boolean active;
    private Integer energy;
    private Integer points;
    private Integer xp;
    private String frequency; // "DAILY", "WEEKLY", "MONTHLY"
    private Long zoneId;
    private Long effectId; // Por si necesitas asociarlo a un Effect
}