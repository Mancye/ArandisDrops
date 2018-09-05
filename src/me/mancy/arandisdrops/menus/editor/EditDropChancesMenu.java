package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.utils.FormattedMessage;
import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class EditDropChancesMenu extends Menu {

    private final Inventory menu = Bukkit.createInventory(null, 27, "Select Rarity");
    private final int tier;

    EditDropChancesMenu(int tier) {
        this.tier = tier;
        setUp();
    }

    @Override
    protected Inventory getInventory() {
        return this.menu;
    }

    @Override
    protected void setUp() {
        setButton(11, Material.FEATHER, ChatColor.AQUA + "Common", new ArrayList<>());
        setButton(12, Material.FEATHER, ChatColor.AQUA + "Uncommon", new ArrayList<>());
        setButton(13, Material.FEATHER, ChatColor.AQUA + "Rare", new ArrayList<>());
        setButton(14, Material.FEATHER, ChatColor.AQUA + "Epic", new ArrayList<>());
        setButton(15, Material.FEATHER, ChatColor.AQUA + "Legendary", new ArrayList<>());
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player player) {
        switch (slot) {
            case 11:
                player.sendMessage(new FormattedMessage(ChatColor.AQUA + "Enter a new value").toString());
                PlayerEditingManager.instance.playerTierEditingMap.put(player, tier);
                PlayerEditingManager.instance.playerRarityEditingMap.put(player, 1);
                break;
            case 12:
                player.sendMessage(new FormattedMessage(ChatColor.AQUA + "Enter a new value").toString());
                PlayerEditingManager.instance.playerTierEditingMap.put(player, tier);
                PlayerEditingManager.instance.playerRarityEditingMap.put(player, 2);
                break;
            case 13:
                player.sendMessage(new FormattedMessage(ChatColor.AQUA + "Enter a new value").toString());
                PlayerEditingManager.instance.playerTierEditingMap.put(player, tier);
                PlayerEditingManager.instance.playerRarityEditingMap.put(player, 3);
                break;
            case 14:
                player.sendMessage(new FormattedMessage(ChatColor.AQUA + "Enter a new value").toString());
                PlayerEditingManager.instance.playerTierEditingMap.put(player, tier);
                PlayerEditingManager.instance.playerRarityEditingMap.put(player, 4);
                break;
            case 15:
                player.sendMessage(new FormattedMessage(ChatColor.AQUA + "Enter a new value").toString());
                PlayerEditingManager.instance.playerTierEditingMap.put(player, tier);
                PlayerEditingManager.instance.playerRarityEditingMap.put(player, 5);
                break;
        }
    }
}
