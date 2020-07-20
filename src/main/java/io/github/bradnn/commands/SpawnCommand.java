package io.github.bradnn.commands;

import io.github.bradnn.bHub;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class SpawnCommand extends SubCommand {
    private bHub plugin = bHub.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {

        if(plugin.getConfig().getConfigurationSection("spawn") == null) {
            player.sendMessage("Spawn point has not been set.");
            return;
        }
        World w = Bukkit.getServer().getWorld(plugin.getConfig().getString("spawn.world"));
        double x = plugin.getConfig().getDouble("spawn.x");
        double y = plugin.getConfig().getDouble("spawn.y");
        double z = plugin.getConfig().getDouble("spawn.z");
        float yaw = (float) plugin.getConfig().getDouble("spawn.yaw");
        float pitch = (float) plugin.getConfig().getDouble("spawn.pitch");
        player.teleport(new Location(w, x, y, z, yaw, pitch));

    }

    @Override
    public String name() {
        return plugin.commandManager.spawn;
    }

    @Override
    public String info() {
        return "";
    }

    @Override
    public String[] aliases() {
        return new String[0];
    }
}
