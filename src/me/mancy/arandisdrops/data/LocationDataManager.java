package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.parties.LocationManager;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationDataManager {

    private final Main plugin;
    private final FileConfiguration locationConfig;
    private File locationFile;


    public LocationDataManager(Main main) {
        this.plugin = main;
        saveDefaultConfig();
        this.locationConfig = YamlConfiguration.loadConfiguration(locationFile);
    }

    private void saveDefaultConfig() {
        if (locationFile == null) {
            locationFile = new File(plugin.getDataFolder(), "locations.yml");
        }
        if (!locationFile.exists()) {
            plugin.saveResource("locations.yml", false);
        }
    }

    public void saveLocations() {
        locationConfig.set("Drop Locations", LocationManager.getLocations());
        saveFile(locationConfig, locationFile);
    }

    @SuppressWarnings("unchecked")
    public void loadLocations() {
        if (locationConfig.contains("Drop Locations") && locationConfig.getList("Drop Locations") != null) {
            try {
                LocationManager.setLocations((List<Location>) this.locationConfig.getList("Drop Locations"));
            } catch (ClassCastException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(new FormattedMessage(ChatColor.RED + "ERROR: Drop Locations configuration section corrupted, please revert changes to locations.yml file.").toString());
                LocationManager.setLocations(new ArrayList<>());
            }
        } else {
            locationConfig.set("Drop Locations", null);
        }
    }

    private void saveFile(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
