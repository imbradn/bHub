package io.github.bradnn.events;

import io.github.bradnn.bHub;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;

public class NoInventoryUpdate implements Listener {
    bHub plugin;
    public NoInventoryUpdate(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void onUpdate(InventoryClickEvent e) {
        if(plugin.getConfig().getConfigurationSection("checks") == null) {
            plugin.getConfig().set("checks.no inventory move", true);
            plugin.getConfig().set("checks.no item drop", true);
            plugin.getConfig().set("checks.no item pickup", true);
            plugin.saveConfig();
        }

        if(plugin.getConfig().getBoolean("checks.no inventory move") == true) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        if(plugin.getConfig().getConfigurationSection("checks") == null) {
            plugin.getConfig().set("checks.no inventory move", true);
            plugin.getConfig().set("checks.no item drop", true);
            plugin.getConfig().set("checks.no item pickup", true);
            plugin.saveConfig();
        }
        if(plugin.getConfig().getBoolean("checks.no item drop") == true) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e) {
        if(plugin.getConfig().getConfigurationSection("checks") == null) {
            plugin.getConfig().set("checks.no inventory move", true);
            plugin.getConfig().set("checks.no item drop", true);
            plugin.getConfig().set("checks.no item pickup", true);
            plugin.saveConfig();
        }
        if(plugin.getConfig().getBoolean("checks.no item pickup") == true) {
            e.setCancelled(true);
        }
    }
}
