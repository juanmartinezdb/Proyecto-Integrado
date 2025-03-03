package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "journal_entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_journal_entry")
    private Long id;
    private Boolean deleted;

    @Column(name = "edited_at")
    private LocalDate editedAt;

    @Lob //se usa lob para guardar textos largos
    private String content;

    private Integer points;
    private Integer xp;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_journal", nullable = false)
    private Journal journal;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}
