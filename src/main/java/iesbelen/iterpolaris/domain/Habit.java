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
    @JoinColumn(name = "user_id")
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @ManyToMany(mappedBy = "habits")
    private Set<Project> projects = new HashSet<>();
}