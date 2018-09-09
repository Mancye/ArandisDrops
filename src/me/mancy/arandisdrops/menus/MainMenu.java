package me.mancy.arandisdrops.menus;

import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.parties.Countdown;
import me.mancy.arandisdrops.parties.DropPartyManager;
import me.mancy.arandisdrops.parties.LocationManager;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import me.mancy.arandisdrops.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu implements Listener {

    private Inventory menu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Start a Drop Party");

    public MainMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @Override
    public Inventory getInventory() {
        return this.menu;
    }

    @Override
    public void setUp() {
        setButton(10, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 1", new ArrayList<>());
        setButton(12, Material.IRON_BLOCK, ChatColor.GRAY + "Tier 2", new ArrayList<>());
        setButton(14, Material.GOLD_BLOCK, ChatColor.GOLD + "Tier 3", new ArrayList<>());
        setButton(16, Material.DIAMOND_BLOCK, ChatColor.AQUA + "Tier 4", new ArrayList<>());
        setExitButton(18);
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    private List<String> getTierLore(int tier, Player p) {
        List<String> lore = new ArrayList<>();
        Account account = AccountManager.getPlayersAccount(p);
        if (account == null)
            return null;
        ChatColor color;
        if (account.getBalance(tier) >= Settings.getCosts().get(tier))
            color = ChatColor.GREEN;
        else
            color = ChatColor.RED;
        lore.add(color + ChatColor.ITALIC.toString() + "Click To Start A Tier " + tier + " Drop Party!");
        lore.add(color + ChatColor.ITALIC.toString() + "COST: " + Settings.getCosts().get(tier) + " Token(s)");
        return lore;
    }

    public MainMenu() {
        setUp();
    }

    public void displayTokens(Player p) {
        if (AccountManager.getPlayersAccount(p) == null) return;

        Account account = AccountManager.getPlayersAccount(p);
        if (account == null)
            return;

        List<String> tokensDesc = new ArrayList<>();
        for (int x = 1; x <= 4; x++) {
            if (account.getBalance(x) != null)
                tokensDesc.add(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Tier " + x + ": " + ChatColor.AQUA + account.getBalance(x));
        }
        setButton(26, Material.BOOK, ChatColor.GRAY + "Your Tokens:", tokensDesc);

    }

    @Override
    protected void handleInput(int slot, Player player) {
        Account account = AccountManager.getPlayersAccount(player);
        if (account == null) return;
        if (slot % 2 == 0 && slot >= 10 && slot <= 18) {
            if (DropPartyManager.isActiveDropParty()) {
                player.closeInventory();
                player.sendMessage(Messager.toFormatted(Strings.alreadyActive.trim()));
            } else if (LocationManager.getValidatedLocations().isEmpty()) {
                player.closeInventory();
                Messager.sendMessage(player, ChatColor.RED + "Error: No locations set");
            } else if (Settings.getItemLists().get(1).size() == 0 && Settings.getItemLists().get(2).size() == 0 && Settings.getItemLists().get(3).size() == 0 && Settings.getItemLists().get(4).size() == 0 && Settings.getItemLists().get(5).size() == 0) {
                player.closeInventory();
                Messager.sendMessage(player, ChatColor.RED + "Error: No items to drop");
            } else {
                Countdown countdown = new Countdown();
                int tier = Integer.parseInt(getInventory().getItem(slot).getItemMeta().getDisplayName().charAt(7) + "");

                if (account.getBalance(tier) >= Settings.getCosts().get(tier)) {
                    account.removeTokens(tier, Settings.getCosts().get(tier));
                    countdown.setTimer(Settings.getCountdownTime(), tier);
                    countdown.startTimer(tier);
                    player.closeInventory();
                } else {
                    player.closeInventory();
                    player.sendMessage(Messager.toFormatted(Strings.insufficientBalance.trim()));
                }
            }
        }

    }

    public void displayPriceDesc(Player p) {
        setButton(10, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 1", getTierLore(1, p));
        setButton(12, Material.IRON_BLOCK, ChatColor.GRAY + "Tier 2", getTierLore(2, p));
        setButton(14, Material.GOLD_BLOCK, ChatColor.GOLD + "Tier 3", getTierLore(3, p));
        setButton(16, Material.DIAMOND_BLOCK, ChatColor.AQUA + "Tier 4", getTierLore(4, p));
    }
}
