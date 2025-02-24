package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "skill")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Skill {     //ESTA ENTIDAD NO SE ASOCIA CON USUARIO! ENTIDAD GLOBAL!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_skill")
    private Long id;

    private String name;
    private String description;
    private String type;   // mental, physical, emotional, social, creative, all
    private Integer level; //a qie nivel estará disponible
    private Integer cost; //para comprar con experiencia
    private Integer mana; //para usarla
    private String icon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "effect_id", nullable = false)
    private Effect effect;
}
