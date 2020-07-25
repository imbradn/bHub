package io.github.bradnn.events;

import io.github.bradnn.bHub;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.*;
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
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NoVoid implements Listener {
    bHub plugin;
    public NoVoid(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void noVoidEvent(PlayerMoveEvent e) {
        if(plugin.getConfig().getBoolean("no void.enabled") == true) {
            if(e.getPlayer().getLocation().getY() < 0) {
                World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
                double x = plugin.getConfig().getDouble("spawn.x");
                double y = plugin.getConfig().getDouble("spawn.y");
                double z = plugin.getConfig().getDouble("spawn.z");
                float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
                float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
                e.getPlayer().teleport(new Location(w, x, y, z, yaw, pitch));
            }
        }
    }
}
