package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "effect")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "logicKey")
public class Effect {     //ESTA ENTIDAD NO SE ASOCIA CON USUARIO! ENTIDAD GLOBAL!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_effect")
    private Long id;

    private String name;
    private String logicKey;
    private String description;

    //con el tipo determino a que areas se va a afectar dependiendo de su dupla
    private String type; // mental, physical, emotional, social, creative, all


    //por ejemplo si el efecto afecta a proyectos, habitos, tareas etc... se apunta en ese array.
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "effect_target_entities", joinColumns = @JoinColumn(name = "id_effect"))
    @Column(name = "entity_name")
    private Set<String> targetEntities = new HashSet<>();


    @OneToMany(mappedBy = "effect")
    private Set<Skill> skills = new HashSet<>();

    @OneToMany(mappedBy = "effect")
    private Set<Gear> gears = new HashSet<>();


}
