package me.mancy.alphadrops.menus.editor;

import me.mancy.alphadrops.main.Main;
import me.mancy.alphadrops.utils.MenuUtil;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class EditorMainMenu implements Listener {

    public EditorMainMenu() {

    }

    public EditorMainMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    public Inventory getMenu() {
        Inventory inv = Bukkit.createInventory(null, 9, ChatColor.RED + "Main Editor Menu");
        /*
            Global Party Settings
                - Edit radius
                    * Click to prompt for value in chat
                - Edit countdown
                    * Click to prompt for value in chat
                - Edit height
                    * Click to prompt for value in chat
            Tier Settings
              - Select tier
                    - Edit chances
                        * Common Uncommon Rare Epic Legendary
                    - Edit item list
                        * Common Uncommon Rare Epic Legendary
                    - Edit cost
                        * Increase/Decrease
         */

        MenuUtil.addButton(inv, Material.BOOK, ChatColor.RED + "Global Settings", 3);
        MenuUtil.addButton(inv, Material.BOOK, ChatColor.RED + "Tier Settings", 4);
        MenuUtil.addExitButton(inv);
        MenuUtil.fillEmptySlots(inv);
        return inv;
    }

    @EventHandler
    private void handleClicks(InventoryClickEvent event) {
        if (!(ChatColor.stripColor(event.getClickedInventory().getName()).equalsIgnoreCase("Main Editor Menu"))) return;
        event.setCancelled(true);
        Player p = (Player) event.getWhoClicked();
        switch (event.getSlot()) {

            case 3:
                //TODO open global settings
                p.openInventory(new GlobalSettingsMenu().getMenu());
            case 4:
                //TODO open tier settings
            case 5:
                event.getWhoClicked().closeInventory();
        }

    }

}
