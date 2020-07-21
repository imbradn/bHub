package io.github.bradnn.events;

import io.github.bradnn.bHub;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

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
        togglePlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("visibility.visible.name")));

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
            enderButtMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("ender butt.name")));
            enderButt.setItemMeta(enderButtMeta);

            player.getInventory().setItem(plugin.getConfig().getInt("ender butt.item slot"), enderButt);
        }
    }
}
