package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import me.mancy.arandisdrops.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class TierSettingsMenu extends Menu {

    private int tier;
    private Inventory inventory = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Select Setting To Edit");


    public TierSettingsMenu(int tier) {
        this.tier = tier;
        setUp();
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    @Override
    protected void setUp() {
        setButton(12, Material.BOOK, ChatColor.AQUA + "Edit Chances", new ArrayList<>());
        setButton(14, Material.BOOK, ChatColor.AQUA + "Edit Cost", new ArrayList<>());
        setExitButton(18);
        setBackButton(26);
    }

    @Override
    protected void handleInput(int slot, Player player) {
        switch (slot) {
            case 12:
                PlayerEditingManager.instance.playersEditingMap.put(player, SettingType.CHANCE);
                player.openInventory(new EditDropChancesMenu(tier).getInventory());
                break;
            case 14:
                PlayerEditingManager.instance.playerTierEditingMap.put(player, tier);
                PlayerEditingManager.instance.playersEditingMap.put(player, SettingType.COST);
                Messager.sendMessage(player, ChatColor.AQUA + "Enter a new cost");
                break;
            case 18:
                player.closeInventory();
                break;
            case 26:
                player.openInventory(new SelectTierMenu().getInventory());
                break;
        }
    }
}
