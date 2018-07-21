package me.mancy.alphadrops.main;

import me.mancy.alphadrops.menus.MainMenu;
import me.mancy.alphadrops.tokens.Account;
import me.mancy.alphadrops.tokens.AccountManager;
import me.mancy.alphadrops.tokens.AccountSetup;
import me.mancy.alphadrops.tokens.DropCosts;
import me.mancy.alphadrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Main extends JavaPlugin {

    private final File accountsFile = new File(this.getDataFolder() + "/accounts.yml");
    private final FileConfiguration accountsConfig = YamlConfiguration.loadConfiguration(accountsFile);

    private final File dpSettingsFile = new File(this.getDataFolder() + "/dpsettings.yml");
    private final FileConfiguration dpSettingsConfig = YamlConfiguration.loadConfiguration(dpSettingsFile);

    private final File dropLocationsFile = new File(this.getDataFolder() + "/droplocations.yml");
    private final FileConfiguration dropLocationsConfig = YamlConfiguration.loadConfiguration(dropLocationsFile);

    private final File dropItemsFile = new File(this.getDataFolder() + "/dropitems.yml");
    private final FileConfiguration dropItemsConfig = YamlConfiguration.loadConfiguration(dropItemsFile);

    @Override
    public void onEnable() {
        loadAccounts();
        loadCosts();
        registerListeners();
        Bukkit.getConsoleSender().sendMessage(new FormattedMessage(ChatColor.GREEN + this.getDescription().getName()).toString() + " Was Successfully Enabled");
    }

    @Override
    public void onDisable() {
        saveAccounts();
        saveCosts();
        Bukkit.getConsoleSender().sendMessage(new FormattedMessage(ChatColor.RED + this.getDescription().getName()).toString() + " Was Successfully Disabled");
    }

    private void saveAccounts() {
        for (int x = 0; x < AccountManager.getAccounts().size(); x++) {
            accountsConfig.set(x + ". uuid", AccountManager.getAccounts().get(x).getPlayer().getUniqueId().toString());
            saveFile(accountsConfig, accountsFile);
            for (int y = 1; y <= 4; y++) {
                accountsConfig.set(x + ". tier " + y + " tokens", AccountManager.getAccounts().get(x).getBalance(y));
                saveFile(accountsConfig, accountsFile);
            }

        }

        this.accountsConfig.set("Amount Of Accounts", AccountManager.getAccounts().size());
    }

    private void loadAccounts() {
        int amtAccounts = 0;
        if (this.accountsConfig.contains("Amount Of Accounts")) {
            amtAccounts = this.accountsConfig.getInt("Amount Of Accounts");
        } else {
            this.accountsConfig.set("Amount Of Accounts", 0);
        }

        for (int x = 0; x < amtAccounts; x++) {
            UUID uuid = null;
            Integer balance = 0;
            if (accountsConfig.contains(x + ". uuid")) {
                uuid = UUID.fromString(accountsConfig.get(x + ". uuid").toString());
            }
            if (Bukkit.getServer().getPlayer(uuid) != null) {
                Account account = new Account(Bukkit.getPlayer(uuid), 0);
                for (int y = 1; y <= 4; y++) {
                    if (accountsConfig.contains(x + ". tier " + y + " tokens")) {
                        account.setBalance(y, accountsConfig.getInt(x + ". tier " + y + " tokens"));
                    }
                }
            }

        }

    }

    private void saveCosts() {
        for (int x = 1; x <= 4; x++) {
            dpSettingsConfig.set("Tier " + x + " Drop Party Cost", DropCosts.getCost(x));
            saveFile(dpSettingsConfig, dpSettingsFile);
        }
    }

    private void loadCosts() {
        for (int x = 1; x <= 4; x++) {
            if (dpSettingsConfig.contains("Tier " + x + " Drop Party Cost")) {
                DropCosts.setCost(x, dpSettingsConfig.getInt("Tier " + x + " Drop Party Cost"));
            } else {
                dpSettingsConfig.set("Tier " + x + " Drop Party Cost", 1);
                saveFile(dpSettingsConfig, dpSettingsFile);
            }
        }
    }

    private void registerListeners() {
        new AccountSetup(this);

        new MainMenu(this);
    }

    private void saveFile(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
