package me.mancy.alphadrops.menus.editor;

import me.mancy.alphadrops.main.Main;
import me.mancy.alphadrops.data.Settings;
import me.mancy.alphadrops.utils.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
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
        this.setUp();
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
        List<String> radiusLore = new ArrayList<>(2);
        radiusLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Current: " + Settings.getDropRadius());
        setButton(11, MATERIAL_RADIUS, ChatColor.AQUA + "Edit Radius", radiusLore);

        List<String> countdownLore = new ArrayList<>(2);
        countdownLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Current: " + Settings.getCountdownTime());
        setButton(13, MATERIAL_COUNTDOWN, ChatColor.AQUA + "Edit Countdown", countdownLore);

        List<String> heightLore = new ArrayList<>(2);;
        countdownLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Current: " + Settings.getDropHeight());
        setButton(15, MATERIAL_HEIGHT, ChatColor.AQUA + "Edit Height", heightLore);
    }

    @Override
    protected void handleInput(InventoryClickEvent event) {
        if (event.getClickedInventory().getName().equalsIgnoreCase(this.menu.getName())) {
            if (event.getWhoClicked() instanceof Player) {
                event.setCancelled(true);
                switch (event.getSlot()) {
                    case 11:

                        break;
                    case 13:

                        break;
                    case 15:
                        //Chat input for values
                        break;
                }
            }
        }
    }
}
