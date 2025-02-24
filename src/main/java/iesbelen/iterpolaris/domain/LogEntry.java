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
@EqualsAndHashCode( of = "id")
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_log")
    private Long id;


    private Integer points;

    private String type; // "TASK", "HABIT", "JOURNAL_ENTRY", "PROJECT" IMPORTANTE!!!!!!!!!

    @Column(name = "item_id") //del objeto que registra
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

    //    @Column(name = "productivity_percentage")
//    private Integer productivityPercentage;

//    private String otros; //se que me falta meter algun dato mas al registro seguro
}
