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

    GlobalSettingsMenu() {
        this.setUp();
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

        List<String> heightLore = new ArrayList<>(2);
        heightLore.add(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Current: " + Settings.getDropHeight());
        setButton(15, MATERIAL_HEIGHT, ChatColor.AQUA + "Edit Height", heightLore);
        MenuRegistry.registeredMenus.put(getInventory(), this);
    }

    @Override
    protected void handleInput(int slot, Player player) {
        switch (slot) {
            case 11:
                player.closeInventory();
                player.sendMessage(new FormattedMessage(ChatColor.GRAY + "Enter a new value for the radius").toString());
                PlayerEditingManager.playersEditingMap.put(player, SettingType.RADIUS);
                break;
            case 13:
                player.closeInventory();
                player.sendMessage(new FormattedMessage(ChatColor.GRAY + "Enter a new value for the countdown").toString());
                PlayerEditingManager.playersEditingMap.put(player, SettingType.COUNTDOWN);
                break;
            case 15:
                player.closeInventory();
                player.sendMessage(new FormattedMessage(ChatColor.GRAY + "Enter a new value for the height").toString());
                PlayerEditingManager.playersEditingMap.put(player, SettingType.HEIGHT);
                break;
        }

    }
}
