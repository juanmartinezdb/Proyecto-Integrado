package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String type;
    private LocalDateTime createdAt;
    private Long userId;
    private Set<Long> entriesIds;
}