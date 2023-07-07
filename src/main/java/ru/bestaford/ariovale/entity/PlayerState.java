package ru.bestaford.ariovale.entity;

import cn.nukkit.Player;
import cn.nukkit.PlayerFood;
import cn.nukkit.Server;
import cn.nukkit.inventory.PlayerInventory;
import cn.nukkit.inventory.PlayerOffhandInventory;
import cn.nukkit.item.Item;
import cn.nukkit.level.Location;
import cn.nukkit.potion.Effect;
import com.google.common.base.Preconditions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Column(name = "held_item_index", nullable = false)
    private Integer heldItemIndex;

    @OneToMany(mappedBy = "playerState", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<InventoryItem> persistedItems = new ArrayList<>();

    @Transient
    private Map<Integer, Item> restoredItems = new HashMap<>();

    @Transient
    private Map<Integer, Item> restoredOffhandItems = new HashMap<>();

    @OneToMany(mappedBy = "playerState", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlayerEffect> persistedEffects = new ArrayList<>();

    @Transient
    private List<Effect> restoredEffects = new ArrayList<>();

    public PlayerState(Player player) {
        Preconditions.checkArgument(player != null);
        save(player);
    }

    public void save(Player player) {
        Preconditions.checkArgument(player != null);
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
        this.persistedItems.clear();
        PlayerInventory playerInventory = player.getInventory();
        for (Map.Entry<Integer, Item> entry : playerInventory.getContents().entrySet()) {
            this.persistedItems.add(new InventoryItem(this, entry.getKey(), entry.getValue()));
        }
        PlayerOffhandInventory playerOffhandInventory = player.getOffhandInventory();
        for (Item item : playerOffhandInventory.getContents().values()) {
            this.persistedItems.add(new InventoryItem(this, InventoryItem.OFFHAND_SLOT, item));
        }
        this.heldItemIndex = playerInventory.getHeldItemIndex();
        this.persistedEffects.clear();
        for (Effect effect : player.getEffects().values()) {
            this.persistedEffects.add(new PlayerEffect(this, effect));
        }
    }

    @PostLoad
    public void postLoad() {
        for (InventoryItem item : persistedItems) {
            Integer slot = item.getSlot();
            Item restoredItem = item.restore();
            if (slot == InventoryItem.OFFHAND_SLOT) {
                restoredOffhandItems.put(0, restoredItem);
            } else {
                restoredItems.put(slot, restoredItem);
            }
        }
        for (PlayerEffect effect : persistedEffects) {
            restoredEffects.add(effect.restore());
        }
    }

    public void restore(Player player) {
        player.teleport(new Location(x, y, z, yaw, pitch, headYaw, Server.getInstance().getLevelByName(levelName)));
        player.setMaxHealth(maxHealth);
        player.setHealth(health);
        player.setExperience(experience, experienceLevel);

        PlayerFood playerFood = player.getFoodData();
        playerFood.setLevel(foodLevel, saturationLevel);

        PlayerInventory playerInventory = player.getInventory();
        playerInventory.clearAll();
        playerInventory.setContents(restoredItems);
        playerInventory.sendContents(player);
        playerInventory.setHeldItemIndex(heldItemIndex);

        PlayerOffhandInventory playerOffhandInventory = player.getOffhandInventory();
        playerOffhandInventory.clearAll();
        playerOffhandInventory.setContents(restoredOffhandItems);
        playerOffhandInventory.sendContents(player);

        for (Effect effect : restoredEffects) {
            player.addEffect(effect);
        }
    }
}
