package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "gear")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Gear {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gear")
    private Long id;

    private String name;
    private String description;
    private String image;
    private String type; // mental, physical, emotional, social, creative, all

    @Column(name = "max_uses")
    private Integer maxUses;
    private Integer cost;           //cuantos puntos cuesta comprarlo
    private Boolean consumable;
    private String rarity;         // para darle flavor y usar unos colores u otros

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "effect_id", nullable = false)
    private Effect effect;
}