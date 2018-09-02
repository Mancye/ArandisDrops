package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ParticlesDataManager {

    private static File particleFile;
    private static FileConfiguration particleConfig;
    private Main plugin;

    public ParticlesDataManager(Main main) {
        plugin = main;
        saveDefaultConfig();
        ParticlesDataManager.particleConfig = YamlConfiguration.loadConfiguration(particleFile);
    }

    public static void reloadParticles() {
        Particles.commonRed = particleConfig.getInt("Common.R");
        Particles.commonGreen = particleConfig.getInt("Common.G");
        Particles.commonBlue = particleConfig.getInt("Common.B");

        Particles.uncommonRed = particleConfig.getInt("Uncommon.R");
        Particles.uncommonGreen = particleConfig.getInt("Uncommon.G");
        Particles.uncommonBlue = particleConfig.getInt("Uncommon.B");

        Particles.rareRed = particleConfig.getInt("Rare.R");
        Particles.rareGreen = particleConfig.getInt("Rare.G");
        Particles.rareBlue = particleConfig.getInt("Rare.B");

        Particles.epicRed = particleConfig.getInt("Epic.R");
        Particles.epicGreen = particleConfig.getInt("Epic.G");
        Particles.epicBlue = particleConfig.getInt("Epic.B");

        Particles.legendaryRed = particleConfig.getInt("Legendary.R");
        Particles.legendaryGreen = particleConfig.getInt("Legendary.G");
        Particles.legendaryBlue = particleConfig.getInt("Legendary.B");
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
