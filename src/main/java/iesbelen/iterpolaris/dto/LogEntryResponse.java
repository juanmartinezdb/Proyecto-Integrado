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
public class LogEntryResponse {
    private Long id;
    private Integer points;
    private String type;
    private Long itemId;
    private LocalDate endTimestamp;
    private Integer energy;
    private Long userId;
    private Long zoneId;
}
