package me.mancy.arandisdrops.data;

import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
        List<Account> badAccounts = new ArrayList<>();
        for (Account account : AccountManager.getAccounts()) {
            if (account == null) {
                badAccounts.add(account);
            }
        }
        accountsConfig.set("Account List", Arrays.asList(AccountManager.getAccounts().toArray()));
        saveFile(accountsConfig, accountsFile);
    }

    public void loadAccounts() {
        if (accountsConfig.getList("Account List") != null && !accountsConfig.getList("Account List").isEmpty()) {
            AccountManager.setAccounts(new HashSet<>((List<Account>) accountsConfig.getList("Account List")));
        } else {
            AccountManager.setAccounts(new HashSet<>());
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
