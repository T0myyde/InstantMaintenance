package t0myy.ch.instantmaintenance;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import t0myy.ch.instantmaintenance.commands.MaintenanceCommand;
import t0myy.ch.instantmaintenance.events.LoginEvent;
import t0myy.ch.instantmaintenance.utils.ConfigManager;
import t0myy.ch.instantmaintenance.utils.FileManager;
import t0myy.ch.instantmaintenance.utils.MessageFile;

import java.io.File;

public final class InstantMaintenance extends JavaPlugin {

    private static InstantMaintenance instance;

    @Override
    public void onEnable() {
        instance = this;
        new ConfigManager();
        new FileManager();
        MessageFile.createMessageFile();
        getCommand("maintenance").setExecutor(new MaintenanceCommand());
        Bukkit.getPluginManager().registerEvents(new LoginEvent(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    public static InstantMaintenance getInstance() {
        return instance;
    }


}
