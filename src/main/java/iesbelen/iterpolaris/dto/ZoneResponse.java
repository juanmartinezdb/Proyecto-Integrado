package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ZoneResponse {
    private Long id;
    private String name;
    private String description;
    private String image;
    private String color;
    private Integer energy;
    private Integer xp;
    private Integer level;
    private Set<String> zoneTypes;
}
