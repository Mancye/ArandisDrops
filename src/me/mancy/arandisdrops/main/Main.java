package me.mancy.arandisdrops.main;

import me.mancy.arandisdrops.commands.BaseCMD;
import me.mancy.arandisdrops.data.AccountsDataManager;
import me.mancy.arandisdrops.data.SettingsManager;
import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.menus.MainMenu;
import me.mancy.arandisdrops.menus.editor.EditorMainMenu;
import me.mancy.arandisdrops.menus.editor.itemlists.*;
import me.mancy.arandisdrops.parties.DropParty;
import me.mancy.arandisdrops.tokens.AccountSetup;
import me.mancy.arandisdrops.utils.FormattedMessage;
import me.mancy.arandisdrops.utils.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private final File settingsFile = new File(this.getDataFolder() + "/settings.yml");
    private final FileConfiguration settingsConfig = YamlConfiguration.loadConfiguration(settingsFile);

    private final File accountsFile = new File(this.getDataFolder() + "/accounts.yml");
    private final FileConfiguration accountsConfig = YamlConfiguration.loadConfiguration(accountsFile);

    private final File locationsFile = new File(this.getDataFolder() + "/locations.yml");
    private final FileConfiguration locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);

    private final File stringsFile = new File(this.getDataFolder() + "/strings.yml");
    private final FileConfiguration stringsConfig = YamlConfiguration.loadConfiguration(stringsFile);

    private final SettingsManager settings = new SettingsManager(settingsFile, settingsConfig);

    @Override
    public void onEnable() {
        settings.loadSettings();
        new Strings(stringsConfig);
        new AccountsDataManager(accountsConfig, accountsFile).loadAccounts();
        registerCommands();
        registerListeners();
        Bukkit.getConsoleSender().sendMessage(new FormattedMessage(ChatColor.GREEN + this.getDescription().getName()).toString() + " Was Successfully Enabled");
    }

    @Override
    public void onDisable() {
        settings.saveSettings();
        new AccountsDataManager(accountsConfig, accountsFile).saveAccounts();
        Bukkit.getConsoleSender().sendMessage(new FormattedMessage(ChatColor.RED + this.getDescription().getName()).toString() + " Was Successfully Disabled");
    }


    private void registerListeners() {
        new AccountSetup(this);
        new MenuListener(this);
        new DropParty(this);

        new CommonItemsMenu(this);
        new UnCommonItemsMenu(this);
        new RareItemsMenu(this);
        new EpicItemsMenu(this);
        new LegendaryItemsMenu(this);
    }

    private void registerCommands() {
        this.getCommand("drops").setExecutor(new BaseCMD());
    }

}
