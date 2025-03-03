package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GearRequest {
    private String name;
    private String description;
    private String image;
    private String type; // mental, physical, emotional, social, creative, all
    private Integer maxUses;
    private Integer cost;
    private Boolean consumable;
    private String rarity;
    private Long effectId;
}
