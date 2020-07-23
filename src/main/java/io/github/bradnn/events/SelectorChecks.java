package io.github.bradnn.events;

import io.github.bradnn.bHub;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SelectorChecks implements Listener {
    bHub plugin;
    public SelectorChecks(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();

        String invenName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("selector.name"));
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            invenName = PlaceholderAPI.setPlaceholders(player, invenName);
        }

        if(e.getClickedInventory().getTitle().equalsIgnoreCase(invenName) && e.getClickedInventory().getTitle() != null) {
            e.setCancelled(true);

            int itemKey;

            for(final String key : plugin.getConfig().getConfigurationSection("items").getKeys(false)) {

                final ConfigurationSection section = plugin.getConfig().getConfigurationSection("items." + key);

                if(section.getInt("slot") == e.getSlot()) {
                    player.performCommand(plugin.getConfig().getString("items." + key + ".command"));
                }

            }

        }
    }
}
