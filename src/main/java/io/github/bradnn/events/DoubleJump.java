package io.github.bradnn.events;

import io.github.bradnn.bHub;
import net.minecraft.server.v1_8_R3.EnumParticle;
import net.minecraft.server.v1_8_R3.PacketPlayOutWorldParticles;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

public class DoubleJump implements Listener {
    bHub plugin;
    public DoubleJump(bHub instance) {
        plugin = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        player.setAllowFlight(true);
    }

    @EventHandler
    public void onDoubleJump(PlayerToggleFlightEvent e) {

        if(plugin.getConfig().getConfigurationSection("double jump") == null) {
            plugin.getConfig().set("double jump.enabled", true);
            plugin.saveConfig();
        }
        if(plugin.getConfig().getBoolean("double jump.enabled") == false) {
            return;
        }

        Player player = e.getPlayer();
        e.setCancelled(true);
        player.setVelocity(player.getLocation().getDirection().multiply(1.6d).setY(1.0d));
        player.playSound(player.getLocation(), Sound.GHAST_FIREBALL, 5.0F, 0.733F);
        Location loc = player.getLocation();
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.FLAME,true, (float) (loc.getX()), (float) (loc.getY()), (float) (loc.getZ()), 0.6F, 0, 0.6F, 0, 3);
        PacketPlayOutWorldParticles packet2 = new PacketPlayOutWorldParticles(EnumParticle.SMOKE_NORMAL,true, (float) (loc.getX()), (float) (loc.getY()), (float) (loc.getZ()), 0.6F, 0, 0.6F, 0, 3);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet2);
        for(Entity ent : loc.getWorld().getNearbyEntities(loc, 30, 30, 30)) { // For-loop sends the packet to all nearby players
            if(!(ent instanceof Player)) continue;
            Player nearbyPlayer = (Player) ent;
            ((CraftPlayer)nearbyPlayer).getHandle().playerConnection.sendPacket(packet);
            ((CraftPlayer)nearbyPlayer).getHandle().playerConnection.sendPacket(packet2);
        }
    }
}
