package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private Long id;

    private String name;
    private String description;
    private String image;

    private String status;          // "PENDING", "IN_PROGRESS", "COMPLETED", "CANCELLED"
    private Integer energy;
    private Integer points;
    private Integer xp;
    private String priority;        // "HIGH", "MEDIUM", "LOW"
    private String cycle;           // "NONE", "DAILY", "WEEKLY", "MONTHLY"

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;


    private Boolean active;               // Activa o pausada

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_project")
    private Project project;

    //REFLEXIVA!
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_parent_task") //se entiende que es opcional
    private Task parentTask;

    @OneToMany(mappedBy = "parentTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> subTasks = new HashSet<>();
}
