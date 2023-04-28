package ru.bestaford.ariovale.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import ru.bestaford.ariovale.util.PermissionLevel;
import ru.bestaford.ariovale.util.Sex;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.regex.Pattern;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "accounts")
public class Account {

    public final static Pattern NAME_PATTERN = Pattern.compile("^\\p{L}{1,20}\\s+\\p{L}{1,20}$");
    public final static Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!\\\"#$%&'()*+,\\-./:;<=>?@\\[\\\\\\]^_`{|}~])\\S{8,}$");
    public final static int MIN_AGE = 18;
    public final static int MAX_AGE = 80;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "permission_level", nullable = false)
    private PermissionLevel permissionLevel;

    @Column(name = "registration_date", nullable = false)
    private LocalDateTime registrationDate;

    @Column(name = "login_date", nullable = false)
    private LocalDateTime loginDate;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "uuid", nullable = false)
    private UUID uniqueId;

    @Column(name = "xuid")
    private String xboxUserId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sex", nullable = false)
    private Sex sex;

    @Column(name = "age", nullable = false)
    private int age;
}