package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class TierSettingsMenu extends Menu {

    private final Inventory menu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Tier Settings");

    TierSettingsMenu() {
        this.setUp();
    }

    @Override
    protected Inventory getInventory() {
        return this.menu;
    }

    @Override
    protected void setUp() {
        setButton(10, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 1 Settings", null);

        setButton(12, Material.IRON_BLOCK, ChatColor.DARK_GRAY + "Tier 2 Settings", null);

        setButton(14, Material.GOLD_BLOCK, ChatColor.DARK_GRAY + "Tier 3 Settings", null);

        setButton(16, Material.DIAMOND_BLOCK, ChatColor.DARK_GRAY + "Tier 4 Settings", null);
        setExitButton(18);
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player player) {
        switch (slot) {
            case 10:
                break;
            case 12:
                break;
            case 14:
                break;
            case 16:
                break;
        }
    }
}
