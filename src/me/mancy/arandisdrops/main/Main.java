package me.mancy.arandisdrops.main;

import me.mancy.arandisdrops.commands.BaseCMD;
import me.mancy.arandisdrops.commands.DTokensCMD;
import me.mancy.arandisdrops.data.*;
import me.mancy.arandisdrops.menus.MainMenu;
import me.mancy.arandisdrops.menus.editor.ItemList;
import me.mancy.arandisdrops.menus.editor.listeners.EditValueChatHandler;
import me.mancy.arandisdrops.parties.Countdown;
import me.mancy.arandisdrops.parties.DropLocation;
import me.mancy.arandisdrops.parties.DropParty;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import me.mancy.arandisdrops.tokens.AccountSetup;
import me.mancy.arandisdrops.tokens.listener.TokenChangeHandler;
import me.mancy.arandisdrops.utils.FormattedMessage;
import me.mancy.arandisdrops.utils.MenuListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();
        loadData();
        loadAccounts();
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
        new ParticlesDataManager(this).loadParticles();
        new AccountsDataManager(this).loadAccounts();
        new LocationDataManager(this).loadLocations();
    }

    private void saveData() {
        new SettingsManager(this).saveSettings();
        new AccountsDataManager(this).saveAccounts();
        new LocationDataManager(this).saveLocations();
    }

    private void registerListeners() {
        new TokenChangeHandler(this);
        new AccountSetup(this);
        new MenuListener(this);
        new DropParty(this);
        new EditValueChatHandler(this);
        new ItemList(this);
        new MainMenu(this);
        new DropLocation(this);
        new Countdown(this);
    }

    private void loadAccounts() {
        getServer().getOnlinePlayers().stream()
                .filter(p -> AccountManager.getPlayersAccount(p) == null)
                .forEach(p -> new Account(p.getUniqueId(), 0));
    }

    private void registerCommands() {
        this.getCommand("drops").setExecutor(new BaseCMD());
        this.getCommand("dtokens").setExecutor(new DTokensCMD());
    }

}
