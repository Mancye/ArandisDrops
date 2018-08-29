package me.mancy.arandisdrops.menus;

import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends Menu {

    private Inventory menu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Start a Drop Party");

    @Override
    public Inventory getInventory() {
        return this.menu;
    }

    @Override
    public void setUp() {
        setButton(10, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 1", getTierLore(1));
        setButton(12, Material.IRON_BLOCK, ChatColor.GRAY + "Tier 2", getTierLore(2));
        setButton(14, Material.GOLD_BLOCK, ChatColor.GOLD + "Tier 3", getTierLore(3));
        setButton(16, Material.DIAMOND_BLOCK, ChatColor.AQUA + "Tier 4", getTierLore(4));
        setExitButton(18);
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    private List<String> getTierLore(int tier) {
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.RED + ChatColor.ITALIC.toString() + "Click To Start A Tier " + tier + " Drop Party!");
        lore.add(ChatColor.RED + ChatColor.ITALIC.toString() + "COST: " + Settings.getCosts().get(tier) + " Token(s)");
        return lore;
    }

    public MainMenu() {
        setUp();
    }

    @EventHandler
    private void updateMenu(InventoryOpenEvent event) {
        if (!event.getInventory().getName().contains("Start a Drop Party")) return;
        if (!(event.getPlayer() instanceof Player)) return;
        Player p = (Player) event.getPlayer();
        if (AccountManager.getAccount(p) == null) return;

        Account account = AccountManager.getAccount(p);


        List<String> tokensDesc = new ArrayList<>();
        for (int x = 1; x <= 4; x++) {
            if (account.getBalance(x) != null)
            tokensDesc.add(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Tier " + x + ": " + ChatColor.GREEN + account.getBalance(x));
        }
        setButton(26, Material.BOOK, ChatColor.GRAY + "Your Tokens:", tokensDesc);
    }

    @Override
    protected void handleInput(int slot, Player player) {
        Account account = AccountManager.getAccount(player);
        if (account == null) return;

        switch (slot) {
            case 10:
                System.out.println("Start");
                if (account.getBalance(1) >= Settings.getCosts().get(1))
                    System.out.println("Start");
                break;
            case 12:
                if (account.getBalance(2) >= Settings.getCosts().get(2))
                    System.out.println("Start");
                break;
            case 14:
                if (account.getBalance(3) >= Settings.getCosts().get(3))
                    System.out.println("Start");
                break;
            case 16:
                if (account.getBalance(4) >= Settings.getCosts().get(4))
                    System.out.println("Start");
                break;
            case 18:
                player.closeInventory();
                break;
        }

    }

}
