package io.github.bradnn;
import io.github.bradnn.commands.CommandManager;
import io.github.bradnn.events.*;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class bHub extends JavaPlugin {
    private static bHub instance;
    public CommandManager commandManager;
    final FileConfiguration config = this.getConfig();
    private File launchpadConfigFile;
    private FileConfiguration launchpadConfig;

    @Override
    public void onEnable() {
        config.options().copyDefaults(true);
        saveConfig();
        createLaunchpadConfig();
        getLogger().info("onEnabled is called!");
        getServer().getPluginManager().registerEvents(new EnderButt(this), this);
        getServer().getPluginManager().registerEvents(new JoinSpawn(this), this);
        getServer().getPluginManager().registerEvents(new DoubleJump(this), this);
        getServer().getPluginManager().registerEvents(new PlayerVisibility(this), this);
        getServer().getPluginManager().registerEvents(new NoInventoryUpdate(this), this);
        getServer().getPluginManager().registerEvents(new JoinMessages(this), this);
        getServer().getPluginManager().registerEvents(new LaunchpadEvents(this), this);
        setInstance(this);
        commandManager = new CommandManager();

        commandManager.setup();
    }

    public static bHub getInstance() {
        return instance;
    }

    private static void setInstance(bHub instance) {
        bHub.instance = instance;
    }

    public FileConfiguration getLaunchpadConfig() {
        return this.launchpadConfig;
    }

    public void createLaunchpadConfig() {
        launchpadConfigFile = new File(getDataFolder(), "launchpads.yml");
        if(!launchpadConfigFile.exists()) {
            launchpadConfigFile.getParentFile().mkdirs();
            saveResource("launchpads.yml", false);
        }

        launchpadConfig = new YamlConfiguration();
        try {
            launchpadConfig.load(launchpadConfigFile);
        } catch (IOException | InvalidConfigurationException exc){
            exc.printStackTrace();
        }
    }

    public void saveLaunchpadConfig() {
        try {
            launchpadConfig.save(launchpadConfigFile);
            try {
                launchpadConfig.load(launchpadConfigFile);
            } catch (IOException | InvalidConfigurationException exc){
                exc.printStackTrace();
            }
        } catch (IOException exc){
            exc.printStackTrace();
        }
    }


    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
