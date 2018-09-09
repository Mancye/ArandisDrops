package me.mancy.arandisdrops.utils;

import me.mancy.arandisdrops.data.Strings;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;


public class Messager {

    public static void sendMessage(Player p, String message) {
        p.sendMessage(Strings.prefix.trim() + " " + message);
    }

    public static void sendMessage(ConsoleCommandSender c, String message) { c.sendMessage(Strings.prefix.trim() + " " + message); }

    public static void sendMessage(CommandSender s, String message) { s.sendMessage(Strings.prefix.trim() + " " + message); }

    public static String toFormatted(String string) {
        return Strings.prefix.trim() + " " + string;
    }

}
