package iesbelen.iterpolaris.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
 @Table(name ="project")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode (of = "id")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_project")

    private Long id;

    private String name;
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private Integer points;
    private Integer xp;

    private String image;
    private String icon;
    private String color;

    private String status; // "ACTIVE", "COMPLETED", "ARCHIVED"

    private String priority; // "HIGH", "MEDIUM", "LOW" (puede ser útil para ordenar o filtrar)


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_zone")
    private Zone zone;


    @ManyToMany
    @JoinTable(
            name = "project_materials",
            joinColumns = @JoinColumn(name = "id_project"),
            inverseJoinColumns = @JoinColumn(name = "id_material")
    )
    private Set<Material> materials = new HashSet<>();


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "journal_id", unique = true)
    private Journal journal;


    @ManyToMany
    @JoinTable(
            name = "project_habits",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "habit_id")
    )
    private Set<Habit> habits = new HashSet<>();


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


}
