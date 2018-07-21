package me.mancy.alphadrops.menus;

import me.mancy.alphadrops.main.Main;
import me.mancy.alphadrops.tokens.Account;
import me.mancy.alphadrops.tokens.AccountManager;
import me.mancy.alphadrops.tokens.DropCosts;
import me.mancy.alphadrops.utils.MenuUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainMenu implements Listener {

    private Inventory menu;

    public MainMenu() {

    }

    public MainMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    public void openMainMenu(Player player) {

        this.menu = Bukkit.createInventory(null, 27, ChatColor.RED + "Start a Drop Party!");
        ItemStack[] dropParties = {new ItemStack(Material.COAL_ORE), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.DIAMOND_BLOCK)};
        int slotToPut = 10;
        for (int x = 0; x <= dropParties.length; x++) {
            ItemStack item = dropParties[x];
            if (item == null) item = new ItemStack(Material.DIAMOND_BLOCK);
            List<String> desc = new ArrayList<>();
            desc.add(ChatColor.GRAY + "Click to start a tier " + x + " drop party");
            ChatColor costColor;
            if (AccountManager.getAccount(player).getBalance(1) >= DropCosts.getCost(1)) {
                costColor = ChatColor.GREEN;
            } else {
                costColor = ChatColor.RED;
            }
            desc.add(ChatColor.GRAY + "Cost: " + costColor + DropCosts.getCost(1) + " Token(s)");
            MenuUtil.addButton(this.menu, item.getType(), ChatColor.RED + "Tier " + x, desc, slotToPut);
            slotToPut+= 2;
        }
        MenuUtil.fillEmptySlots(this.menu);
        player.openInventory(this.menu);
    }

    @EventHandler
    private void displayBalance(InventoryOpenEvent event) {
        if (!event.getInventory().equals(this.menu)) return;
        if (!(event.getPlayer() instanceof Player)) return;
        Player p = (Player) event.getPlayer();
        if (AccountManager.getAccount(p) == null) return;

        Account account = AccountManager.getAccount(p);
        List<String> tokensDesc = new ArrayList<>();
        for (int x = 1; x <= 4; x++) {
            if (account.getBalance(x) != null)
            tokensDesc.add(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Tier " + x + ": " + ChatColor.GREEN + account.getBalance(x));
        }
        MenuUtil.addButton(event.getInventory(), Material.BOOK, ChatColor.GRAY + "Your Tokens:", tokensDesc, 26);
    }

    @EventHandler
    private void handleInput(InventoryClickEvent event) {
        if (ChatColor.stripColor(event.getClickedInventory().getName()).equalsIgnoreCase("Start a Drop Party!")) {
            if (!(event.getWhoClicked() instanceof Player)) return;
            Player p = (Player) event.getWhoClicked();
            Account account = AccountManager.getAccount(p);
            if (account == null) return;
            if (event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                switch (event.getSlot()) {
                    case 10: {

                    }
                    case 12: {

                    }
                    case 14: {

                    }
                    case 16: {

                    }

                    case 18: {

                    }
                }
            }

        }
    }

}
