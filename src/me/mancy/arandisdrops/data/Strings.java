package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Strings {

    private final FileConfiguration stringsConfig;

    public static String prefix = "";

    public static String noPermission = "";

    public Strings(FileConfiguration configuration) {
        this.stringsConfig = configuration;
        loadStrings();
    }

    private void loadStrings() {
        if (stringsConfig == null) {
            System.out.println("ArandisDrops ERROR Loading Strings!");
        }
        if (!stringsConfig.contains("prefix"))
            stringsConfig.set("prefix", " ");

        if (stringsConfig.get("prefix") != null)
            Strings.prefix = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("prefix"));
         else
            Strings.prefix = " ";


        if (!stringsConfig.contains("no-permission"))
            stringsConfig.set("no-permission", new FormattedMessage(ChatColor.RED + "Sorry, you don't have permission to do this").toString());
        if (stringsConfig.get("no-permission") != null)
            Strings.noPermission = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("no-permission"));
    }

}
