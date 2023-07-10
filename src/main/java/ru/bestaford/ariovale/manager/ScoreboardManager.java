package ru.bestaford.ariovale.manager;

import cn.nukkit.Player;
import cn.nukkit.Server;
import ru.bestaford.ariovale.entity.Account;
import ru.bestaford.ariovale.scoreboard.AccountScoreboard;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;
import java.util.UUID;

@Singleton
public final class ScoreboardManager {

    @Inject private AuthenticationManager authenticationManager;

    public void updateScoreboard(Account account) {
        //TODO: Update existing scoreboard instead of creating new
        for (Map.Entry<UUID, Player> entry : Server.getInstance().getOnlinePlayers().entrySet()) {
            if (account.getUniqueId().equals(entry.getKey())) {
                Player player = entry.getValue();
                if (authenticationManager.isLoggedIn(player)) {
                    new AccountScoreboard(account).display(player);
                    break;
                }
            }
        }
    }
}