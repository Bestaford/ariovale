package ru.bestaford.ariovale.entity;

import cn.nukkit.potion.Effect;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "player_effects")
public class PlayerEffect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_state_id", nullable = false)
    private PlayerState playerState;

    @Column(name = "effect_id", nullable = false)
    private Integer effectId;

    @Column(name = "amplifier", nullable = false)
    private Integer amplifier;

    @Column(name = "duration", nullable = false)
    private Integer duration;

    @Column(name = "visible", nullable = false)
    private Boolean visible;

    @Column(name = "ambient", nullable = false)
    private Boolean ambient;

    public PlayerEffect(PlayerState playerState, Effect effect) {
        this.playerState = playerState;
        this.effectId = effect.getId();
        this.amplifier = effect.getAmplifier();
        this.duration = effect.getDuration();
        this.visible = effect.isVisible();
        this.ambient = effect.isAmbient();
    }

    public Effect restore() {
        Effect effect = Effect.getEffect(effectId);
        effect.setAmplifier(amplifier);
        effect.setDuration(duration);
        effect.setVisible(visible);
        effect.setAmbient(ambient);
        return effect;
    }
}