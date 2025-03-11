package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "achievement")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Achievement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_achievement")
    private Long id;

    private String name;
    private String description;
    private String type; // "streak", "xp", "tasks_completed"
    private Integer threshold; // Ejemplo: 10 tareas completadas para desbloquear
    private Boolean unlocked;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
