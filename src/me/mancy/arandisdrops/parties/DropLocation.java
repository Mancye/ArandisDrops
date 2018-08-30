package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.main.Main;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DropLocation implements Listener {

    private Set<UUID> playersEditing = new HashSet<>();

    public DropLocation(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void createTotem(BlockPlaceEvent event) {
        //if (event.getBlockAgainst().getType() == Material.BEACON || event.getBlockAgainst().getType() == Material.STAIN)
    }

    private void validateLocation(Location location) {
        if (!location.getWorld().getHighestBlockAt(location).getType().name().contains("Stained Glass") &&
                !location.getWorld().getHighestBlockAt(location).getType().name().contains("Beacon")) {
                LocationManager.addLocation(location);
        }
    }


}
