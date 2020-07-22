package io.github.bradnn.events;

import io.github.bradnn.bHub;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class JoinMessages implements Listener {
    bHub plugin;
    public JoinMessages(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        if(plugin.getConfig().getConfigurationSection("join messages")  == null) {
            plugin.getConfig().set("join messages.enabled", true);
            plugin.getConfig().set("join messages.join message", "&7+ &b{player}");
            plugin.getConfig().set("join messages.leave message", "&7- &b{player}");
            plugin.saveConfig();
        }

        if(plugin.getConfig().getBoolean("join messages.enabled") == true) {
            String joinString = plugin.getConfig().getString("join messages.join message");
            String playerName = e.getPlayer().getDisplayName();
            String string = joinString.replace("{player}", playerName);

            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                string = PlaceholderAPI.setPlaceholders(e.getPlayer(), string);
            }
            e.setJoinMessage(ChatColor.translateAlternateColorCodes('&', string));
        } else {
            return;
        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent e) {
        if(plugin.getConfig().getConfigurationSection("join messages")  == null) {
            plugin.getConfig().set("join messages.enabled", true);
            plugin.getConfig().set("join messages.join message", "&7+ &b{player}");
            plugin.getConfig().set("join messages.leave message", "&7- &b{player}");
            plugin.saveConfig();
        }

        if(plugin.getConfig().getBoolean("join messages.enabled") == true) {

            String leaveString = plugin.getConfig().getString("join messages.leave message");
            String playerName = e.getPlayer().getDisplayName();
            leaveString.replace("{player}", playerName);
            String string = leaveString.replace("{player}", playerName);


            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                string = PlaceholderAPI.setPlaceholders(e.getPlayer(), string);
            }
            e.setQuitMessage(ChatColor.translateAlternateColorCodes('&', string));
        } else {
            return;
        }
    }

}
