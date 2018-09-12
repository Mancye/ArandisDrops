package me.mancy.arandisdrops.data;

import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Settings {

    private static Settings instance = null;

    // Global Settings
    private double dropRadius;
    private double dropHeight;
    private int countdownTime;

    //Tier Settings
    private Map<Integer, Integer[]> dropChances = new HashMap<>();
    private Map<Integer, Integer> costs = new HashMap<>();
    private Map<Integer, List<ItemStack>> itemLists = new HashMap<>();

    public double getDropRadius() {
        return dropRadius;
    }

    public void setDropRadius(double dropRadius) {
        this.dropRadius = dropRadius;
    }

    public double getDropHeight() {
        return dropHeight;
    }

    public void setDropHeight(double dropHeight) {
        this.dropHeight = dropHeight;
    }

    public int getCountdownTime() {
        return countdownTime;
    }

    public void setCountdownTime(int countdownTime) {
        this.countdownTime = countdownTime;
    }

    public Map<Integer, Integer[]> getDropChances() {
        return dropChances;
    }

     void setDropChances(Map<Integer, Integer[]> dropChances) {
         this.dropChances = dropChances;
    }

    public Map<Integer, Integer> getCosts() {
        return costs;
    }

     void setCosts(Map<Integer, Integer> costs) {
         this.costs = costs;
    }

    public Map<Integer, List<ItemStack>> getItemLists() {
        return itemLists;
    }

     void setItemLists(Map<Integer, List<ItemStack>> itemLists) {
         this.itemLists = itemLists;
    }

    public static Settings getInstance() {
        if (instance == null)
            instance = new Settings();
        return instance;
    }


}
