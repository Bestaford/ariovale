package ru.bestaford.ariovale.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.*;
import ru.bestaford.ariovale.manager.AuthenticationManager;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthenticationListener implements Listener {

    @Inject private AuthenticationManager authenticationManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        event.setJoinMessage("");
        authenticationManager.reset(player);
        authenticationManager.update(player);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event) {
        Player player = event.getPlayer();
        authenticationManager.update(player);
        authenticationManager.process(player);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        authenticationManager.processQuit(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAchievementAwarded(PlayerAchievementAwardedEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAnimation(PlayerAnimationEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBlockPick(PlayerBlockPickEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChangeArmorStand(PlayerChangeArmorStandEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChangeSkin(PlayerChangeSkinEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(PlayerChatEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!authenticationManager.isLoggedIn(event.getEntity())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerEatFood(PlayerEatFoodEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerEditBook(PlayerEditBookEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExperienceChange(PlayerExperienceChangeEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFish(PlayerFishEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFoodLevelChange(PlayerFoodLevelChangeEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerServerSettingsRequest(PlayerServerSettingsRequestEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerSettingsResponded(PlayerSettingsRespondedEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerShowCredits(PlayerShowCreditsEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (!(event.getCause() == PlayerTeleportEvent.TeleportCause.PLUGIN || event.getCause() == PlayerTeleportEvent.TeleportCause.PLAYER_SPAWN)) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleFlight(PlayerToggleFlightEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleGlide(PlayerToggleGlideEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSpinAttack(PlayerToggleSpinAttackEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSwim(PlayerToggleSwimEvent event) {
        if (!authenticationManager.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }
}