package me.mancy.arandisdrops.parties;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class LocationManager {

    public static List<Location> locations = new ArrayList<>();

    public static boolean isValidLocation(Location location) {
        return location.getWorld().getHighestBlockAt(location).getType().equals(Material.BEACON);
    }

}
