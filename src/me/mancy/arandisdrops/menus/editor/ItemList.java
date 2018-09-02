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

import java.util.*;

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
                    if (inventory.getName().contains("Common") && !inventory.getName().contains("Un")) {
                        inventory.setContents((ItemStack[]) Settings.getItemLists().get(1).toArray());
                    } else if (inventory.getName().contains("Uncommon")) {
                        inventory.setContents((ItemStack[]) Settings.getItemLists().get(2).toArray());
                    } else if (inventory.getName().contains("Rare")) {
                        inventory.setContents((ItemStack[]) Settings.getItemLists().get(3).toArray());
                    } else if (inventory.getName().contains("Epic"))
                        inventory.setContents((ItemStack[]) Settings.getItemLists().get(4).toArray());
                    } else if (inventory.getName().contains("Legendary")) {
                        inventory.setContents((ItemStack[]) Settings.getItemLists().get(5).toArray());
                    }
                }
            }
        }

    @EventHandler
    private void saveItems(InventoryCloseEvent event) {
        if (event.getInventory() != null) {
            if (event.getInventory().getName() != null) {
                if (event.getInventory().getName().contains("Item List") && !event.getInventory().getName().contains("Lists")) {
                    List<ItemStack> itemStackList = new ArrayList<>(Arrays.asList(event.getInventory().getContents()));
                    itemStackList.removeIf(Objects::isNull);
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
                    event.getPlayer().openInventory(new GlobalSettingsMenu().getInventory());
                }
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
