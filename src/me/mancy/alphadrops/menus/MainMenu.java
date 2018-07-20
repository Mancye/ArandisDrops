package me.mancy.alphadrops.menus;

import me.mancy.alphadrops.main.Main;
import me.mancy.alphadrops.tokens.Account;
import me.mancy.alphadrops.tokens.AccountManager;
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

    private ItemMeta dropItemMeta = null;
    private List<String> dropItemDescription = new ArrayList<>();

    public MainMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    public Inventory getMainMenu() {
        Inventory menu = Bukkit.createInventory(null, 27, ChatColor.RED + "Start a Drop Party!");
        ItemStack[] dropParties = {new ItemStack(Material.COAL_ORE), new ItemStack(Material.IRON_BLOCK), new ItemStack(Material.GOLD_BLOCK), new ItemStack(Material.DIAMOND_BLOCK)};
        for (int x = 0; x <= dropParties.length; x++) {
            ItemStack item = dropParties[x];
            if (item == null) item = new ItemStack(Material.DIAMOND_BLOCK);
            dropItemMeta = item.getItemMeta();
            dropItemMeta.setDisplayName(ChatColor.RED + ChatColor.ITALIC.toString() + "Click To Start A Tier " + x + " Drop Party!");
            dropItemMeta.setLore(dropItemDescription);
            item.setItemMeta(dropItemMeta);
        }
        return menu;
    }

    @EventHandler
    private void displayBalance(InventoryOpenEvent event) {

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
