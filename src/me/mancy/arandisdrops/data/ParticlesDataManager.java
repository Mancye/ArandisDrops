package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ParticlesDataManager {

    private static File particleFile;
    private static FileConfiguration particleConfig;
    private Main plugin;

    public ParticlesDataManager(Main main) {
        plugin = main;
        saveDefaultConfig();
        ParticlesDataManager.particleConfig = YamlConfiguration.loadConfiguration(particleFile);
    }

    private static void reloadParticles() {
        Map<String, Integer[]> particles = new HashMap<>();
        String[] rarities = {"Common", "Uncommon", "Rare", "Epic", "Legendary"};

        for (int x = 0; x < rarities.length; x++) {
            particles.put(rarities[x], particleConfig.getIntegerList("Particles." + rarities[x]).toArray(new Integer[particleConfig.getIntegerList("Particles." + rarities[x]).size()]));
        }
        Particles.particles = particles;
      }

    public void loadParticles() {
        reloadParticles();
    }

    public static void reloadConfig() {
        particleConfig = YamlConfiguration.loadConfiguration(particleFile);
        reloadParticles();
    }

    private void saveDefaultConfig() {
        if (particleFile == null) {
            particleFile = new File(plugin.getDataFolder(), "particles.yml");
        }
        if (!particleFile.exists()) {
            plugin.saveResource("particles.yml", false);
        }
    }

}
