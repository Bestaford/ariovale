package ru.bestaford.ariovale.entity;

import cn.nukkit.Player;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "login_history")
public class LoginHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "uuid", nullable = false)
    private UUID uniqueId;

    @Column(name = "xuid")
    private String xboxUserId;

    public LoginHistory(Player player, Account account) {
        this.account = account;
        this.datetime = LocalDateTime.now();
        this.address = player.getAddress();
        this.uniqueId = player.getUniqueId();
        this.xboxUserId = player.getLoginChainData().getXUID();
    }
}
