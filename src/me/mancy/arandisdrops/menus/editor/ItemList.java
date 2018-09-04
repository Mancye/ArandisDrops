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

    private Main plugin;

    public ItemList(Main main) {
        this.plugin = main;
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
                        if (Settings.getItemLists().get(1).size() > 0)
                            for (ItemStack i : Settings.getItemLists().get(1)) {
                                if (i != null)
                                    inventory.addItem(i);
                            }
                    } else if (inventory.getName().contains("Uncommon")) {
                        for (ItemStack i : Settings.getItemLists().get(2)) {
                            if (i != null)
                                inventory.addItem(i);
                        }

                    } else if (inventory.getName().contains("Rare")) {
                        for (ItemStack i : Settings.getItemLists().get(3)) {
                            if (i != null)
                                inventory.addItem(i);
                        }

                    } else if (inventory.getName().contains("Epic")) {
                        for (ItemStack i : Settings.getItemLists().get(4)) {
                            if (i != null)
                                inventory.addItem(i);
                        }

                    } else if (inventory.getName().contains("Legendary")) {
                        for (ItemStack i : Settings.getItemLists().get(5)) {
                            if (i != null)
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
                    ItemStack[] contents = event.getInventory().getContents();
                    List<ItemStack> itemStacks = new ArrayList<>();
                    for (ItemStack i : contents) {
                        if (i != null)
                            itemStacks.add(i);
                    }
                    if (event.getInventory().getName().contains("Common") && !event.getInventory().getName().contains("Un")) {
                        Settings.getItemLists().put(1, itemStacks);
                    } else if (event.getInventory().getName().contains("Uncommon")) {
                        Settings.getItemLists().put(2, itemStacks);
                    } else if (event.getInventory().getName().contains("Rare")) {
                        Settings.getItemLists().put(3, itemStacks);
                    } else if (event.getInventory().getName().contains("Epic")) {
                        Settings.getItemLists().put(4, itemStacks);
                    } else if (event.getInventory().getName().contains("Legendary")) {
                        Settings.getItemLists().put(5, itemStacks);
                    }
                    Bukkit.getScheduler().runTaskLater(plugin, () -> {
                        event.getPlayer().openInventory(new EditItemListsMenu().getInventory());
                    }, 5L);
                }
            }
        }
    }

    public Inventory getInventory() {
        return inventory;
    }
}
