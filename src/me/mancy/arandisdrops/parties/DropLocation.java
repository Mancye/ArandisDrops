package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DropLocation implements Listener {

    public static Set<UUID> playersEditing = new HashSet<>();

    public DropLocation(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void placeValidBlock(BlockPlaceEvent event) {
        if (playersEditing.contains(event.getPlayer().getUniqueId())) {
            for (Location location : LocationManager.getUnValidatedLocations()) {
                System.out.println("One");
                if (!LocationManager.getValidatedLocations().contains(location)) {
                    if (location.getBlockX() == event.getBlock().getX() && location.getBlockZ() == event.getBlock().getZ()) {
                        if (event.getBlockAgainst().getType().name().contains("Stained Glass")) {
                            validateLocation(location);
                            if (LocationManager.getValidatedLocations().contains(location)) {
                                event.getPlayer().sendMessage(new FormattedMessage(ChatColor.AQUA + "Successfully validated location!").toString());
                            } else {
                                System.out.println("Not validated");
                            }
                            playersEditing.remove(event.getPlayer().getUniqueId());
                        }
                    } else {
                        System.out.println("Not same coords");
                    }
                } else {
                    System.out.println("Already valid");
                }
            }
        } else {
            System.out.println("Nope not in playersEditing Set");
        }

    }

    @EventHandler
    private void removeValidBlock(BlockBreakEvent event) {
        for (Location location : LocationManager.getValidatedLocations()) {
            if (event.getBlock().getX() == location.getBlockX() && event.getBlock().getZ() == location.getBlockZ()) {
                validateLocation(location);
            }
        }
    }

    private void validateLocation(Location location) {
        Block highest = location.getWorld().getBlockAt(location.getBlockX(), location.getWorld().getHighestBlockYAt(location) - 1, location.getBlockZ());
        if (!highest.getType().name().contains("Stained Glass") &&
                !highest.getType().name().contains("Beacon")) {
                LocationManager.addValidatedLocation(location);
        } else {
            LocationManager.getValidatedLocations().remove(location);
        }
    }


}
