package ru.bestaford.ariovale.entity;

import cn.nukkit.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventory_items")
public class InventoryItem {

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

    @Column(name = "item_meta", nullable = false)
    private Integer itemMeta;

    @Column(name = "item_count", nullable = false)
    private Integer itemCount;

    public InventoryItem(PlayerState playerState, Integer slot, Item item) {
        this.playerState = playerState;
        this.slot = slot;
        this.itemId = item.getId();
        this.itemMeta = item.getDamage();
        this.itemCount = item.getCount();
    }

    public Item restore() {
        return Item.get(itemId, itemMeta, itemCount);
    }
}