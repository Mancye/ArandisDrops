package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.main.Main;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SettingsManager {

    private File settingsFile;
    private FileConfiguration settingsConfiguration;

    private final Main plugin;

    public SettingsManager(Main main) {
        this.plugin = main;
        saveDefaultConfig();
        this.settingsConfiguration = YamlConfiguration.loadConfiguration(settingsFile);
    }


    private void saveDefaultConfig() {
        if (settingsFile == null) {
            settingsFile = new File(plugin.getDataFolder(), "settings.yml");
        }
        if (!settingsFile.exists()) {
            plugin.saveResource("settings.yml", false);
        }
    }

    public void saveSettings() {
        settingsConfiguration.set("Drop Radius", Settings.getDropRadius());
        saveFile(settingsConfiguration, settingsFile);
        settingsConfiguration.set("Countdown Time", Settings.getCountdownTime());
        saveFile(settingsConfiguration, settingsFile);
        settingsConfiguration.set("Drop Height", Settings.getDropHeight());
        saveFile(settingsConfiguration, settingsFile);

        String[] rarities = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};
        for (int x : Settings.getItemLists().keySet()) {
            settingsConfiguration.set("Item Lists." + rarities[x - 1], Settings.getItemLists().get(x));
            saveFile(settingsConfiguration, settingsFile);
        }

        for (int x : Settings.getCosts().keySet()) {
            settingsConfiguration.set("Costs.Tier " + x, Settings.getCosts().get(x));
            saveFile(settingsConfiguration, settingsFile);
        }

        for (int x : Settings.getDropChances().keySet()) {
            settingsConfiguration.set("Drop Chances.Tier " + x, Settings.getDropChances().get(x));
            saveFile(settingsConfiguration, settingsFile);
        }

    }

    public void loadSettings() {
        Settings.setDropRadius(settingsConfiguration.getDouble("Drop Radius"));
        Settings.setCountdownTime(settingsConfiguration.getInt("Countdown Time"));
        Settings.setDropHeight(settingsConfiguration.getDouble("Drop Height"));
        Map<Integer, List<ItemStack>> itemList = new HashMap<>();
        String[] rarities = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};
        for (int x = 1; x <= 5; x++) {
            if (!settingsConfiguration.contains("Item Lists." + rarities[x - 1]))
                settingsConfiguration.set("Item Lists." + rarities[x - 1], new ArrayList<>());

            itemList.put(x, (List<ItemStack>) settingsConfiguration.getList("Item Lists." + rarities[x - 1]));
        }
        Settings.setItemLists(itemList);

        Map<Integer, Integer> costs = new HashMap<>();
        for (int x = 1; x <= 4; x++) {
            if (!settingsConfiguration.contains("Costs.Tier " + x))
                settingsConfiguration.set("Costs.Tier " + x, 0);
            costs.put(x, settingsConfiguration.getInt("Costs.Tier " + x));
        }
        Settings.setCosts(costs);

        Map<Integer, Integer[]> dropChances = new HashMap<>();

        for (int x = 1; x <= 4; x++) {
            if (!settingsConfiguration.contains("Drop Chances.Tier " + x))
                settingsConfiguration.set("Drop Chances.Tier " + x, null);
            dropChances.put(x, settingsConfiguration.getIntegerList("Drop Chances.Tier " + x).toArray(new Integer[settingsConfiguration.getIntegerList("Drop Chances.Tier " + x).size()]));
        }
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
