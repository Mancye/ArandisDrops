package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.parties.LocationManager;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationDataManager {

    private final FileConfiguration configuration;
    private final File file;

    public LocationDataManager(FileConfiguration configurationFile, File file) {
        this.configuration = configurationFile;
        this.file = file;
    }

    public void saveLocations() {
        configuration.set("Drop Locations", LocationManager.getLocations());
        saveFile(configuration, file);
    }

    @SuppressWarnings("unchecked")
    public void loadLocations() {
        if (configuration.contains("Drop Locations") && configuration.getList("Drop Locations") != null) {
            try {
                LocationManager.setLocations((List<Location>) this.configuration.getList("Drop Locations"));
            } catch (ClassCastException e) {
                Bukkit.getServer().getConsoleSender().sendMessage(new FormattedMessage(ChatColor.RED + "ERROR: Drop Locations configuration section corrupted, please revert changes to locations.yml file.").toString());
                LocationManager.setLocations(new ArrayList<>());
            }
        } else {
            configuration.set("Drop Locations", null);
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
