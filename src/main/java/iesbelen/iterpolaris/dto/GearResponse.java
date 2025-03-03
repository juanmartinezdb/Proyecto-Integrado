package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GearResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String type;
    private Integer maxUses;
    private Integer cost;
    private Boolean consumable;
    private String rarity;
    private Long effectId;
}
