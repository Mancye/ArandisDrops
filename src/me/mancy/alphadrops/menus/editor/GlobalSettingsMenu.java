package me.mancy.alphadrops.menus.editor;

import me.mancy.alphadrops.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;

public class GlobalSettingsMenu implements Listener {

    public GlobalSettingsMenu() {

    }

    public GlobalSettingsMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    public Inventory getMenu() {
        Inventory menu = Bukkit.createInventory(null, 27, ChatColor.RED + "Global Settings");

        return menu;
    }

}
