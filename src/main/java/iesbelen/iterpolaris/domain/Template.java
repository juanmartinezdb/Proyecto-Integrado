package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "template")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_template")
    private Long id;

    private Boolean deleted;

    private String name;
    private String description;
    private Integer energy;
    private Integer points;
    private String priority;
    private String cycle;
    private String category; // "task", "habit", "project"

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_user", nullable = false)
    private User user;
}

