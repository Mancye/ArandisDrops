package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

public class DropLocation implements Listener {

    private Main plugin;

    public DropLocation(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
        this.plugin = main;
    }

    @EventHandler
    private void placeValidBlock(BlockPlaceEvent event) {
        if (event.getBlock().getType().name().contains("STAINED_GLASS"))
            return;
        Iterator<Location> iterator = LocationManager.getUnValidatedLocations().iterator();
        int amtValid = LocationManager.getValidatedLocations().size();
        while (iterator.hasNext()) {
            Location location = iterator.next();
            if (!LocationManager.getValidatedLocations().contains(location)) {
                if (location.getBlockX() == event.getBlock().getX() && location.getBlockZ() == event.getBlock().getZ()) {
                    if (event.getBlockAgainst().getType().name().contains("STAINED_GLASS")) {
                        if (isValidLocation(location)) {
                            LocationManager.addValidatedLocation(location);
                            iterator.remove();
                            break;
                        }
                    }
                }
            }
        }
        if (amtValid < LocationManager.getValidatedLocations().size()) {
            event.getPlayer().sendMessage(Messager.toFormatted(ChatColor.AQUA + "Successfully validated location!"));
        }
    }

    @EventHandler
    private void removeValidBlock(BlockBreakEvent event) {
        if (event.getPlayer().hasPermission("droppary.editlocations")) {
            Iterator<Location> iterator = LocationManager.getValidatedLocations().iterator();
            int amtValid = LocationManager.getValidatedLocations().size();
            if (event.getBlock().getType().name().contains("STAINED_GLASS"))
                return;
            while (iterator.hasNext()) {
                Location location = iterator.next();
                if (event.getBlock().getX() == location.getBlockX() && event.getBlock().getZ() == location.getBlockZ()) {

                    if (event.getBlock().getType() == Material.BEACON)
                        return;
                    Bukkit.getServer().getScheduler().runTaskLater(plugin, () -> {
                        if (!isValidLocation(location)) {
                            LocationManager.addUnvalidatedLocation(location);
                            iterator.remove();
                            if (amtValid > LocationManager.getValidatedLocations().size())
                                event.getPlayer().sendMessage(Messager.toFormatted(ChatColor.RED + "Unvalidated location!"));
                        }
                    }, 5L);
                    break;
                }
            }
        }
    }

    private boolean isValidLocation(Location location) {
        Block highest = location.getWorld().getBlockAt(location.getBlockX(), location.getWorld().getHighestBlockYAt(location) - 1, location.getBlockZ());

        return (!highest.getType().name().contains("STAINED_GLASS") && highest.getType() != Material.BEACON);
    }


}
