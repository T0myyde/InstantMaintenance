package t0myy.ch.instantmaintenance.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import t0myy.ch.instantmaintenance.utils.ConfigManager;
import t0myy.ch.instantmaintenance.utils.MaintenanceManager;
import t0myy.ch.instantmaintenance.utils.MessageFile;

import java.util.List;

public class LoginEvent implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();

        Boolean maintenance = ConfigManager.getCfg().getBoolean("ToggleMaintenance");

        String line1 = ConfigManager.getConfigTextValueWP("KickMessage.line1");
        String line2 = ConfigManager.getConfigTextValueWP("KickMessage.line2");
        String line3 = ConfigManager.getConfigTextValueWP("KickMessage.line3");
        String line4 = ConfigManager.getConfigTextValueWP("KickMessage.line4");
        String line5 = ConfigManager.getConfigTextValueWP("KickMessage.line5");

        if (maintenance == true) {
            if (!player.hasPermission(ConfigManager.getCfg().getString("Permission"))) {
                if (MaintenanceManager.checkPlayer(player) == true)return;
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, line1 + "\n" + line2 + "\n" + line3 + "\n" + line4 + "\n" +line5);

            }
        }
    }
}
