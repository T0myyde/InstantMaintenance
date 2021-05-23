package t0myy.ch.instantmaintenance.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import t0myy.ch.instantmaintenance.InstantMaintenance;

import java.io.File;
import java.io.IOException;

public class FileManager {

    public FileManager() {
        load();
    }

    private void load() {
        File dir = new File(InstantMaintenance.getInstance().getDataFolder() + "//WhitelistedPlayers/");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

}
