package io.github.bradnn.utils;

import org.bukkit.ChatColor;

public class StringUtils {

    public static String color(String msg) {
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

}
