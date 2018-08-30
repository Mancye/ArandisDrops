package me.mancy.arandisdrops.tokens;

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

    public static void registerAccount(Account account) {
        accounts.add(account);
    }

    public static Account getPlayersAccount(Player player) {
        for (Account account : accounts) {
            if (account.getPlayer() == player)
                return account;
        }
        return null;
    }



}
