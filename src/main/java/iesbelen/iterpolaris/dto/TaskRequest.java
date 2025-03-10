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
public class TaskRequest {
    private String name;
    private String description;
    private String image;
    private String status;          // "PENDING", "IN_PROGRESS", "COMPLETED", "CANCELLED"
    private Integer energy;
    private Integer points;
    @Enumerated(EnumType.STRING)
    private ChallengeLevel challengeLevel;
    private String priority;        // "HIGH", "MEDIUM", "LOW"
    private String cycle;           // "NONE", "DAILY", "WEEKLY", "MONTHLY"
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean active;
    private Long projectId;
    private Long parentTaskId;
}