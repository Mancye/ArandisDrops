package me.mancy.alphadrops.menus.editor;

import me.mancy.alphadrops.main.Main;
import me.mancy.alphadrops.utils.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

public class TierSettingsMenu extends Menu implements Listener {

    private final Inventory menu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Tier Settings");

    public TierSettingsMenu() {

    }

    public TierSettingsMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }


    @Override
    protected Inventory getInventory() {
        return null;
    }

    @Override
    protected void setUp() {

    }

    @Override
    protected void handleInput(InventoryClickEvent event) {

    }
}
