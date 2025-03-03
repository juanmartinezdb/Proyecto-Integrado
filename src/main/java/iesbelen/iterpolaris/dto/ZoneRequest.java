package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ZoneRequest {
    private String name;
    private String description;
    private String image;
    private String color;
    private Set<String> zoneTypes;

}
