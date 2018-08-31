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

    public static void addUnvalidatedLocation(Location loc) {
        if (loc != null)
            unValidatedLocations.add(loc);
    }

    public static void addValidatedLocation(Location loc) {
        if (loc != null) {
            unValidatedLocations.remove(loc);
            validatedLocations.add(loc);
        }
    }

    public static List<Location> getValidatedLocations() {
        return validatedLocations;
    }
}
