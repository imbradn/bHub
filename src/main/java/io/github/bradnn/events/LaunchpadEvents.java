package io.github.bradnn.events;

import io.github.bradnn.bHub;
import org.bukkit.*;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;

public class LaunchpadEvents implements Listener {
    bHub plugin;
    public LaunchpadEvents(bHub instance) {
        plugin = instance;
    }

    public List<String> locations;

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {

        ItemStack launchpadItem = new ItemStack(Material.IRON_PLATE, 64);
        ItemMeta launchpadMeta = launchpadItem.getItemMeta();
        launchpadMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bLaunchpad"));
        ArrayList<String> launchpadLore = new ArrayList();
        launchpadLore.add("&7Place down to set a launchpad in this location.");
        launchpadMeta.setLore(launchpadLore);
        launchpadItem.setItemMeta(launchpadMeta);
        if(!e.getItemInHand().getItemMeta().equals(launchpadMeta)) {
            return;
        }

            if(plugin.getLaunchpadConfig().getList("locations") == null) {
                locations = new ArrayList<String>();
                plugin.getLaunchpadConfig().set("locations", locations);
                plugin.saveLaunchpadConfig();
            }

            locations = (List<String>) plugin.getLaunchpadConfig().getList("locations");

            locations.add(e.getBlock().getLocation().toString());
            plugin.getLaunchpadConfig().set("locations", locations);
            plugin.saveLaunchpadConfig();

    }

    @EventHandler
    public void onInteract(PlayerMoveEvent e) {
        if(e.getTo().getBlock().getRelative(BlockFace.SELF).getType() == Material.IRON_PLATE) {
            locations = (List<String>) plugin.getLaunchpadConfig().getList("locations");
            if(locations.contains(e.getTo().getBlock().getRelative(BlockFace.SELF).getLocation().toString())) {
                Player player = e.getPlayer();
                player.setVelocity(player.getLocation().getDirection().multiply(3));
                player.setVelocity(new Vector(player.getVelocity().getX(), 1.0D, e.getPlayer().getVelocity().getZ()));
                player.playSound(player.getLocation(), Sound.PISTON_EXTEND, 5.0F, 0.733F);
            }
        }
    }

}
