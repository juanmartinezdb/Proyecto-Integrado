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
public class JournalRequest {
    private String name;
    private String description;
    private String image;
    private String type; // "mental", "physical", "emotional", "creative", "social"
    private LocalDate lastEntryDate; // Nueva propiedad para rastrear la última entrada
    private Integer streak; // Racha de días consecutivos escribiendo
}
