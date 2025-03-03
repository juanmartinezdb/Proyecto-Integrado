package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectResponse {
    private Long id;
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime createdAt;
    private Integer points;
    private Integer xp;
    private String image;
    private String icon;
    private String color;
    private String status;
    private String priority;
    private Long zoneId;
    private Set<Long> materialsIds;
    private Set<Long> habitsIds;
    private Long userId;
}
