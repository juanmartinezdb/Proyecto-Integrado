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
public class EffectRequest {
    private String name;
    private String logicKey;
    private String description;
    private String type; // mental, physical, emotional, social, creative, all
    private Set<String> targetEntities; // Por ejemplo: {"task", "habit", "project"}
}
