package me.mancy.alphadrops.data;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsManager {

    private final FileConfiguration configurationFile;
    private final File file;

    public SettingsManager(File file, FileConfiguration config) {
        this.configurationFile = config;
        this.file = file;
    }

    public void saveSettings() {
        configurationFile.set("Drop Radius", Settings.getDropRadius());
        saveFile(configurationFile, file);
        configurationFile.set("Countdown Time", Settings.getCountdownTime());
        saveFile(configurationFile, file);
        configurationFile.set("Drop Height", Settings.getDropHeight());
        saveFile(configurationFile, file);

        for (int x : Settings.getItemLists().keySet()) {
            configurationFile.set("Item Lists. Tier" + x, Settings.getItemLists().get(x));
            saveFile(configurationFile, file);
        }

        for (int x : Settings.getCosts().keySet()) {
            configurationFile.set("Costs. Tier" + x, Settings.getCosts().get(x));
            saveFile(configurationFile, file);
        }

        for (int x : Settings.getDropChances().keySet()) {
            configurationFile.set("Drop Chances. Tier" + x, Settings.getDropChances().get(x));
            saveFile(configurationFile, file);
        }

    }

    @SuppressWarnings("unchecked")
    public void loadSettings() {
        Settings.setDropRadius(configurationFile.getDouble("Drop Radius"));
        Settings.setCountdownTime(configurationFile.getInt("Countdown Time"));
        Settings.setDropRadius(configurationFile.getDouble("Drop Height"));
        Map<Integer, List<ItemStack>> itemList = new HashMap<>();
        for (int x = 1; x <= 5; x++) {
            itemList.put(x, (List<ItemStack>) configurationFile.getList("Item Lists. Tier" + x));
        }
        Settings.setItemLists(itemList);

        Map<Integer, Integer> costs = new HashMap<>();
        for (int x = 1; x <= 4; x++) {

        }
        Settings.setCosts(costs);

        Map<Integer, Integer[]> dropChances = new HashMap<>();
        Settings.setDropChances(dropChances);
    }

    private void saveFile(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
