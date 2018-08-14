package me.mancy.alphadrops.listeners;

import me.mancy.alphadrops.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class EditValuesChatHandler implements Listener {

    public EditValuesChatHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void handleChat(AsyncPlayerChatEvent event) {
        /*
        TODO Handle value inputs, check for valid input(only numbers) else keep looping until it's a valid number
         */
    }

}
