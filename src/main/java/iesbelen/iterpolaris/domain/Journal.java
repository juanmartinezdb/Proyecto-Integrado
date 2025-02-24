package iesbelen.iterpolaris.domain;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "journal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(of = "id")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_journal")
    private Long id;

    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String description;
    private String image;
    private String type; // "mental", "physical", "emotional", "creative", "social"


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;


    @OneToOne(mappedBy = "journal", fetch = FetchType.LAZY)
    private Project project;


    @OneToMany(mappedBy = "journal", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JournalEntry> entries = new HashSet<>();
}
