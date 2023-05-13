package ru.bestaford.ariovale.entity;

import cn.nukkit.Player;
import cn.nukkit.utils.LoginChainData;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class CustomLoginChainData {

    private String username;

    private UUID clientUUID;

    private String xuid;

    private String identityPublicKey;

    private long clientId;

    private String serverAddress;

    private String deviceModel;

    private int deviceOS;

    private String deviceId;

    private String gameVersion;

    private int guiScale;

    private String languageCode;

    private int currentInputMode;

    private int defaultInputMode;

    public CustomLoginChainData(Player player) {
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