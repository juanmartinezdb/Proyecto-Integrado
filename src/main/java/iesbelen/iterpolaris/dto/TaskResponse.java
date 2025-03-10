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
public class TaskResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String status;
    private Integer energy;
    private Integer points;
    @Enumerated(EnumType.STRING)
    private ChallengeLevel challengeLevel;
    private String priority;
    private String cycle;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;
    private Long userId;
    private Long projectId;
    private Long parentTaskId;
    private Set<Long> subTasksIds;
}