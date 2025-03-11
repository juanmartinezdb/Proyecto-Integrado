package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TemplateRequest {
    private String name;
    private String description;
    private Integer energy;
    private Integer points;
    private String priority;
    private String cycle;
    private String category; // "task", "habit", "project"
}
