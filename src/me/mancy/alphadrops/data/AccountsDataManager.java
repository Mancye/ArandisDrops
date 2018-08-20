package me.mancy.alphadrops.data;

import me.mancy.alphadrops.tokens.Account;
import me.mancy.alphadrops.tokens.AccountManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AccountsDataManager {

    private final FileConfiguration configuration;
    private final File file;

    public AccountsDataManager(FileConfiguration configurationFile, File file) {
        this.configuration = configurationFile;
        this.file = file;
    }

    public void saveAccounts() {
        for (int x = 0; x < AccountManager.getAccounts().size(); x++) {
            this.configuration.set(x + ". uuid", AccountManager.getAccounts().get(x).getPlayer().getUniqueId().toString());
            saveFile(this.configuration, file);
            for (int y = 1; y <= 4; y++) {
                this.configuration.set(x + ". tier " + y + " tokens", AccountManager.getAccounts().get(x).getBalance(y));
                saveFile(this.configuration, file);
            }

        }

        this.configuration.set("Amount Of Accounts", AccountManager.getAccounts().size());
    }

    public void loadAccounts() {
        int amtAccounts = 0;
        if (this.configuration.contains("Amount Of Accounts")) {
            amtAccounts = this.configuration.getInt("Amount Of Accounts");
        } else {
            this.configuration.set("Amount Of Accounts", 0);
        }

        for (int x = 0; x < amtAccounts; x++) {
            UUID uuid = null;
            if (this.configuration.contains(x + ". uuid")) {
                uuid = UUID.fromString(this.configuration.get(x + ". uuid").toString());
            }
            if (Bukkit.getServer().getPlayer(uuid) != null) {
                Account account = new Account(Bukkit.getPlayer(uuid), 0);
                for (int y = 1; y <= 4; y++) {
                    if (this.configuration.contains(x + ". tier " + y + " tokens")) {
                        account.setBalance(y, configuration.getInt(x + ". tier " + y + " tokens"));
                    }
                }
            }

        }
    }

    private void saveFile(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
