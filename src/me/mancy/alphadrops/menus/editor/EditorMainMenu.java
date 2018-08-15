package me.mancy.alphadrops.menus.editor;

import me.mancy.alphadrops.main.Main;
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

public class EditorMainMenu extends Menu implements Listener {

    private final Inventory menu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Select SettingsManager To Edit");

    public EditorMainMenu() {
        setUp();
    }
    public EditorMainMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

        /*
            Global Party SettingsManager
                - Edit radius
                    * Click to prompt for value in chat
                - Edit countdown
                    * Click to prompt for value in chat
                - Edit height
                    * Click to prompt for value in chat
            Tier SettingsManager
              - Select tier
                    - Edit chances
                        * Common Uncommon Rare Epic Legendary
                    - Edit item list
                        * Common Uncommon Rare Epic Legendary
                    - Edit cost
                        * Increase/Decrease
         */


    @Override
    public Inventory getInventory() {
        return this.menu;
    }

    @Override
    protected void setUp() {
        List<String> globalLore = new ArrayList<>();
        globalLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "Edit drop radius");
        globalLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "Edit drop countdown timer");
        globalLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "Edit drop height");
        setButton(3, Material.BOOK, ChatColor.GREEN + "Global SettingsManager", globalLore);
        List<String> tierLore = new ArrayList<>();
        tierLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "Edit drop chances");
        tierLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "Edit item lists");
        tierLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "Edit costs");
        setButton(5, Material.FEATHER, ChatColor.AQUA + "Tier SettingsManager", tierLore);
    }

    @Override
    protected void handleInput(InventoryClickEvent event) {
        if (event.getClickedInventory().getName().equalsIgnoreCase(this.menu.getName())) {
            if (event.getWhoClicked() instanceof Player) {
                event.setCancelled(true);
                switch (event.getSlot()) {
                    case 3:
                        event.getWhoClicked().openInventory(new GlobalSettingsMenu().getInventory());
                        break;
                    case 5:
                        event.getWhoClicked().openInventory(new TierSettingsMenu().getInventory());
                        break;
                }
            }
        }
    }


}
