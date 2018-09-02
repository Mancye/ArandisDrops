package me.mancy.arandisdrops.tokens;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class AccountManager {

    private static Set<Account> accounts = new HashSet<>();

    public static Set<Account> getAccounts() {
        return accounts;
    }

    public static void setAccounts(Set<Account> accounts) {
        AccountManager.accounts = accounts;
    }

    static void registerAccount(Account account) {
        accounts.add(account);
    }

    public static Account getPlayersAccount(Player player) {
        for (Account account : accounts) {
            if (account == null)
                return null;
            if (account.getPlayerUUID() == null)
                return null;
            if (account.getPlayerUUID().equals(player.getUniqueId()))
                return account;
        }
        return null;
    }


    public static Account getOfflineAccount(OfflinePlayer player) {
        for (Account account : accounts) {
            if (account == null)
                return null;
            if (account.getPlayerUUID() == null)
                return null;
            if (account.getPlayerUUID().equals(player.getUniqueId()))
                return account;
        }
        return null;
    }


}
