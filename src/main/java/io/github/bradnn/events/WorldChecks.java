package io.github.bradnn.events;

import io.github.bradnn.bHub;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.hanging.HangingEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WorldChecks implements Listener {
    bHub plugin;
    public WorldChecks(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void blockBreakProtection(BlockBreakEvent e) {
        if(plugin.getConfig().getBoolean("checks.no block break") == true) {
            if(!e.getPlayer().hasPermission("hub.bypass")) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void blockPlaceProtection(BlockPlaceEvent e) {
        if(plugin.getConfig().getBoolean("checks.no block place") == true) {
            if(!e.getPlayer().hasPermission("hub.bypass")) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void entityProtection(EntityDamageByEntityEvent e) {
        if(plugin.getConfig().getBoolean("checks.no armor stand break") == true) {
            if(!e.getDamager().hasPermission("hub.bypass")) {
                if(e.getEntityType() == EntityType.ARMOR_STAND) {
                    e.setCancelled(true);
                }
            }
        }
        if(plugin.getConfig().getBoolean("checks.no item frame break") == true) {
            if(!e.getDamager().hasPermission("hub.bypass")) {
                if(e.getEntityType() == EntityType.ITEM_FRAME) {
                    e.setCancelled(true);
                }
            }
        }

    }

    @EventHandler
    public void entityProtection2(HangingBreakByEntityEvent e) {

        if(plugin.getConfig().getBoolean("checks.no item frame break") == true) {
            if(!e.getRemover().hasPermission("hub.bypass")) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void NoDamage(EntityDamageEvent e) {
        if(plugin.getConfig().getBoolean("checks.no damage") == true) {
            if(e.getEntityType() == EntityType.PLAYER) {
                e.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void NoPvP(EntityDamageByEntityEvent e) {
        if(plugin.getConfig().getBoolean("checks.no pvp") == true) {
            if(e.getEntityType() == EntityType.PLAYER) {
                if(e.getDamager().getType() == EntityType.PLAYER) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
