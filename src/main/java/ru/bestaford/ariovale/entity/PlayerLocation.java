package ru.bestaford.ariovale.entity;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Location;
import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Embeddable
public class PlayerLocation {

    @Column(name = "x")
    private Double x;

    @Column(name = "y")
    private Double y;

    @Column(name = "z")
    private Double z;

    @Column(name = "level_name")
    private String levelName;

    @Column(name = "yaw")
    private Double yaw;

    @Column(name = "pitch")
    private Double pitch;

    @Column(name = "head_yaw")
    private Double headYaw;

    public PlayerLocation(Player player) {
        this.x = player.x;
        this.y = player.y;
        this.z = player.z;
        this.levelName = player.level.getName();
        this.yaw = player.yaw;
        this.pitch = player.pitch;
        this.headYaw = player.headYaw;
    }

    public Vector3 asVector3() {
        return new Vector3(x, y, z);
    }

    public Position asPosition() {
        return new Position(x, y, z, Server.getInstance().getLevelByName(levelName));
    }

    public Location asLocation() {
        return new Location(x, y, z, yaw, pitch, headYaw, Server.getInstance().getLevelByName(levelName));
    }
}