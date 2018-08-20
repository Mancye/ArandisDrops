package me.mancy.arandisdrops.utils;

import me.mancy.arandisdrops.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MenuListener implements Listener {

    public MenuListener(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void onClick(InventoryClickEvent event) {
        if (MenuRegistry.registeredMenus.containsKey(event.getClickedInventory())) {
            if (event.getWhoClicked() instanceof Player) {
                event.setCancelled(true);
                if (event.getInventory().getItem(event.getSlot()) != null)
                   MenuRegistry.registeredMenus.get(event.getClickedInventory()).handleInput(event.getSlot(), (Player) event.getWhoClicked());
            }
        }
    }

}
