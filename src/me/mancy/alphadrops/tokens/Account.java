package me.mancy.alphadrops.tokens;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class Account {

    private Player player;

    //Tier, Balance
    private Map<Integer, Integer> balances = new HashMap<>();

    public Account(Player player, int startBalance) {
        this.player = player;
        for (int x = 1; x <= 4; x++) {
            this.balances.putIfAbsent(x, startBalance);
        }
    }

    public void addTokens(int tier, int amount) {
        this.balances.put(tier, this.balances.get(tier) + amount);
    }

    public void removeTokens(int tier, int amount) {
        if (this.balances.get(tier) - amount < 0) {
            this.balances.put(tier, 0);
        } else {
            this.balances.put(tier, this.balances.get(tier) - amount);
        }
    }

    public void setBalance(int tier, int amount) {
        if (amount < 0) {
            this.balances.put(tier, 0);
        } else {
            this.balances.put(tier, amount);
        }
    }

    public Integer getBalance(int tier) {
        return this.balance;
    }

    public void resetBalance(int tier) {
        this.balances.put(tier, 0);
    }

    public void resetAllBalances() {
        for (int x = 1; x <= 4; x++) {
            this.balances.put(x, 0);
        }
    }

    public Player getPlayer() {
        return this.player;
    }

}
