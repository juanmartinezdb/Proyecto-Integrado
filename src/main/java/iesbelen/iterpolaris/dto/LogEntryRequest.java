package iesbelen.iterpolaris.dto;

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
    private Integer points;
    private String type; // "TASK", "HABIT", "JOURNAL_ENTRY", "PROJECT"
    private Long itemId;
    private LocalDate endTimestamp;
    private Integer energy;
    private Long zoneId;
}
