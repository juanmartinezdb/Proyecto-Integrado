package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "habit")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_habit")
    private Long id;

    private Boolean deleted;

    private String name;
    private String description;
    private String image;

    private Boolean active;      // si está activo o pausa
    private Integer energy;
    private Integer points;
    private Integer xp;
    private String frequency;    //"DAILY", "WEEKLY", "MONTHLY"
    private Integer streak;
    @Column(name = "total_check")
    private Integer totalCheck;    // Total veces completado


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_effect", nullable = true)
    private Effect effect;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zone")
    private Zone zone;

    @ManyToMany(mappedBy = "habits")
    private Set<Project> projects = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}