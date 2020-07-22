package io.github.bradnn;
import io.github.bradnn.commands.CommandManager;
import io.github.bradnn.events.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class bHub extends JavaPlugin {
    private static bHub instance;
    public CommandManager commandManager;
    final FileConfiguration config = this.getConfig();

    @Override
    public void onEnable() {
        config.options().copyDefaults(true);
        saveConfig();
        getLogger().info("onEnabled is called!");
        getServer().getPluginManager().registerEvents(new EnderButt(this), this);
        getServer().getPluginManager().registerEvents(new JoinSpawn(this), this);
        getServer().getPluginManager().registerEvents(new DoubleJump(this), this);
        getServer().getPluginManager().registerEvents(new PlayerVisibility(this), this);
        getServer().getPluginManager().registerEvents(new NoInventoryUpdate(this), this);
        getServer().getPluginManager().registerEvents(new JoinMessages(this), this);
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


    @Override
    public void onDisable() {
        getLogger().info("onDisable is called!");
    }
}
