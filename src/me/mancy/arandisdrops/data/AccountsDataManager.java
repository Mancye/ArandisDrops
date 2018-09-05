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

    private static File accountsFile;
    private static FileConfiguration accountsConfig;
    private static Main plugin;

    public AccountsDataManager(Main main) {
        plugin = main;
        saveDefaultConfig();
        accountsConfig = YamlConfiguration.loadConfiguration(accountsFile);
    }

    public static void safeSave() {
        AccountManager.getAccounts().removeIf(Objects::isNull);
        accountsConfig.set("Account List", Arrays.asList(AccountManager.getAccounts().toArray()));
        saveFile(accountsConfig, accountsFile);
    }

    public void saveAccounts() {
        AccountManager.getAccounts().removeIf(Objects::isNull);
        accountsConfig.set("Account List", Arrays.asList(AccountManager.getAccounts().toArray()));
        saveFile(accountsConfig, accountsFile);
    }

    public void loadAccounts() {
        if (accountsConfig.getList("Account List") != null && !accountsConfig.getList("Account List").isEmpty()) {
            final List<Account> list = (List<Account>) accountsConfig.getList("Account List");
            list.removeIf(Objects::isNull);
            final Set<Account> accountsSet = new HashSet<>(list);
            accountsSet.removeIf(Objects::isNull);
            AccountManager.setAccounts(accountsSet);
        } else {
            AccountManager.setAccounts(new HashSet<>());
        }
    }

    private static void saveFile(FileConfiguration ymlConfig, File ymlFile) {
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
