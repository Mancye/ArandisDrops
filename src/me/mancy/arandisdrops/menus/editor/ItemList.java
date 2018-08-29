package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class ItemList implements Listener {
    private Inventory inventory;

    public ItemList(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    ItemList(String rarity) {
        inventory = Bukkit.createInventory(null, 54, rarity + " Item List");
    }

    @EventHandler
    private void saveItems(InventoryCloseEvent event) {
        if (event.getInventory().getName().contains("Item List")) {
            List<ItemStack> itemStackList = Arrays.asList(event.getInventory().getContents());
            for (ItemStack i : itemStackList) {
                if (i == null || i.getType() == Material.AIR) {
                    itemStackList.remove(i);
                }
            }
            if (event.getInventory().getName().contains("Common") && !event.getInventory().getName().contains("Un")) {
                Settings.getItemLists().put(1, itemStackList);
            } else if (event.getInventory().getName().contains("Uncommon")) {
                Settings.getItemLists().put(2, itemStackList);
            } else if (event.getInventory().getName().contains("Rare")) {
                Settings.getItemLists().put(3, itemStackList);
            } else if (event.getInventory().getName().contains("Epic")) {
                Settings.getItemLists().put(4, itemStackList);
            } else if (event.getInventory().getName().contains("Legendary")) {
                Settings.getItemLists().put(5, itemStackList);
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
