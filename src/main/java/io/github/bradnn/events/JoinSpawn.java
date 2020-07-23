package io.github.bradnn.events;

import io.github.bradnn.bHub;
import me.clip.placeholderapi.PlaceholderAPI;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.scheduler.BukkitRunnable;

public class JoinSpawn implements Listener {
    bHub plugin;
    public JoinSpawn(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
        player.teleport(new Location(w, x, y, z, yaw, pitch));
        DyeColor lime = DyeColor.LIME;
        byte limeData = (byte) (15 - lime.getData());
        ItemStack togglePlayers = new ItemStack(Material.INK_SACK, 1, limeData);
        ItemMeta togglePlayersMeta = togglePlayers.getItemMeta();
        String togglePlayersName = plugin.getConfig().getString("visibility.visible.name");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            togglePlayersName = PlaceholderAPI.setPlaceholders(e.getPlayer(), togglePlayersName);
        }
        togglePlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', togglePlayersName));

        togglePlayers.setItemMeta(togglePlayersMeta);
        player.getInventory().clear();
        if(plugin.getConfig().getBoolean("visibility.enabled") == true) {
            player.getInventory().setItem(plugin.getConfig().getInt("visibility.item slot"), togglePlayers);

        }

        if(plugin.getConfig().getConfigurationSection("ender butt") == null) {
            plugin.getConfig().set("ender butt.item slot", 5);
            plugin.getConfig().set("ender butt.name", "&aEnder Butt");
            plugin.getConfig().set("ender butt.enabled", true);
            plugin.saveConfig();
        }
        if(plugin.getConfig().getBoolean("ender butt.enabled") == true) {

            ItemStack enderButt = new ItemStack(Material.ENDER_PEARL, 64);
            ItemMeta enderButtMeta = enderButt.getItemMeta();
            String enderButtName = plugin.getConfig().getString("ender butt.name");
            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                enderButtName = PlaceholderAPI.setPlaceholders(e.getPlayer(), enderButtName);
            }
            enderButtMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', enderButtName));
            enderButt.setItemMeta(enderButtMeta);

            player.getInventory().setItem(plugin.getConfig().getInt("ender butt.item slot"), enderButt);
        }

        if(plugin.getConfig().getConfigurationSection("join sound") == null) {
            plugin.getConfig().set("join sound.enabled", true);
            plugin.getConfig().set("join sound.sound", "LEVEL_UP");
            plugin.saveConfig();
        }

        if(plugin.getConfig().getBoolean("join sound.enabled") == true) {
            try {
                player.playSound(player.getLocation(), Sound.valueOf(plugin.getConfig().getString("join sound.sound")), 3, 1);
            } catch(Exception exc) {
                System.out.print("The sound in the config is not valid (Join Sound)");
            }
        }

        if(plugin.getConfig().getConfigurationSection("join title") == null) {
            plugin.getConfig().set("join title.enabled", true);
            plugin.getConfig().set("join title.title", "&b&lWELCOME");
            plugin.getConfig().set("join title.subtitle", "&f{player}");
            plugin.saveConfig();
        }

        if(plugin.getConfig().getBoolean("join title.enabled") == true) {
            String titleString = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join title.title"));
            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                titleString = PlaceholderAPI.setPlaceholders(e.getPlayer(), titleString);
            }
            String title = titleString.replace("{player}", player.getDisplayName());
            String subtitleString = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join title.subtitle"));
            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                subtitleString = PlaceholderAPI.setPlaceholders(e.getPlayer(), subtitleString);
            }
            String subtitle = subtitleString.replace("{player}", player.getDisplayName());

            IChatBaseComponent chatTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title + "\"}");
            IChatBaseComponent chatSubTitle = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subtitle + "\"}");

            PacketPlayOutTitle t = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, chatTitle);
            PacketPlayOutTitle s = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, chatSubTitle);
            PacketPlayOutTitle length = new PacketPlayOutTitle(4, 20, 4);

            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(t);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(s);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);

        }

        ItemStack selectorItem = new ItemStack(Material.valueOf(plugin.getConfig().getString("selector.inventory.item")), 1);
        ItemMeta selectorMeta = selectorItem.getItemMeta();

        String selectorName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("selector.inventory.name"));
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            selectorName = PlaceholderAPI.setPlaceholders(e.getPlayer(), selectorName);
        }
        selectorMeta.setDisplayName(selectorName);
        selectorItem.setItemMeta(selectorMeta);
        player.getInventory().setItem(plugin.getConfig().getInt("selector.inventory.slot"), selectorItem);


    }
}
