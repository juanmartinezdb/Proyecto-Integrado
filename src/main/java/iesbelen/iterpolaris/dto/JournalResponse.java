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
public class JournalResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String type;
    private LocalDateTime createdAt;
    private Long userId;
    private LocalDate lastEntryDate; // Nueva propiedad para rastrear la última entrada
    private Integer streak; // Racha de días consecutivos escribiendo
    private Set<Long> entriesIds;
}