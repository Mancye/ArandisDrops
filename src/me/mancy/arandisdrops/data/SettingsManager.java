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
    private Settings settings = Settings.getInstance();

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
        settingsConfiguration.set("Drop Radius", settings.getDropRadius());
        saveFile(settingsConfiguration, settingsFile);
        settingsConfiguration.set("Countdown Time", settings.getCountdownTime());
        saveFile(settingsConfiguration, settingsFile);
        settingsConfiguration.set("Drop Height", settings.getDropHeight());
        saveFile(settingsConfiguration, settingsFile);

        String[] rarities = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};
        for (int x : settings.getItemLists().keySet()) {
            settingsConfiguration.set("Item Lists." + rarities[x - 1], settings.getItemLists().get(x));
            saveFile(settingsConfiguration, settingsFile);
        }

        for (int x : settings.getCosts().keySet()) {
            settingsConfiguration.set("Costs.Tier " + x, settings.getCosts().get(x));
            saveFile(settingsConfiguration, settingsFile);
        }

        for (int x : settings.getDropChances().keySet()) {
            settingsConfiguration.set("Drop Chances.Tier " + x, settings.getDropChances().get(x));
            saveFile(settingsConfiguration, settingsFile);
        }

    }

    public void loadSettings() {
        settings.setDropRadius(settingsConfiguration.getDouble("Drop Radius"));
        settings.setCountdownTime(settingsConfiguration.getInt("Countdown Time"));
        settings.setDropHeight(settingsConfiguration.getDouble("Drop Height"));

        Map<Integer, List<ItemStack>> itemList = new HashMap<>();
        String[] rarities = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};
        for (int x = 1; x <= 5; x++) {
            if (!settingsConfiguration.contains("Item Lists." + rarities[x - 1]))
                settingsConfiguration.set("Item Lists." + rarities[x - 1], new ItemStack[]{});

            itemList.put(x, (List<ItemStack>) settingsConfiguration.getList("Item Lists." + rarities[x - 1]));
        }
        settings.setItemLists(itemList);

        Map<Integer, Integer> costs = new HashMap<>();
        for (int x = 1; x <= 4; x++) {
            if (!settingsConfiguration.contains("Costs.Tier " + x))
                settingsConfiguration.set("Costs.Tier " + x, 0);
            costs.put(x, settingsConfiguration.getInt("Costs.Tier " + x));
        }
        settings.setCosts(costs);

        Map<Integer, Integer[]> dropChances = new HashMap<>();

        for (int x = 1; x <= 4; x++) {
            if (!settingsConfiguration.contains("Drop Chances.Tier " + x))
                settingsConfiguration.set("Drop Chances.Tier " + x, null);
            dropChances.put(x, settingsConfiguration.getIntegerList("Drop Chances.Tier " + x).toArray(new Integer[settingsConfiguration.getIntegerList("Drop Chances.Tier " + x).size()]));
        }
        settings.setDropChances(dropChances);
    }

    private void saveFile(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
