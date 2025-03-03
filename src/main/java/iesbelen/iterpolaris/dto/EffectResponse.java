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
public class EffectResponse {
    private Long id;
    private String name;
    private String logicKey;
    private String description;
    private String type;
    private Set<String> targetEntities;
}
