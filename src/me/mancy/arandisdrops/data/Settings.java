package me.mancy.arandisdrops.data;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {

    // Global Settings
    private static double dropRadius = 0.0;
    private static double dropHeight = 0.0;
    private static int countdownTime = 0;

    //Tier Settings
    private static Map<Integer, Integer[]> dropChances = new HashMap<>();
    private static Map<Integer, Integer> costs = new HashMap<>();
    private static Map<Integer, List<ItemStack>> itemLists = new HashMap<>();

    public static double getDropRadius() {
        return dropRadius;
    }

    public static void setDropRadius(double dropRadius) {
        Settings.dropRadius = dropRadius;
    }

    public static double getDropHeight() {
        return dropHeight;
    }

    public static void setDropHeight(double dropHeight) {
        Settings.dropHeight = dropHeight;
    }

    public static int getCountdownTime() {
        return countdownTime;
    }

    public static void setCountdownTime(int countdownTime) {
        Settings.countdownTime = countdownTime;
    }

    public static Map<Integer, Integer[]> getDropChances() {
        return dropChances;
    }

    public static void setDropChances(Map<Integer, Integer[]> dropChances) {
        Settings.dropChances = dropChances;
    }

    public static Map<Integer, Integer> getCosts() {
        return costs;
    }

    public static void setCosts(Map<Integer, Integer> costs) {
        Settings.costs = costs;
    }

    public static Map<Integer, List<ItemStack>> getItemLists() {
        return itemLists;
    }

    public static void setItemLists(Map<Integer, List<ItemStack>> itemLists) {
        Settings.itemLists = itemLists;
    }




}
