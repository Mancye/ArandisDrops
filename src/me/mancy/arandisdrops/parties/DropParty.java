package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DropParty implements Listener {

    /*
    SettingsManager
     */
    /*
     */

    private Main plugin;

    private int tier;

    private List<Block> beaconCapBlocks = new ArrayList<>(LocationManager.locations.size());

    public DropParty(int tier) {
        this.tier = tier;
    }

    public DropParty(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    private List<Location> getLocationsToUse() {
        if (LocationManager.locations != null) {
            int amtToUse = Math.round(LocationManager.locations.size() / 2f);
            List<Location> locationsToUse = new ArrayList<>();

            for (int x = 1; x <= amtToUse; x++) {
                int index = new Random().nextInt(amtToUse);
                if (LocationManager.locations.get(index) != null)
                    locationsToUse.add(LocationManager.locations.get(index));
            }
            return locationsToUse;
        }
        System.out.println(new FormattedMessage(ChatColor.RED + "Fatal Error: No locations found when starting drop party"));
        return null;
    }

    private List<ItemStack> getItemList(int tier) {

        int totalItemsToDrop = Bukkit.getServer().getOnlinePlayers().size() * 2;
        // Ex. 10 online = 20

        List<ItemStack> itemStackList = new ArrayList<>();

        int amtCommonItems = Math.round((Settings.getDropChances().get(tier)[0] / 100f) * totalItemsToDrop);
        int amtUnCommonItems = Math.round((Settings.getDropChances().get(tier)[1] / 100f) * totalItemsToDrop);
        int amtRareItems = Math.round((Settings.getDropChances().get(tier)[2] / 100f) * totalItemsToDrop);
        int amtEpicItems = Math.round((Settings.getDropChances().get(tier)[3] / 100f) * totalItemsToDrop);
        int amtLegendaryItems = Math.round((Settings.getDropChances().get(tier)[4] / 100f) * totalItemsToDrop);

        if (amtCommonItems > Settings.getItemLists().get(1).size())
            amtCommonItems = Settings.getItemLists().get(1).size();

        if (amtUnCommonItems > Settings.getItemLists().get(2).size())
            amtUnCommonItems = Settings.getItemLists().get(2).size();

        if (amtRareItems > Settings.getItemLists().get(3).size())
            amtRareItems = Settings.getItemLists().get(3).size();

        if (amtEpicItems > Settings.getItemLists().get(4).size())
            amtEpicItems = Settings.getItemLists().get(4).size();

        if (amtLegendaryItems > Settings.getItemLists().get(5).size())
            amtLegendaryItems = Settings.getItemLists().get(5).size();

        for (int x = 0; x <= amtCommonItems; x++)
            itemStackList.add(Settings.getItemLists().get(1).get(x));

        for (int x = 0; x <= amtUnCommonItems; x++)
            itemStackList.add(Settings.getItemLists().get(2).get(x));

        for (int x = 0; x <= amtRareItems; x++)
            itemStackList.add(Settings.getItemLists().get(3).get(x));

        for (int x = 0; x <= amtEpicItems; x++)
            itemStackList.add(Settings.getItemLists().get(4).get(x));

        for (int x = 0; x <= amtLegendaryItems; x++)
            itemStackList.add(Settings.getItemLists().get(5).get(x));
        Collections.shuffle(itemStackList, new Random());
        return itemStackList;
    }

    private void removeBeaconCaps() {
        int amtDone = 0;

        for (Location loc : getLocationsToUse()) {
            if (amtDone < amtDropLocs) {
                Block highest = loc.getWorld().getBlockAt(loc.getWorld().getHighestBlockAt(loc).getLocation().subtract(0, 1, 0));
                capBlocks.put(highest.getLocation(), highest);
                amtDone++;
            } else {
                break;
            }
        }

        for (Location loc : capBlocks.keySet()) {
            capBlocks.get(loc).setType(Material.AIR);
        }
    }

    private void cycleLocations() {

    }

    private void playDropFireworks() {

    }

    private void playDropParticles() {

    }

    private void playStartFireworks() {

    }

    public void start() {


        BukkitScheduler bukkitScheduler = Bukkit.getServer().getScheduler();
        List<ItemStack> itemsToDrop = getItemList(tier);
        for (int x = 0; x < itemsToDrop.size(); x++) {
            final ItemStack i = itemsToDrop.get(x);
            i.setAmount(1);

            bukkitScheduler.scheduleSyncDelayedTask(plugin, () -> {

                cycleLocations();

                double offsetX = -Settings.getDropRadius() + (Settings.getDropRadius() + Settings.getDropRadius()) * new Random().nextDouble();
                double offsetZ = -Settings.getDropRadius() + (Settings.getDropRadius() + Settings.getDropRadius()) * new Random().nextDouble();
                Location offsetLoc = new Location(locToDrop.getWorld(), locToDrop.getX() + offsetX, (locToDrop.getWorld().getHighestBlockYAt(locToDrop) + 1) + DropPartyManager.heightToDrop, locToDrop.getZ() + offsetZ);

                playDropFireworks(offsetLoc);
                playParticleEffects(offsetLoc, i);

                offsetLoc.getWorld().dropItemNaturally(offsetLoc, i);
                itemsDropped++;
                if (itemsDropped >= itemsToDrop.size()) {
                    replaceBeaconCaps();
                }

            }, 40L * (x + 1));

        }

    }


}
