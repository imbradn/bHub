package io.github.bradnn.events;

import io.github.bradnn.bHub;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SelectorItem implements Listener {
    bHub plugin;
    public SelectorItem(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void onToggle(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if(player.getItemInHand().getType() == Material.AIR) {
            return;
        }

        ItemStack selectorItem = new ItemStack(Material.valueOf(plugin.getConfig().getString("selector.inventory.item")), 1);
        ItemMeta selectorMeta = selectorItem.getItemMeta();

        String selectorName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("selector.inventory.name"));
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            selectorName = PlaceholderAPI.setPlaceholders(e.getPlayer(), selectorName);
        }
        selectorMeta.setDisplayName(selectorName);
        selectorItem.setItemMeta(selectorMeta);


        if(player.getItemInHand().getItemMeta().equals(selectorItem.getItemMeta())) {
            player.performCommand("hub selector");
        }
    }
}
