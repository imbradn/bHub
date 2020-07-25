package io.github.bradnn;
import io.github.bradnn.commands.CommandManager;
import io.github.bradnn.events.*;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

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
        getServer().getPluginManager().registerEvents(new SelectorChecks(this), this);
        getServer().getPluginManager().registerEvents(new SelectorItem(this), this);
        getServer().getPluginManager().registerEvents(new WorldChecks(this), this);
        getServer().getPluginManager().registerEvents(new NoVoid(this), this);
        setInstance(this);
        commandManager = new CommandManager();

        commandManager.setup();

        if(getConfig().getBoolean("tablist.enabled") == true) {

            PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
            new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        Field a = packet.getClass().getDeclaredField("a");
                        a.setAccessible(true);
                        Field b = packet.getClass().getDeclaredField("b");
                        b.setAccessible(true);

                        List<String> headerList = getConfig().getStringList("tablist.header");
                        String headerFinal = ChatColor.translateAlternateColorCodes('&', String.join("\n", headerList));
                        List<String> footerList = getConfig().getStringList("tablist.footer");
                        String footerFinal = ChatColor.translateAlternateColorCodes('&', String.join("\n", footerList));

                        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                            headerFinal = PlaceholderAPI.setPlaceholders(null, headerFinal);
                            footerFinal = PlaceholderAPI.setPlaceholders(null, footerFinal);
                        }

                        Object header = new ChatComponentText(headerFinal);

                        Object footer = new ChatComponentText(footerFinal);
                        a.set(packet, header);
                        b.set(packet, footer);

                        if (Bukkit.getOnlinePlayers().size() == 0) return;
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                        }

                    } catch (NoSuchFieldException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }.runTaskTimer(this, 0, 20);
        }
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
