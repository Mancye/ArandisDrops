package me.mancy.arandisdrops.menus.editor.listeners;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.menus.editor.GlobalSettingsMenu;
import me.mancy.arandisdrops.menus.editor.PlayerEditingManager;
import me.mancy.arandisdrops.menus.editor.TierSettingsMenu;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Set;

public class EditValueChatHandler implements Listener {

    public EditValueChatHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }


    @EventHandler
    private void handleChat(AsyncPlayerChatEvent event) {
        if (PlayerEditingManager.instance.playersEditingMap.containsKey(event.getPlayer()) && !PlayerEditingManager.instance.instance.playerTierEditingMap.containsKey(event.getPlayer())) {
            event.setCancelled(true);
            if (NumberUtils.isNumber(event.getMessage())) {
                switch (PlayerEditingManager.instance.playersEditingMap.get(event.getPlayer())) {
                    case HEIGHT:
                        Settings.setDropHeight(Double.parseDouble(event.getMessage()));
                        PlayerEditingManager.instance.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set drop height to " + ChatColor.AQUA + Settings.getDropHeight()).toString());
                        event.getPlayer().openInventory(new GlobalSettingsMenu().getInventory());
                        break;
                    case RADIUS:
                        Settings.setDropRadius(Double.parseDouble(event.getMessage()));
                        PlayerEditingManager.instance.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set drop radius to " + ChatColor.AQUA + Settings.getDropRadius()).toString());
                        event.getPlayer().openInventory(new GlobalSettingsMenu().getInventory());
                        break;
                    case COUNTDOWN:
                        Settings.setCountdownTime(Integer.parseInt(event.getMessage()));
                        PlayerEditingManager.instance.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set countdown time to " + ChatColor.AQUA + Settings.getCountdownTime()).toString());
                        event.getPlayer().openInventory(new GlobalSettingsMenu().getInventory());
                        break;
                }
            } else {
                event.getPlayer().sendMessage(new FormattedMessage(ChatColor.RED + "Please Enter A Valid Number").toString());
            }
        } else if (PlayerEditingManager.instance.playerTierEditingMap.containsKey(event.getPlayer())) {
            event.setCancelled(true);
            if (NumberUtils.isNumber(event.getMessage())) {
                int tier = PlayerEditingManager.instance.playerTierEditingMap.get(event.getPlayer());
                switch (PlayerEditingManager.instance.playersEditingMap.get(event.getPlayer())) {
                    case COST:
                        Settings.getCosts().put(tier, Integer.parseInt(event.getMessage()));
                        PlayerEditingManager.instance.playerTierEditingMap.remove(event.getPlayer());
                        PlayerEditingManager.instance.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set cost to " + ChatColor.AQUA + Settings.getCosts().get(tier)).toString());
                        event.getPlayer().openInventory(new TierSettingsMenu(tier).getInventory());
                        break;
                    case CHANCE:
                        if (PlayerEditingManager.instance.playerRarityEditingMap.containsKey(event.getPlayer())) {
                            int rarity = PlayerEditingManager.instance.playerRarityEditingMap.get(event.getPlayer());
                            Settings.getDropChances().get(tier)[rarity - 1] = Integer.parseInt(event.getMessage());
                            PlayerEditingManager.instance.playerTierEditingMap.remove(event.getPlayer());
                            PlayerEditingManager.instance.playerRarityEditingMap.remove(event.getPlayer());
                            PlayerEditingManager.instance.playersEditingMap.remove(event.getPlayer());
                            event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set drop chance to " + ChatColor.AQUA + Settings.getDropChances().get(tier)[rarity - 1]).toString());
                            event.getPlayer().openInventory(new TierSettingsMenu(tier).getInventory());
                        }
                        break;
                }

            } else {
                event.getPlayer().sendMessage(new FormattedMessage(ChatColor.RED + "Please Enter A Valid Number").toString());

            }
        }
    }


}
