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

public class EditValueChatHandler implements Listener {

    public EditValueChatHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }


    @EventHandler
    private void handleChat(AsyncPlayerChatEvent event) {
        if (PlayerEditingManager.playersEditingMap.containsKey(event.getPlayer())) {
            event.setCancelled(true);
            if (NumberUtils.isNumber(event.getMessage())) {
                switch (PlayerEditingManager.playersEditingMap.get(event.getPlayer())) {
                    case HEIGHT:
                        Settings.setDropHeight(Double.parseDouble(event.getMessage()));
                        PlayerEditingManager.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set drop height to " + Settings.getDropHeight()).toString());
                        break;
                    case RADIUS:
                        Settings.setDropRadius(Double.parseDouble(event.getMessage()));
                        PlayerEditingManager.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set drop radius to " + Settings.getDropRadius()).toString());
                        break;
                    case COUNTDOWN:
                        Settings.setCountdownTime(Integer.parseInt(event.getMessage()));
                        PlayerEditingManager.playersEditingMap.remove(event.getPlayer());
                        event.getPlayer().sendMessage(new FormattedMessage(ChatColor.GRAY + "Set countdown time to " + Settings.getCountdownTime()).toString());
                        break;
                    case COST:
                        break;
                }
            } else {
                event.getPlayer().sendMessage(new FormattedMessage(ChatColor.RED + "Please Enter A Valid Number").toString());
            }
        }
    }


}
