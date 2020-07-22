package io.github.bradnn.events;

import io.github.bradnn.bHub;
import me.clip.placeholderapi.PlaceholderAPI;
import net.md_5.bungee.api.chat.ClickEvent;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class PlayerVisibility implements Listener {
    bHub plugin;
    public PlayerVisibility(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void onToggle(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if(plugin.getConfig().getConfigurationSection("visibility") == null) {
            plugin.getConfig().set("visibility.item slot", 8);
            plugin.getConfig().set("visibility.enabled", true);
            plugin.getConfig().set("visibility.visible.name", "&fPlayers: &aVisible");
            plugin.getConfig().set("visibility.visible.dye color", "LIME");
            plugin.getConfig().set("visibility.invisible.name", "&fPlayers: &7Invisible");
            plugin.getConfig().set("visibility.invisible.dye color", "GRAY");
            plugin.saveConfig();
        }
        if(plugin.getConfig().getBoolean("visibility.enabled") == false) {
            return;
        }

        if(player.getItemInHand().getType() == Material.AIR) {
            return;
        }

        String visibleColor = plugin.getConfig().getString("visibility.visible.dye color");
        String invisibleColor = plugin.getConfig().getString("visibility.invisible.dye color");

        DyeColor lime = DyeColor.valueOf(visibleColor);
        byte limeData = (byte) (15 - lime.getData());
        ItemStack togglePlayers = new ItemStack(Material.INK_SACK, 1, limeData);
        ItemMeta togglePlayersMeta = togglePlayers.getItemMeta();
        String togglePlayersName = plugin.getConfig().getString("visibility.visible.name");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            togglePlayersName = PlaceholderAPI.setPlaceholders(e.getPlayer(), togglePlayersName);
        }
        togglePlayersMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', togglePlayersName));
        togglePlayers.setItemMeta(togglePlayersMeta);

        DyeColor gray = DyeColor.valueOf(invisibleColor);
        byte grayData = (byte) (15 - gray.getData());
        ItemStack togglePlayers2 = new ItemStack(Material.INK_SACK, 1, grayData);
        ItemMeta togglePlayersMeta2 = togglePlayers2.getItemMeta();
        String togglePlayers2Name = plugin.getConfig().getString("visibility.invisible.name");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            togglePlayers2Name = PlaceholderAPI.setPlaceholders(e.getPlayer(), togglePlayers2Name);
        }
        togglePlayersMeta2.setDisplayName(ChatColor.translateAlternateColorCodes('&', togglePlayers2Name));
        togglePlayers2.setItemMeta(togglePlayersMeta2);

        if(player.getItemInHand().getItemMeta().equals(togglePlayersMeta)) {
            player.getInventory().setItem(plugin.getConfig().getInt("visibility.item slot"), togglePlayers2);
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                player.hidePlayer(onlinePlayers);
            }
        }else if(player.getItemInHand().getItemMeta().equals(togglePlayersMeta2)) {
            player.getInventory().setItem(plugin.getConfig().getInt("visibility.item slot"), togglePlayers);
            for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                player.showPlayer(onlinePlayers);
            }
        }
    }
}
