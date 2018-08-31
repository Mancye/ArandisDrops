package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Strings {

    private Main plugin;
    private static FileConfiguration stringsConfig;
    private static File stringsFile;

    public static String prefix = "";

    public static String noPermission = "";

    public static String partyEnded = "";

    public static String partyStarted = "";

    public static String invalidArguments = "";

    public static String insufficientBalance = "";

    public static String alreadyActive = "";

    public Strings(Main main) {
        this.plugin = main;
        saveDefaultConfig();
        Strings.stringsConfig = YamlConfiguration.loadConfiguration(stringsFile);
    }

    private void saveDefaultConfig() {
        if (stringsFile == null) {
            stringsFile = new File(plugin.getDataFolder(), "strings.yml");
        }
        if (!stringsFile.exists()) {
            plugin.saveResource("strings.yml", false);
        }
    }

    public void loadStrings() {
        prefix = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("Prefix"));
        noPermission = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("No-Permission"));
        partyEnded = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("Party-Ended"));
        partyStarted = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("Party-Started"));
        invalidArguments = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("Invalid-Arguments"));
        insufficientBalance = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("Insufficient-Balance"));
        alreadyActive = ChatColor.translateAlternateColorCodes('&', stringsConfig.getString("Already-Active"));
    }

    public static void reloadConfig() {
        stringsConfig = YamlConfiguration.loadConfiguration(stringsFile);
    }


}
