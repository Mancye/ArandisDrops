package me.mancy.alphadrops.utils;

import org.bukkit.ChatColor;

public class FormattedMessage  {

    private String message;
    private final String prefix = ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + ChatColor.BOLD.toString() + "P" + ChatColor.RED + ChatColor.BOLD.toString() + "A" + ChatColor.DARK_GRAY + ":" + ChatColor.GRAY + "Events" + ChatColor.DARK_GRAY + "]";

    public FormattedMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return prefix + " " + message;
    }

}
