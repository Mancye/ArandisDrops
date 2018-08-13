package me.mancy.alphadrops.menus.editor;

import me.mancy.alphadrops.main.Main;
import me.mancy.alphadrops.utils.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GlobalSettingsMenu extends Menu implements Listener {

    private final Inventory menu = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Global SettingsManager");

    /*Global Party SettingsManager
          - Edit radius
                   * Click to prompt for value in chat
          - Edit countdown
                    * Click to prompt for value in chat
          - Edit height
                   * Click to prompt for value in chat
    */

    private final Material MATERIAL_RADIUS = Material.FEATHER;
    private final Material MATERIAL_COUNTDOWN = Material.FEATHER;
    private final Material MATERIAL_HEIGHT = Material.FEATHER;

    public GlobalSettingsMenu() {
        setUp();
    }

    public GlobalSettingsMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }


    @Override
    protected Inventory getInventory() {
        return this.menu;
    }

    @Override
    protected void setUp(){
        List<String> lore = new ArrayList<>(2);
        lore.add(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Current: ");
        setButton(11, MATERIAL_RADIUS, ChatColor.AQUA + "Edit Radius", lore);
    }

    @Override
    protected void handleInput(InventoryClickEvent event) {

    }
}
