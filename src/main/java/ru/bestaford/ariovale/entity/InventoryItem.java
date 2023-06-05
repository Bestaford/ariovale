package ru.bestaford.ariovale.entity;

import cn.nukkit.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventory_items")
public class InventoryItem {

    public static int OFFHAND_SLOT = -1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_state_id", nullable = false)
    private PlayerState playerState;

    @Column(name = "slot", nullable = false)
    private Integer slot;

    @Column(name = "item_id", nullable = false)
    private Integer itemId;

    @Column(name = "meta", nullable = false)
    private Integer meta;

    @Column(name = "count", nullable = false)
    private Integer count;

    public InventoryItem(PlayerState playerState, Integer slot, Item item) {
        this.playerState = Objects.requireNonNull(playerState);
        this.slot = Objects.requireNonNull(slot);
        Objects.requireNonNull(item);
        this.itemId = item.getId();
        this.meta = item.getDamage();
        this.count = item.getCount();
    }

    public Item restore() {
        return Item.get(itemId, meta, count);
    }
}