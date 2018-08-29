package me.mancy.arandisdrops.tokens;

import org.bukkit.entity.Player;

import java.util.*;

public class AccountManager {

    private static List<Account> accounts = new ArrayList<>();

    public static void addAccount(Account account) {
        if (account == null) return;
        if (doesAccountExist(account)) return;
        accounts.add(account);
    }

    public static List<Account> getAccounts() {
        return AccountManager.accounts;
    }

    public static void setAccounts(List<Account> accountList) {
        accounts = accountList;
    }

    public static void clearAccounts() {
        accounts.clear();
    }

    public static Account getAccount(Player player) {
        if (doesAccountExist(player)) {
            System.out.println("Does Exist");
            for (Account account : accounts) {
                if (account.getPlayer().equals(player))
                    return account;
            }
        }
        return null;
    }

     private static boolean doesAccountExist(Player player) {
        for (Account account : accounts) {
            if (account.getPlayer().equals(player)) {
                return true;
            }
        }
        return false;
    }

    private static boolean doesAccountExist(Account account) {
       return accounts.contains(account);
    }

}
