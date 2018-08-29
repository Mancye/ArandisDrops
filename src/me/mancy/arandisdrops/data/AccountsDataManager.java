package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountsDataManager {

    private File accountsFile;
    private final FileConfiguration accountsConfig;
    private final Main plugin;

    public AccountsDataManager(Main main) {
        this.plugin = main;
        saveDefaultConfig();
        this.accountsConfig = YamlConfiguration.loadConfiguration(accountsFile);
    }

    public void saveAccounts() {
        accountsConfig.set("Account List", AccountManager.getAccounts());
        saveFile(accountsConfig, accountsFile);
    }

    public void loadAccounts() {
        if (accountsConfig.getList("Account List") != null && !accountsConfig.getList("Account List").isEmpty()) {
            AccountManager.setAccounts((List<Account>) accountsConfig.getList("Account List"));
        } else {
            AccountManager.setAccounts(new ArrayList<>());
        }
    }

    private void saveFile(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveDefaultConfig() {
        if (accountsFile == null) {
            accountsFile = new File(plugin.getDataFolder(), "accounts.yml");
        }
        if (!accountsFile.exists()) {
            plugin.saveResource("accounts.yml", false);
        }
    }

}
