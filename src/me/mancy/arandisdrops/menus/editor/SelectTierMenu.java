package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SelectTierMenu extends Menu {

    private final Inventory menu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Tier Settings");

    SelectTierMenu() {
        this.setUp();
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected Inventory getInventory() {
        return this.menu;
    }

    @Override
    protected void setUp() {
        setButton(10, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 1 Settings", null);

        setButton(12, Material.IRON_BLOCK, ChatColor.GRAY + "Tier 2 Settings", null);

        setButton(14, Material.GOLD_BLOCK, ChatColor.GOLD + "Tier 3 Settings", null);

        setButton(16, Material.DIAMOND_BLOCK, ChatColor.AQUA + "Tier 4 Settings", null);
        setExitButton(18);
        setBackButton(26);
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player player) {
        switch (slot) {
            case 10:
                player.openInventory(new TierSettingsMenu(1).getInventory());
                break;
            case 12:
                player.openInventory(new TierSettingsMenu(2).getInventory());
                break;
            case 14:
                player.openInventory(new TierSettingsMenu(3).getInventory());
                break;
            case 16:
                player.openInventory(new TierSettingsMenu(4).getInventory());
                break;
            case 18:
                player.closeInventory();
                break;
            case 26:
                player.openInventory(new EditorMainMenu().getInventory());
                break;
        }
    }
}
