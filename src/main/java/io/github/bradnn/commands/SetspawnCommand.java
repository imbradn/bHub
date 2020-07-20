package io.github.bradnn.commands;

import io.github.bradnn.bHub;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class SetspawnCommand extends SubCommand {
    private bHub plugin = bHub.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {

        plugin.getConfig().set("spawn.world", player.getLocation().getWorld().getName());
        plugin.getConfig().set("spawn.x", player.getLocation().getX());
        plugin.getConfig().set("spawn.y", player.getLocation().getY());
        plugin.getConfig().set("spawn.z", player.getLocation().getZ());
        plugin.getConfig().set("spawn.yaw", player.getLocation().getYaw());
        plugin.getConfig().set("spawn.pitch", player.getLocation().getPitch());
        plugin.saveConfig();
        player.sendMessage(ChatColor.GREEN + "Spawn successfully set!");


    }

    @Override
    public String name() {
        return plugin.commandManager.setspawn;
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
