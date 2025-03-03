package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JournalRequest {
    private String name;
    private String description;
    private String image;
    private String type; // "mental", "physical", "emotional", "creative", "social"
}
