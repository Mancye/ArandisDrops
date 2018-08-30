package me.mancy.arandisdrops.main;

import me.mancy.arandisdrops.commands.BaseCMD;
import me.mancy.arandisdrops.data.AccountsDataManager;
import me.mancy.arandisdrops.data.LocationDataManager;
import me.mancy.arandisdrops.data.SettingsManager;
import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.menus.MainMenu;
import me.mancy.arandisdrops.menus.editor.ItemList;
import me.mancy.arandisdrops.menus.editor.listeners.EditValueChatHandler;
import me.mancy.arandisdrops.parties.DropParty;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountSetup;
import me.mancy.arandisdrops.utils.FormattedMessage;
import me.mancy.arandisdrops.utils.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private final File locationsFile = new File(this.getDataFolder() + "/locations.yml");
    private final FileConfiguration locationsConfig = YamlConfiguration.loadConfiguration(locationsFile);

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();
        loadData();
        Bukkit.getConsoleSender().sendMessage(new FormattedMessage(ChatColor.GREEN + this.getDescription().getName()).toString() + " Was Successfully Enabled");
    }

    @Override
    public void onDisable() {
        saveData();
        Bukkit.getConsoleSender().sendMessage(new FormattedMessage(ChatColor.RED + this.getDescription().getName()).toString() + " Was Successfully Disabled");
    }

    private void loadData() {
        ConfigurationSerialization.registerClass(Account.class, "Account");
        new SettingsManager(this).loadSettings();
        new Strings(this).loadStrings();
        new AccountsDataManager(this).loadAccounts();
        new LocationDataManager(this).loadLocations();
    }

    private void saveData() {
        new SettingsManager(this).saveSettings();
        new AccountsDataManager(this).saveAccounts();
        new LocationDataManager(this).saveLocations();
    }

    private void registerListeners() {
        new AccountSetup(this);
        new MenuListener(this);
        new DropParty(this);
        new EditValueChatHandler(this);
        new ItemList(this);
        new MainMenu(this);
    }

    private void registerCommands() {
        this.getCommand("drops").setExecutor(new BaseCMD());
        this.getCommand("dtokens").setExecutor(new BaseCMD());
    }

}
