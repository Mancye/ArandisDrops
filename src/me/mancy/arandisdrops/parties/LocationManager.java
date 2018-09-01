package me.mancy.arandisdrops.parties;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocationManager {

    private static List<Location> unValidatedLocations = new ArrayList<>();
    private static List<Location> validatedLocations = new ArrayList<>();

    public static List<Location> getUnValidatedLocations() {
        return unValidatedLocations;
    }

    public static void setValidatedLocations(List<Location> locations) {
        LocationManager.validatedLocations = locations;
    }

    public static Location getBlockLocation(Location location) {
        return new Location(location.getWorld(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    public static void addUnvalidatedLocation(Location loc) {
        if (loc != null) {
            unValidatedLocations.add(getBlockLocation(loc));
        }
    }

    static void addValidatedLocation(Location loc) {
        if (loc != null) {
            validatedLocations.add(getBlockLocation(loc));
        }
    }

    public static List<Location> getValidatedLocations() {
        return validatedLocations;
    }
}
