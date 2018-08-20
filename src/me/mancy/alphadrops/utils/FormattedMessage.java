package me.mancy.alphadrops.utils;

import me.mancy.alphadrops.data.Strings;
import org.bukkit.ChatColor;

public class FormattedMessage  {

    private String message;

    public FormattedMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return Strings.prefix + " " + message;
    }
}
