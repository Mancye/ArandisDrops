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
        globalLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit item lists");
        setButton(3, Material.BOOK, ChatColor.GRAY + "Global Settings", globalLore);

        List<String> tierLore = new ArrayList<>();
        tierLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit drop chances");
        tierLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.AQUA + "Edit costs");
        setButton(5, Material.FEATHER, ChatColor.GRAY + "Tier Settings", tierLore);
        setExitButton(0);
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player p) {
        if (slot == 0)
            p.closeInventory();
        if (slot == 3)
            p.openInventory(new GlobalSettingsMenu().getInventory());
        if (slot == 5)
            p.openInventory(new SelectTierMenu().getInventory());
    }


}
