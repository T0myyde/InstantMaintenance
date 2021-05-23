package t0myy.ch.instantmaintenance.utils;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import t0myy.ch.instantmaintenance.InstantMaintenance;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Iterator;

public class MaintenanceManager {

    private static YamlConfiguration cfg = ConfigManager.getCfg();

    public static void setMaintenanceMode(Player player, boolean maintenanceMode) {
       cfg.set("ToggleMaintenance", maintenanceMode);
       System.out.println("the player "+player.getName()+" has the status of maintenance work on " + maintenanceMode);

       ConfigManager.saveCfg();
    }

    public static void addPlayer(OfflinePlayer target, Player player) {
        File file = new File(InstantMaintenance.getInstance().getDataFolder() + "//WhitelistedPlayers/", target.getName() + ".yml");
        YamlConfiguration playerCfg = YamlConfiguration.loadConfiguration(file);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            playerCfg.set("PlayerName", target.getName());
            playerCfg.set("UUID", target.getUniqueId().toString());
            playerCfg.set("WhitelistedFrom", player.getName());
            playerCfg.set("IsPlayerWhitelisted", Boolean.valueOf("true"));

            System.out.println("The Player " + player.getName() + " added the Player " + target.getName() + " to the Whitelist");
            try {
                playerCfg.save(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void removePlayer(Player player, OfflinePlayer target) {
        File file = new File(InstantMaintenance.getInstance().getDataFolder() + "//WhitelistedPlayers/", target.getName() + ".yml");
        YamlConfiguration playerCfg = YamlConfiguration.loadConfiguration(file);

        if (file.exists()) {
            file.delete();
        }


    }

    public static boolean checkPlayer(Player player) {
        boolean existPlayer = false;
        File file = new File(InstantMaintenance.getInstance().getDataFolder() + "//WhitelistedPlayers/", player.getName() + ".yml");
        YamlConfiguration playerCfg = YamlConfiguration.loadConfiguration(file);
        if (file.exists()) {
            existPlayer = true;
        }

        return existPlayer;
    }

    public static void listWhitelisted(Player player) {
        File directory = new File(InstantMaintenance.getInstance().getDataFolder() + "//WhitelistedPlayers/");
            File[] listOfFiles = directory.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {
                if (listOfFiles[i].isFile()) {
                    player.sendMessage("§7- " + listOfFiles[i].getName());
                }
            }
    }
}
