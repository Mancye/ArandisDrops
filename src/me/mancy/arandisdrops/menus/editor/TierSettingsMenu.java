package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.utils.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TierSettingsMenu extends Menu {

    private int tier;
    private Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Select Setting To Edit");

    @Override
    protected Inventory getInventory() {
        return inventory;
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void handleInput(int slot, Player player) {

    }
}
