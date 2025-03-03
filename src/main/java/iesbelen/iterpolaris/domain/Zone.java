package iesbelen.iterpolaris.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "zone")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode( of= "id")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zone")

    private Long id;
    private Boolean deleted;
    private String name;
    private String description;
    private String image;
    private String color;

    // Gamificación
//    private Integer productivity;
    private Integer energy;
    private Integer xp;
    private Integer level;


    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "zone_types", joinColumns = @JoinColumn(name = "id_zone"))
    @Column(name = "zone_type")
    private Set<String> zoneTypes = new HashSet<>(); // "MENTAL, PHYSICAL, EMOTIONAL, SOCIAL, CREATIVE" solo se pueden tener entre 1 y 2 por Zona!



    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LogEntry> logEntries = new HashSet<>();


    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();


    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Habit> habits = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

}
