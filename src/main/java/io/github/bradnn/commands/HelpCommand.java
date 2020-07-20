package io.github.bradnn.commands;

import io.github.bradnn.bHub;
import org.bukkit.entity.Player;

public class HelpCommand extends SubCommand {
    private bHub plugin = bHub.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {

        player.sendMessage("This info is hot");

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
