package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.main.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class DropLocation implements Listener {

    public DropLocation(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }



}
