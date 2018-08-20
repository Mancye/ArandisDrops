package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class EditItemListsMenu extends Menu {

    @Override
    protected Inventory getInventory() {
        return null;
    }

    @Override
    protected void setUp() {
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player player) {

    }
}
