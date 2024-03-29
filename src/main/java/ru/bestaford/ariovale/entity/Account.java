package ru.bestaford.ariovale.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;
import ru.bestaford.ariovale.Ariovale;
import ru.bestaford.ariovale.manager.ScoreboardManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "accounts")
public class Account {

    public final static Pattern NAME_PATTERN = Pattern.compile("^\\p{L}{1,20}\\s+\\p{L}{1,20}$");
    public final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!\\\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~])\\S{8,}$");
    public final static int MIN_AGE = 18;
    public final static int MAX_AGE = 80;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NaturalId
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "uuid", nullable = false)
    private UUID uniqueId;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "profile_data_id", nullable = false)
    private ProfileData profileData;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "player_state_id", nullable = false)
    private PlayerState playerState;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LoginHistory> loginHistory = new ArrayList<>();

    @PostLoad
    @PostPersist
    @PostRemove
    @PostUpdate
    public void postChange() {
        Ariovale.injector.getInstance(ScoreboardManager.class).updateScoreboard(this);
    }
}