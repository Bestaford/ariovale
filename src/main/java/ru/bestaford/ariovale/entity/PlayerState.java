package ru.bestaford.ariovale.entity;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.level.Location;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player_state")
public class PlayerState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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

    public PlayerState(Player player) {
        this.x = player.x;
        this.y = player.y;
        this.z = player.z;
        this.levelName = player.level.getName();
        this.yaw = player.yaw;
        this.pitch = player.pitch;
        this.headYaw = player.headYaw;
    }

    public Location getLocation() {
        return new Location(x, y, z, yaw, pitch, headYaw, Server.getInstance().getLevelByName(levelName));
    }
}