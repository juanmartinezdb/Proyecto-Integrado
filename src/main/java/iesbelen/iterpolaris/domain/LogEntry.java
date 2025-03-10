package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "log_entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;
    private Boolean deleted;

    @Enumerated(EnumType.STRING)
    private ChallengeLevel challengeLevel; // Nuevo campo para registrar la dificultad de la actividad

    private String type; // "TASK", "HABIT", "JOURNAL_ENTRY", "PROJECT"

    @Column(name = "item_id") // ID del objeto registrado
    private Long itemId;

    @Column(name = "end_timestamp")
    private LocalDate endTimestamp;

    private Integer energy;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_zone", nullable = false)
    private Zone zone;
}
