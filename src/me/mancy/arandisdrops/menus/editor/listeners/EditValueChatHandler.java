package me.mancy.arandisdrops.menus.editor.listeners;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.menus.editor.PlayerEditingManager;
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
        if (PlayerEditingManager.playersEditingMap.containsKey(event.getPlayer()) && !PlayerEditingManager.playerTierEditingMap.containsKey(event.getPlayer())) {
            event.setCancelled(true);
            if (NumberUtils.isNumber(event.getMessage())) {
                switch (PlayerEditingManager.playersEditingMap.get(event.getPlayer())) {
                    case HEIGHT:
                        Settings.setDropHeight(Double.parseDouble(event.getMessage()));
                        PlayerEditingManager.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set drop height to " + ChatColor.AQUA + Settings.getDropHeight()).toString());
                        break;
                    case RADIUS:
                        Settings.setDropRadius(Double.parseDouble(event.getMessage()));
                        PlayerEditingManager.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set drop radius to " + ChatColor.AQUA + Settings.getDropRadius()).toString());
                        break;
                    case COUNTDOWN:
                        Settings.setCountdownTime(Integer.parseInt(event.getMessage()));
                        PlayerEditingManager.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set countdown time to " + ChatColor.AQUA + Settings.getCountdownTime()).toString());
                        break;
                }
            } else {
                event.getPlayer().sendMessage(new FormattedMessage(ChatColor.RED + "Please Enter A Valid Number").toString());
            }
        } else if (PlayerEditingManager.playerTierEditingMap.containsKey(event.getPlayer())) {
            event.setCancelled(true);
            if (NumberUtils.isNumber(event.getMessage())) {
                int tier = PlayerEditingManager.playerTierEditingMap.get(event.getPlayer());
                switch (PlayerEditingManager.playersEditingMap.get(event.getPlayer())) {
                    case COST: Settings.getCosts().put(tier, Integer.parseInt(event.getMessage()));
                    PlayerEditingManager.playerTierEditingMap.remove(event.getPlayer());
                    PlayerEditingManager.playersEditingMap.remove(event.getPlayer());
                    event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set cost to " + ChatColor.AQUA + Settings.getCosts().get(tier)).toString());
                        break;
                    case CHANCE:
                        if (PlayerEditingManager.playerRarityEditingMap.containsKey(event.getPlayer())) {
                        int rarity = PlayerEditingManager.playerRarityEditingMap.get(event.getPlayer());
                        Settings.getDropChances().get(tier)[rarity - 1] = Integer.parseInt(event.getMessage());
                        PlayerEditingManager.playerTierEditingMap.remove(event.getPlayer());
                        PlayerEditingManager.playerRarityEditingMap.remove(event.getPlayer());
                        PlayerEditingManager.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set drop chance to " + ChatColor.AQUA + Settings.getDropChances().get(tier)[rarity-1]).toString());
                        }
                        break;
                }

            } else {
                event.getPlayer().sendMessage(new FormattedMessage(ChatColor.RED + "Please Enter A Valid Number").toString());

            }
        }
    }


}
