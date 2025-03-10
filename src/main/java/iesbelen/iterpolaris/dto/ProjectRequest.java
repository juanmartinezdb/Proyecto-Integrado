package iesbelen.iterpolaris.dto;

import iesbelen.iterpolaris.domain.ChallengeLevel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectRequest {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String image;
    private String icon;
    private String color;
    private String status;
    private String priority;
    private Long zoneId;
    private Integer points; // ✅ Se define al crear el proyecto
//    private Integer xp; // ✅ Se define al crear el proyecto
@Enumerated(EnumType.STRING)
private ChallengeLevel challengeLevel;
    private Set<Long> materialsIds;
    private Set<Long> habitsIds;
}
