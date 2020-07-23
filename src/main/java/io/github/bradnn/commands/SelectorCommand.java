package io.github.bradnn.commands;

import io.github.bradnn.bHub;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectorCommand extends SubCommand {
    private bHub plugin = bHub.getInstance();

    @Override
    public void onCommand(Player player, String[] args) {


        String invenName = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("selector.name"));
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            invenName = PlaceholderAPI.setPlaceholders(player, invenName);
        }

        int size = plugin.getConfig().getInt("selector.size");

        Inventory selector = Bukkit.getServer().createInventory(player, size, invenName);
        for (final String key : plugin.getConfig().getConfigurationSection("items").getKeys(false)) {
            final ConfigurationSection section = plugin.getConfig().getConfigurationSection("items." + key);

            String materialBase;
            String nameBase;
            List<String> loreBase;
            Integer slotBase;

            materialBase = section.getString("item");
            nameBase = ChatColor.translateAlternateColorCodes('&', section.getString("name"));
            loreBase = section.getStringList("lore");
            slotBase = section.getInt("slot");
            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                nameBase = PlaceholderAPI.setPlaceholders(player, nameBase);
                loreBase = PlaceholderAPI.setPlaceholders(player, loreBase);
            }
            String[] loreArray = loreBase.toArray(new String[loreBase.size()]);
            for (int i=0; i<loreArray.length; i++) {
                loreArray[i] = ChatColor.translateAlternateColorCodes('&', loreArray[i]);
            }

            loreBase = Arrays.asList(loreArray);

            final Material material = Material.valueOf(materialBase);
            ItemStack item = new ItemStack(material);
            ItemMeta itemMeta = item.getItemMeta();

            itemMeta.setDisplayName(nameBase);
            itemMeta.setLore(loreBase);
            item.setItemMeta(itemMeta);
            selector.setItem(slotBase, item);

        }

        player.openInventory(selector);


    }

    @Override
    public String name() {
        return plugin.commandManager.selector;
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
