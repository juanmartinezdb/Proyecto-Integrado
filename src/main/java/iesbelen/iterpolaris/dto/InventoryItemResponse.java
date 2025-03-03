package iesbelen.iterpolaris.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InventoryItemResponse {
    private Long id;
    private Integer remainingUses;
    private LocalDateTime acquiredAt;
    private Long gearId;
    private String gearName;  // opcional, para mostrar info
    private Long userId;
}
