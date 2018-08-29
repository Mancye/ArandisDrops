package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class EditorMainMenu extends Menu {

    private final Inventory menu = Bukkit.createInventory(null, 9, ChatColor.AQUA + "Select Settings To Edit");

    public EditorMainMenu() {
        setUp();
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
                        * Click to prompt
         */


    @Override
    public Inventory getInventory() {
        return this.menu;
    }

    @Override
    protected void setUp() {
        List<String> globalLore = new ArrayList<>();
        globalLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit drop radius");
        globalLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit drop countdown timer");
        globalLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit drop height");
        setButton(3, Material.BOOK, ChatColor.RED + "Global Settings", globalLore);
        List<String> tierLore = new ArrayList<>();
        tierLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit drop chances");
        tierLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit item lists");
        tierLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit costs");
        setButton(5, Material.FEATHER, ChatColor.RED + "Tier Settings", tierLore);
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player p) {
        if (slot == 3)
            p.openInventory(new GlobalSettingsMenu().getInventory());
        if (slot == 5)
            p.openInventory(new SelectTierMenu().getInventory());
    }


}
