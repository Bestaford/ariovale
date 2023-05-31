package ru.bestaford.ariovale.entity;

import cn.nukkit.Player;
import cn.nukkit.PlayerFood;
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

    @Column(name = "x", nullable = false)
    private Double x;

    @Column(name = "y", nullable = false)
    private Double y;

    @Column(name = "z", nullable = false)
    private Double z;

    @Column(name = "level_name", nullable = false)
    private String levelName;

    @Column(name = "yaw", nullable = false)
    private Double yaw;

    @Column(name = "pitch", nullable = false)
    private Double pitch;

    @Column(name = "head_yaw", nullable = false)
    private Double headYaw;

    @Column(name = "health", nullable = false)
    private Float health;

    @Column(name = "max_health", nullable = false)
    private Integer maxHealth;

    @Column(name = "experience", nullable = false)
    private Integer experience;

    @Column(name = "experience_level", nullable = false)
    private Integer experienceLevel;

    @Column(name = "food_level", nullable = false)
    private Integer foodLevel;

    @Column(name = "saturation_level", nullable = false)
    private Float saturationLevel;

    public PlayerState(Player player) {
        save(player);
    }

    public void save(Player player) {
        this.x = player.getX();
        this.y = player.getY();
        this.z = player.getZ();
        this.levelName = player.getLevelName();
        this.yaw = player.getYaw();
        this.pitch = player.getPitch();
        this.headYaw = player.getHeadYaw();
        this.health = player.getHealth();
        this.maxHealth = player.getMaxHealth();
        this.experience = player.getExperience();
        this.experienceLevel = player.getExperienceLevel();
        PlayerFood playerFood = player.getFoodData();
        this.foodLevel = playerFood.getLevel();
        this.saturationLevel = playerFood.getFoodSaturationLevel();
    }

    public void restore(Player player) {
        player.teleport(new Location(x, y, z, yaw, pitch, headYaw, Server.getInstance().getLevelByName(levelName)));
        player.setMaxHealth(maxHealth);
        player.setHealth(health);
        player.setExperience(experience, experienceLevel);
        PlayerFood playerFood = player.getFoodData();
        playerFood.setLevel(foodLevel, saturationLevel);
    }
}