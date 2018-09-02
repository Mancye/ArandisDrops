package me.mancy.arandisdrops.menus.editor;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.utils.FormattedMessage;
import me.mancy.arandisdrops.utils.Menu;
import me.mancy.arandisdrops.utils.MenuRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class GlobalSettingsMenu extends Menu {

    private final Inventory menu = Bukkit.createInventory(null, 27, ChatColor.GREEN + "Global Settings");

    private final Material MATERIAL_RADIUS = Material.FEATHER;
    private final Material MATERIAL_COUNTDOWN = Material.FEATHER;
    private final Material MATERIAL_HEIGHT = Material.FEATHER;
    private final Material MATERIAL_ITEM_LIST = Material.BOOK;

    public GlobalSettingsMenu() {
        this.setUp();
    }

    @Override
    public Inventory getInventory() {
        return this.menu;
    }

    @Override
    protected void setUp(){
        List<String> radiusLore = new ArrayList<>(2);
        radiusLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Current: " + Settings.getDropRadius());
        setButton(10, MATERIAL_RADIUS, ChatColor.AQUA + "Edit Radius", radiusLore);

        List<String> countdownLore = new ArrayList<>(2);
        countdownLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Current: " + Settings.getCountdownTime());
        setButton(12, MATERIAL_COUNTDOWN, ChatColor.AQUA + "Edit Countdown", countdownLore);

        List<String> heightLore = new ArrayList<>(2);
        heightLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Current: " + Settings.getDropHeight());
        setButton(14, MATERIAL_HEIGHT, ChatColor.AQUA + "Edit Height", heightLore);

        setButton(16, MATERIAL_ITEM_LIST, ChatColor.AQUA + "Edit Item Lists", new ArrayList<>());

        setExitButton(18);
        setBackButton(26);
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player player) {
        switch (slot) {
            case 10:
                player.closeInventory();
                player.sendMessage(new FormattedMessage(ChatColor.GRAY + "Enter a new value for the radius").toString());
                PlayerEditingManager.playersEditingMap.put(player, SettingType.RADIUS);
                break;
            case 12:
                player.closeInventory();
                player.sendMessage(new FormattedMessage(ChatColor.GRAY + "Enter a new value for the countdown").toString());
                PlayerEditingManager.playersEditingMap.put(player, SettingType.COUNTDOWN);
                break;
            case 14:
                player.closeInventory();
                player.sendMessage(new FormattedMessage(ChatColor.GRAY + "Enter a new value for the height").toString());
                PlayerEditingManager.playersEditingMap.put(player, SettingType.HEIGHT);
                break;
            case 16:
                player.openInventory(new EditItemListsMenu().getInventory());
                break;
            case 18:
                player.closeInventory();
                break;
            case 26:
                player.openInventory(new EditorMainMenu().getInventory());
                break;
        }

    }
}
