package me.mancy.arandisdrops.data;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Strings {

    private final FileConfiguration stringsConfig;

    public static String prefix = "";

    public Strings(FileConfiguration configuration) {
        this.stringsConfig = configuration;
        loadStrings();
    }

    private void loadStrings() {
        if (stringsConfig == null) {
            System.out.println("ArandisDrops ERROR Loading Strings!");
        }
        if (!stringsConfig.contains("prefix")) {
            stringsConfig.set("prefix", " ");
        }
        if (stringsConfig.get("prefix") != null) {
            Strings.prefix = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("prefix"));
        } else {
            Strings.prefix = " ";
        }
    }

}
