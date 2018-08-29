package me.mancy.arandisdrops.menus.editor;


import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class EditItemListsMenu extends Menu {

    private final Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Edit Item Lists");

    EditItemListsMenu() {
        setUp();
    }

    @Override
    protected Inventory getInventory() {
        return inventory;
    }

    @Override
    protected void setUp() {
        setButton(11, Material.FEATHER, ChatColor.AQUA + "Common", new ArrayList<>());
        setButton(12, Material.FEATHER, ChatColor.AQUA + "Uncommon", new ArrayList<>());
        setButton(13, Material.FEATHER, ChatColor.AQUA + "Rare", new ArrayList<>());
        setButton(14, Material.FEATHER, ChatColor.AQUA + "Epic", new ArrayList<>());
        setButton(15, Material.FEATHER, ChatColor.AQUA + "Legendary", new ArrayList<>());
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player player) {

        switch (slot) {
            case 11:
                player.openInventory(new ItemList("Common").getInventory());
                break;
            case 12:
                player.openInventory(new ItemList("Uncommon").getInventory());
                break;
            case 13:
                player.openInventory(new ItemList("Rare").getInventory());
                break;
            case 14:
                player.openInventory(new ItemList("Epic").getInventory());
                break;
            case 15:
                player.openInventory(new ItemList("Legendary").getInventory());
                break;
        }

    }



}
