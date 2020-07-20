package io.github.bradnn.events;

import io.github.bradnn.bHub;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.UUID;

public class EnderButt implements Listener {
    private bHub plugin = bHub.getInstance();
    HashMap<UUID, String> inair = new HashMap<UUID, String>();

    @EventHandler
    public void onThrow(ProjectileLaunchEvent e) {
        //TODO: Fix this later bc im too lazy to figure it out rn
        if (e.getEntity().getShooter() instanceof Player){
            if (e.getEntity().toString().contentEquals("CraftEnderPearl")){
                final Player p = (Player) e.getEntity().getShooter();
                final Entity ep = (Entity) e.getEntity();
                if (!(inair.containsKey(p.getUniqueId()))){
                    new BukkitRunnable(){
                        public void run(){
                            if (inair.containsKey(p.getUniqueId())){
                                ep.setPassenger(p);
                            } else {
                                cancel();
                            }
                        }
                    }.runTaskTimer(plugin, 0L, 1L);
                    return;
                } else {
                    e.setCancelled(true);
                }
            }
        }
    }

}
