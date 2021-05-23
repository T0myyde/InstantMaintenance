package t0myy.ch.instantmaintenance.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.yaml.snakeyaml.Yaml;
import t0myy.ch.instantmaintenance.InstantMaintenance;

import java.io.File;
import java.io.IOException;

public class ConfigManager {



    private static File file = new File(InstantMaintenance.getInstance().getDataFolder(), "config.yml");
    private static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public ConfigManager() {
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                cfg.set("Permission", "InstantMaintenance.*");
                cfg.set("ToggleMaintenance", Boolean.valueOf("false"));

                cfg.createSection("KickMessage");
                cfg.set("KickMessage.line1", "&7------------------ &6YourServer &7-----------------");
                cfg.set("KickMessage.line2", "");
                cfg.set("KickMessage.line3", "&7the server ServerName is currently in &cmaintenance ");
                cfg.set("KickMessage.line4", "");
                cfg.set("KickMessage.line5", "&7------------------ &6YourServer &7-----------------");

                saveCfg();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static YamlConfiguration getCfg() {
        return cfg;
    }

    public static void saveCfg() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getConfigTextValue(String value) {
        String prefixtext = MessageFile.getMessagecfg().getString("Prefix");
        String prefix = (ChatColor.translateAlternateColorCodes('&', prefixtext));
        String message = MessageFile.getMessagecfg().getString(value);
        return ChatColor.translateAlternateColorCodes('&', prefix + message);
    }


    public static String getConfigTextValueWP(String value) {
        String message = cfg.getString(value);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String getTranslatedMessage(String value) {
        String prefixtext = MessageFile.getMessagecfg().getString("Prefix");
        String prefix = (ChatColor.translateAlternateColorCodes('&', prefixtext));
        String message = MessageFile.getMessagecfg().getString(value);
        return ChatColor.translateAlternateColorCodes('&', prefix+message);
    }
}
