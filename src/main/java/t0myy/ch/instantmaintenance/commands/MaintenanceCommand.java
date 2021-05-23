package t0myy.ch.instantmaintenance.commands;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import t0myy.ch.instantmaintenance.InstantMaintenance;
import t0myy.ch.instantmaintenance.utils.ConfigManager;
import t0myy.ch.instantmaintenance.utils.MaintenanceManager;
import t0myy.ch.instantmaintenance.utils.MessageFile;;import java.io.File;
import java.util.ArrayList;

public class MaintenanceCommand implements CommandExecutor {

    @Override
    public boolean onCommand( CommandSender sender,  Command command, String label, String[] args) {

        ArrayList<Player> whitelistedPlayers = new ArrayList<>();

        if (!(sender instanceof Player)) {
            //Connsloe sender
            return false;
        }

        Player player = (Player) sender;

        if (player.hasPermission(ConfigManager.getCfg().getString("Permission"))) {
            if (args.length == 0) {
               player.sendMessage("§7------ §bMaintenance §7------");
               player.sendMessage("§6Commands:");
               player.sendMessage("§7 - /maintenance add <PlayerName>");
               player.sendMessage("§7 - /maintenance remove <PlayerName>");
               player.sendMessage("§7 - /maintenance toggleMaintenance <on/off>");
                player.sendMessage("§7 - /maintenance list");
                player.sendMessage("§7------ §bMaintenance §7------");
            } else {
                if (args.length == 2) {
                    OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                    File file = new File(InstantMaintenance.getInstance().getDataFolder() + "//WhitelistedPlayers/", target.getName() + ".yml");
                    if (args[0].equalsIgnoreCase("add")) {

                        if (!whitelistedPlayers.contains(player)) {
                            whitelistedPlayers.add(player);
                        }

                        if (!file.exists()) {
                            MaintenanceManager.addPlayer(target, player);
                            player.sendMessage(ConfigManager.getTranslatedMessage("Message.AddToWhitelist").replace("%target%", target.getName()));
                        } else {
                            player.sendMessage(ConfigManager.getConfigTextValue("Message.PlayerAlreadyOnWhitelist"));
                        }


                    } else if (args[0].equalsIgnoreCase("remove")) {

                        if (whitelistedPlayers.contains(player)) {
                            whitelistedPlayers.remove(player);
                        }

                        if (file.exists()) {
                            player.sendMessage(ConfigManager.getTranslatedMessage("Message.RemoveFromWhitelist").replace("%target%", target.getName()));
                            MaintenanceManager.removePlayer(player, target);
                        } else {
                            player.sendMessage(ConfigManager.getConfigTextValue("Message.PlayerDoesNotExists"));
                        }

                    } else if (args[0].equalsIgnoreCase("toggleMaintenance")) {
                        if (args[1].equalsIgnoreCase("on")) {
                            MaintenanceManager.setMaintenanceMode(player, true);
                            player.sendMessage(ConfigManager.getConfigTextValue("Message.TurnOnWhitelist"));
                        } else if (args[1].equalsIgnoreCase("off")) {
                            MaintenanceManager.setMaintenanceMode(player, false);
                            player.sendMessage(ConfigManager.getConfigTextValue("Message.TurnOffWhitelist"));
                        }
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {

                        player.sendMessage("§7------ §bMaintenance §7------");
                        player.sendMessage("§6Whitelisted Players:");
                        MaintenanceManager.listWhitelisted(player);
                        player.sendMessage("§7------ §bMaintenance §7------");
                    }
                }

            }
        } else {
            player.sendMessage(ConfigManager.getConfigTextValue("Message.noPerm"));
        }

        return false;
    }
}
