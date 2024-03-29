package ru.bestaford.ariovale.entity;

import cn.nukkit.Player;
import cn.nukkit.utils.LoginChainData;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
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

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "client_uuid", nullable = false)
    private UUID clientUUID;

    @Column(name = "xuid")
    private String xuid;

    @Column(name = "identity_public_key", nullable = false)
    private String identityPublicKey;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "server_address", nullable = false)
    private String serverAddress;

    @Column(name = "device_model", nullable = false)
    private String deviceModel;

    @Column(name = "device_os", nullable = false)
    private Integer deviceOS;

    @Column(name = "device_id", nullable = false)
    private String deviceId;

    @Column(name = "game_version", nullable = false)
    private String gameVersion;

    @Column(name = "gui_scale", nullable = false)
    private Integer guiScale;

    @Column(name = "language_code", nullable = false)
    private String languageCode;

    @Column(name = "current_input_mode", nullable = false)
    private Integer currentInputMode;

    @Column(name = "default_input_mode", nullable = false)
    private Integer defaultInputMode;

    public LoginHistory(Player player, Account account) {
        this.account = account;
        this.datetime = LocalDateTime.now();
        this.address = player.getAddress();
        this.uniqueId = player.getUniqueId();
        LoginChainData loginChainData = player.getLoginChainData();
        this.username = loginChainData.getUsername();
        this.clientUUID = loginChainData.getClientUUID();
        this.xuid = loginChainData.getXUID();
        this.identityPublicKey = loginChainData.getIdentityPublicKey();
        this.clientId = loginChainData.getClientId();
        this.serverAddress = loginChainData.getServerAddress();
        this.deviceModel = loginChainData.getDeviceModel();
        this.deviceOS = loginChainData.getDeviceOS();
        this.deviceId = loginChainData.getDeviceId();
        this.gameVersion = loginChainData.getGameVersion();
        this.guiScale = loginChainData.getGuiScale();
        this.languageCode = loginChainData.getLanguageCode();
        this.currentInputMode = loginChainData.getCurrentInputMode();
        this.defaultInputMode = loginChainData.getDefaultInputMode();
    }
}