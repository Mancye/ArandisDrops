package me.mancy.arandisdrops.menus.editor.itemlists;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

public class LegendaryItemsMenu implements Listener {

    private final Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.AQUA + "Legendary Items");

    public LegendaryItemsMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void saveItems(InventoryCloseEvent event) {
        if (event.getInventory().equals(inventory)) {
            Settings.getItemLists().put(5, Arrays.asList(inventory.getContents()));
        }
    }

}
