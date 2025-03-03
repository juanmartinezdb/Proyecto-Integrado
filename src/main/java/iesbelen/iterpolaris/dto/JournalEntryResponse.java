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
public class JournalEntryResponse {
    private Long id;
    private String content;
    private LocalDate editedAt;
    private Integer points;
    private Integer xp;
    private Long userId;
    private Long journalId;
}