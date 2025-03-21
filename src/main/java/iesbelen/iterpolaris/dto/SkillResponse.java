package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillResponse {
    private Long id;
    private String name;
    private String description;
    private String type;
    private Integer level;
    private Integer cost;
    private Integer mana;
    private String icon;
    private Long effectId;
}
