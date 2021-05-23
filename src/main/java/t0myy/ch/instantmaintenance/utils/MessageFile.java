package t0myy.ch.instantmaintenance.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import t0myy.ch.instantmaintenance.InstantMaintenance;

import java.io.File;
import java.io.IOException;

public class MessageFile {

    private static File Messagefile = new File(InstantMaintenance.getInstance().getDataFolder(), "message.yml");
    private static YamlConfiguration Messagecfg = YamlConfiguration.loadConfiguration(Messagefile);

    public static void createMessageFile() {
        if (!Messagefile.exists()) {
            try {
                Messagefile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Messagecfg.set("Prefix", "&7[&6YourServer&7] ");
                Messagecfg.createSection("Message");
                Messagecfg.set("Message.AddToWhitelist", "&7You have added &b%target% &7to the Whitelist");
                Messagecfg.set("Message.RemoveFromWhitelist", "&7You have removed &b%target% &7from to the Whitelist");
                Messagecfg.set("Message.TurnOnWhitelist", "&7You turned &aon &7the Maintenance");
                Messagecfg.set("Message.TurnOffWhitelist", "&7You turned &coff &7the Maintenance");
                Messagecfg.set("Message.noPerm", "&cYou do not have the Permission for This Command");
                Messagecfg.set("Message.PlayerDoesNotExists", "&cThis player doesent exists");
                Messagecfg.set("Message.PlayerAlreadyOnWhitelist", "&7This player is alredy whitelisted");
                Messagecfg.set("Message.WhitelistAlreadyOn", "&cthe Maintenance is Already on");
                Messagecfg.set("Message.WhitelistAlreadyOff", "&cthe Maintenance is Already off");
                Messagecfg.save(Messagefile);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static YamlConfiguration getMessagecfg() {
        return Messagecfg;
    }
}