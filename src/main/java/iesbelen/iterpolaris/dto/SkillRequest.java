package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillRequest {
    private String name;
    private String description;
    private String type;   // mental, physical, emotional, social, creative, all
    private Integer level;
    private Integer cost;
    private Integer mana;
    private String icon;
    private Long effectId; // Efecto asociado
}
