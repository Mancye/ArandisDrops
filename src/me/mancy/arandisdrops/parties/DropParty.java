package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.main.Main;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class DropParty implements Listener {

    /*
    SettingsManager
     */
    /*
     */

    private int tier;
    private List<ItemStack> itemList = new ArrayList<>();

    public DropParty(int tier) {
        this.tier = tier;
        loadItemList(this.tier);
    }

    public DropParty(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    private void loadItemList(int tier) {

    }

    public void start() {

    }


}
