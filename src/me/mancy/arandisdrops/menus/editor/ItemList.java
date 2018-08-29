package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ItemList implements Listener {
    private Inventory inventory = Bukkit.createInventory(null, 54, "Loading...");

    public ItemList(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    ItemList(String rarity) {
        this.inventory = Bukkit.createInventory(null, 54, rarity + " Item List");
    }

    @EventHandler
    private void loadItems(InventoryOpenEvent event) {
        if (event.getInventory() != null) {
            if (event.getInventory().getName() != null) {
                if (event.getInventory().getName().contains("Item List") && !event.getInventory().getName().contains("Lists")) {
                    Inventory inventory = event.getInventory();
                    if (event.getInventory().getName().contains("Common") && !event.getInventory().getName().contains("Un")) {
                        for (ItemStack i : Settings.getItemLists().get(1)) {
                            inventory.addItem(i);
                        }
                    } else if (event.getInventory().getName().contains("Uncommon")) {
                        for (ItemStack i : Settings.getItemLists().get(2)) {
                            inventory.addItem(i);
                        }
                    } else if (event.getInventory().getName().contains("Rare")) {
                        for (ItemStack i : Settings.getItemLists().get(3)) {
                            inventory.addItem(i);
                        }
                    } else if (event.getInventory().getName().contains("Epic")) {
                        for (ItemStack i : Settings.getItemLists().get(4)) {
                            inventory.addItem(i);
                        }
                    } else if (event.getInventory().getName().contains("Legendary")) {
                        for (ItemStack i : Settings.getItemLists().get(5)) {
                            inventory.addItem(i);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void saveItems(InventoryCloseEvent event) {
        if (event.getInventory() != null) {
            if (event.getInventory().getName() != null) {
                if (event.getInventory().getName().contains("Item List") && !event.getInventory().getName().contains("Lists")) {
                    List<ItemStack> itemStackList = new ArrayList<>();
                    for (int x = 0; x < event.getInventory().getSize(); x++) {
                        if (event.getInventory().getItem(x) != null)
                            itemStackList.add(event.getInventory().getItem(x));
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
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
