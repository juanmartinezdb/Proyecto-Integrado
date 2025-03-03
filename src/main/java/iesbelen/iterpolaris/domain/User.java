package iesbelen.iterpolaris.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.java.Log;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode( of = "username")
public class User implements UserDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    private Boolean deleted = false;
    @NaturalId
    @Column (nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    //meter validators de email y esas cosas
    private String email;

    @Embedded
    private PersonalData personalData;

    @Column(nullable = false)
    private String password;

    private String role;                //"ADMIN", "USER"

    private LocalDateTime createdAt;


    // Gamificación
//    private Integer productivity;
//
//    @Column(name = "productivity_index")
//    private Integer productivityIndex; //calcular
    private Integer energy;
    private Integer points;
    private Integer xp;
    private Integer skillPoints;
    private Integer level;
    private Integer mana;
    private Integer streak; //racha
//    private String honorTitle;

    // Temas
    private String themeColor;
    private String avatarImage;
    private String backgroundImage;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InventoryItem> inventoryItems = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Habit> habits = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Journal> journals = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<JournalEntry> journalEntries = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<LogEntry> logEntries = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Material> materials = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Project> projects = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> tasks = new HashSet<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Zone> zones = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_skills",
            joinColumns = @JoinColumn(name = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_skill")
    )
    private Set<Skill> skills = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }
    //posible expansión STYLES, STATISTICS, NOTIFICATIONS, ACHIEVEMENTS, REGISTRO DE ACCESOS, LA TIENDA PARA PLANTILLAS Y CLANES.

    //    private String accountStatus;       //"ACTIVE", "BLOCKED", "INACTIVE",... (en vistas a la mejorada)
//    private LocalDateTime lastLogin;
//    private LocalDateTime lastSession;
//    private LocalDateTime modifiedAt;

}
