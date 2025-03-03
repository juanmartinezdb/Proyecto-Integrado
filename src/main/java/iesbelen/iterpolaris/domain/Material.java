package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "material")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material")
    private Long id;
    private Boolean deleted;
    private String name;
    private String type; //"documento", "link", "video", lo que sea es mas que nada para asignarle un icono en el front.
    private String image; //puede que se la quite
    private String url;
    private String description;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;


    @ManyToMany(mappedBy = "materials")
    private Set<Project> projects = new HashSet<>();
}
