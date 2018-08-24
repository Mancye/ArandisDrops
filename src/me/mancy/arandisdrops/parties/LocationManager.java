package me.mancy.arandisdrops.parties;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.List;

public class LocationManager {

    private static List<Location> locations = new ArrayList<>();


    public static List<Location> getLocations() {
        return locations;
    }

    public static void setLocations(List<Location> locations) {
        LocationManager.locations = locations;
    }

    public static void addLocation(Location loc) {
        if (loc != null)
            locations.add(loc);
    }

}
