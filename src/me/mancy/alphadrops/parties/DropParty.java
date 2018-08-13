package me.mancy.alphadrops.parties;

import me.mancy.alphadrops.main.Main;
import org.bukkit.event.Listener;

public class DropParty implements Listener {

    /*
    SettingsManager
     */
    /*
     */

    private int tier;

    public DropParty(int tier) {
        this.tier = tier;
    }

    public DropParty(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    public void start() {

    }


}
