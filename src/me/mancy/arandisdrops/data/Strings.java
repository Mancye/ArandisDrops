package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;

public class Strings {

    private final FileConfiguration configuration;
    private final File file;

    public static String prefix = "";

    public static String noPermission = "";

    public static String partyEnded = "";

    public Strings(File file, FileConfiguration configuration) {
        this.configuration = configuration;
        this.file = file;
        loadStrings();
    }

    public void loadStrings() {
        if (this.configuration == null) {
            Bukkit.getServer().getConsoleSender().sendMessage(new FormattedMessage(ChatColor.RED + "Error Loading Strings").toString());
            return;
        }
        if (!this.configuration.contains("prefix")) {
            this.configuration.set("prefix", " ");
            saveFile(configuration, file);
        }

        if (this.configuration.get("prefix") != null)
            Strings.prefix = ChatColor.translateAlternateColorCodes('&', this.configuration.getString("prefix"));
         else
            Strings.prefix = "";
         

        if (!this.configuration.contains("no-permission")) {
            this.configuration.set("no-permission", prefix + " " + "&cSorry, you don't have permission to do this");
            saveFile(configuration, file);
        }
        if (this.configuration.get("no-permission") != null)
            Strings.noPermission = ChatColor.translateAlternateColorCodes('&', this.configuration.getString("no-permission"));
    }

    private void saveFile(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
