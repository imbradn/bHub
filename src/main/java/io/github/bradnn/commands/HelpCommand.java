package io.github.bradnn.commands;

import io.github.bradnn.bHub;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {
    private bHub plugin = bHub.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&bb&fHub &71.0.0-SNAPSHOT"));
        player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------------------------");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/hub &fsetspawn &8- &7Sets the spawn to your location."));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b/hub &fspawn &8- &7Sends you to the spawn if any is set."));
        player.sendMessage(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-----------------------------------");

    }

    @Override
    public String name() {
        return plugin.commandManager.help;
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
