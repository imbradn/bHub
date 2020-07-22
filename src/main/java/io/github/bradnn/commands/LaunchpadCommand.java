package io.github.bradnn.commands;

import io.github.bradnn.bHub;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class LaunchpadCommand extends SubCommand {
    private bHub plugin = bHub.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {

        ItemStack launchpadItem = new ItemStack(Material.IRON_PLATE, 64);
        ItemMeta launchpadMeta = launchpadItem.getItemMeta();
        launchpadMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bLaunchpad"));
        ArrayList<String> launchpadLore = new ArrayList();
        launchpadLore.add("&7Place down to set a launchpad in this location.");
        launchpadMeta.setLore(launchpadLore);
        launchpadItem.setItemMeta(launchpadMeta);

        if(player.getInventory().contains(launchpadItem)) {
            player.getInventory().remove(launchpadItem);
            player.sendMessage(ChatColor.GREEN + "The launchpad has been removed from your inventory");
        } else {
            player.getInventory().setItemInHand(launchpadItem);
            player.sendMessage(ChatColor.GREEN + "The launchpad has been added to your inventory");
        }

    }

    @Override
    public String name() {
        return plugin.commandManager.launchpad;
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
