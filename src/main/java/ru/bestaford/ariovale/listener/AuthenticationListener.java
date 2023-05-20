package ru.bestaford.ariovale.listener;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.*;
import ru.bestaford.ariovale.service.AuthenticationService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public final class AuthenticationListener implements Listener {

    @Inject private AuthenticationService authenticationService;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage("");
        authenticationService.update(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLocallyInitialized(PlayerLocallyInitializedEvent event) {
        Player player = event.getPlayer();
        authenticationService.update(player);
        authenticationService.process(player);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        event.setQuitMessage("");
        authenticationService.processQuit(event.getPlayer());
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAchievementAwarded(PlayerAchievementAwardedEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerAnimation(PlayerAnimationEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBlockPick(PlayerBlockPickEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBucketFill(PlayerBucketFillEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChangeArmorStand(PlayerChangeArmorStandEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChangeSkin(PlayerChangeSkinEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(PlayerChatEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (!authenticationService.isLoggedIn(event.getEntity())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerEatFood(PlayerEatFoodEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerEditBook(PlayerEditBookEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerExperienceChange(PlayerExperienceChangeEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFish(PlayerFishEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerFoodLevelChange(PlayerFoodLevelChangeEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemConsume(PlayerItemConsumeEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerServerSettingsRequest(PlayerServerSettingsRequestEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerSettingsResponded(PlayerSettingsRespondedEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerShowCredits(PlayerShowCreditsEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
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
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleGlide(PlayerToggleGlideEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSpinAttack(PlayerToggleSpinAttackEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerToggleSwim(PlayerToggleSwimEvent event) {
        if (!authenticationService.isLoggedIn(event.getPlayer())) {
            event.setCancelled();
        }
    }
}