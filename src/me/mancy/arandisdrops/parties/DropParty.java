package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.data.Particles;
import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.*;

public class DropParty implements Listener {

    private static Main plugin;
    private int tier;

    private List<Location> locations = new ArrayList<>(LocationManager.getValidatedLocations().size());
    private List<Block> beaconCapBlocks = new ArrayList<>(LocationManager.getValidatedLocations().size());

    DropParty(int tier) { this.tier = tier; }

    public DropParty(Main main) {
        DropParty.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }


    private void setLocationsToUse() {
        if (LocationManager.getValidatedLocations() != null) {
            int amtToUse = Math.round(LocationManager.getValidatedLocations().size() / 2f);
            Collections.shuffle(LocationManager.getValidatedLocations());
            for (int x = 1; x <= amtToUse; x++) {
                int index = new Random().nextInt(amtToUse);
                if (LocationManager.getValidatedLocations().get(index) != null)
                    locations.add(LocationManager.getValidatedLocations().get(index));
            }
            Collections.shuffle(locations);
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(new FormattedMessage(ChatColor.RED + "Error: No locations found when starting drop party").toString());
        }
    }

    private List<ItemStack> getItemList(int tier) {

        int totalItemsToDrop = Bukkit.getServer().getOnlinePlayers().size() * 2;

        List<ItemStack> itemStackList = new ArrayList<>();

        int amtCommonItems = Math.round((Settings.getDropChances().get(tier)[0] / 100f) * totalItemsToDrop);
        int amtUnCommonItems = Math.round((Settings.getDropChances().get(tier)[1] / 100f) * totalItemsToDrop);
        int amtRareItems = Math.round((Settings.getDropChances().get(tier)[2] / 100f) * totalItemsToDrop);
        int amtEpicItems = Math.round((Settings.getDropChances().get(tier)[3] / 100f) * totalItemsToDrop);
        int amtLegendaryItems = Math.round((Settings.getDropChances().get(tier)[4] / 100f) * totalItemsToDrop);
        if (!Settings.getItemLists().get(1).isEmpty()) {
            if (amtCommonItems > Settings.getItemLists().get(1).size())
                amtCommonItems = Settings.getItemLists().get(1).size();
        }

        if (!Settings.getItemLists().get(2).isEmpty()) {
            if (amtUnCommonItems > Settings.getItemLists().get(2).size())
                amtUnCommonItems = Settings.getItemLists().get(2).size();
        }

        if (!Settings.getItemLists().get(3).isEmpty()) {
            if (amtRareItems > Settings.getItemLists().get(3).size())
                amtRareItems = Settings.getItemLists().get(3).size();
        }

        if (!Settings.getItemLists().get(4).isEmpty()) {
            if (amtEpicItems > Settings.getItemLists().get(4).size())
                amtEpicItems = Settings.getItemLists().get(4).size();
        }

        if (!Settings.getItemLists().get(5).isEmpty()) {
            if (amtLegendaryItems > Settings.getItemLists().get(5).size())
                amtLegendaryItems = Settings.getItemLists().get(5).size();
        }
        if (!Settings.getItemLists().get(1).isEmpty()) {
            for (int x = 0; x <= amtCommonItems; x++)
                itemStackList.add(Settings.getItemLists().get(1).get(x));
        }

        if (!Settings.getItemLists().get(2).isEmpty()) {
            for (int x = 0; x <= amtUnCommonItems; x++)
                itemStackList.add(Settings.getItemLists().get(2).get(x));
        }

        if (!Settings.getItemLists().get(3).isEmpty()) {
            for (int x = 0; x <= amtRareItems; x++)
                itemStackList.add(Settings.getItemLists().get(3).get(x));
        }

        if (!Settings.getItemLists().get(4).isEmpty()) {
            for (int x = 0; x <= amtEpicItems; x++)
                itemStackList.add(Settings.getItemLists().get(4).get(x));
        }

        if (!Settings.getItemLists().get(5).isEmpty()) {
            for (int x = 0; x <= amtLegendaryItems; x++)
                itemStackList.add(Settings.getItemLists().get(5).get(x));
        }
        Collections.shuffle(itemStackList, new Random());

        if (itemStackList.size() > totalItemsToDrop) {
            itemStackList.subList(totalItemsToDrop - 1, itemStackList.size());
            for (int x = itemStackList.size() - 1; x > totalItemsToDrop; x--) {
                itemStackList.remove(x);
            }
        }

        return itemStackList;
    }

    private void removeBeaconCaps() {
        for (Location loc : locations) {
            Block b = loc.getWorld().getBlockAt(loc.getBlockX(), loc.getWorld().getHighestBlockYAt(loc) - 1, loc.getBlockZ());
            beaconCapBlocks.add(b);
            b.setType(Material.AIR);
        }

    }

    private void playDropFireworks(Location offsetLoc) {
        Firework f = offsetLoc.getWorld().spawn(offsetLoc.add(0, 2, 0), Firework.class);
        FireworkMeta fm = f.getFireworkMeta();
        fm.addEffect(FireworkEffect.builder()
                .flicker(false)
                .trail(true)
                .withColor(Color.LIME)
                .with(FireworkEffect.Type.BALL_LARGE)
                .withColor(Color.ORANGE)
                .withFade(Color.ORANGE)
                .build());
        fm.setPower(3);
        f.setFireworkMeta(fm);
    }

    private void playParticleEffects(Location offsetLoc, ItemStack i) {
        Random random = new Random();
        float red;
        float green;
        float blue;

        if (Arrays.asList(Settings.getItemLists().get(1)).contains(i)) {
            //Common = White
            red = Particles.particles.get("Common")[0];
            green = Particles.particles.get("Common")[1];
            blue = Particles.particles.get("Common")[2];
        } else if (Arrays.asList(Settings.getItemLists().get(2)).contains(i)) {
            //Uncommon = green
            red = Particles.particles.get("Uncommon")[0];
            green = Particles.particles.get("Uncommon")[1];
            blue = Particles.particles.get("Uncommon")[2];
        } else if (Arrays.asList(Settings.getItemLists().get(3)).contains(i)) {
            // Rare =blue
            red = Particles.particles.get("Rare")[0];
            green = Particles.particles.get("Rare")[1];
            blue = Particles.particles.get("Rare")[2];
        } else if (Arrays.asList(Settings.getItemLists().get(4)).contains(i)) {
            //Epic = Gold
            red = Particles.particles.get("Epic")[0];
            green = Particles.particles.get("Epic")[1];
            blue = Particles.particles.get("Epic")[2];
        } else if (Arrays.asList(Settings.getItemLists().get(5)).contains(i)) {
            // Legendary = Red
            red = Particles.particles.get("Legendary")[0];
            green = Particles.particles.get("Legendary")[1];
            blue = Particles.particles.get("Legendary")[2];
        } else {
            red = 0;
            green = 0;
            blue = 0;
        }


        for (Player online : Bukkit.getServer().getOnlinePlayers()) {
            for (int amt = 0; amt < 40; amt++) {
                float randX = 0.1f + random.nextFloat() * (1);
                float randY = 0.4f + random.nextFloat() * (1);
                float randZ = 0.1f + random.nextFloat() * (1);
                online.spawnParticle(Particle.SPELL_MOB, new Location(offsetLoc.getWorld(), offsetLoc.getX() + randX, offsetLoc.getY() + randY, offsetLoc.getZ() + randZ),
                                    0, red, green, blue);
            }
        }
    }

    private void playStartFireworks() {
        for (Location location : locations) {
            Location launchLoc = location.add(0, 2, 0);
            Firework f = launchLoc.getWorld().spawn(launchLoc, Firework.class);
            FireworkMeta fm = f.getFireworkMeta();
            fm.addEffect(FireworkEffect.builder()
                    .flicker(false)
                    .trail(true)
                    .withColor(Color.fromBGR(255, 0, 0))
                    .with(FireworkEffect.Type.BALL_LARGE)
                    .withColor(Color.ORANGE)
                    .withFade(Color.BLUE)
                    .build());
            fm.setPower(3);
            f.setFireworkMeta(fm);
        }
    }

    private int itemsDropped = 0;
    void start() {
        DropPartyManager.setIsActiveDropParty(true);
        playStartFireworks();
        setLocationsToUse();
        removeBeaconCaps();
        if (!locations.isEmpty()) {
            List<ItemStack> itemsToDrop = getItemList(tier);
            for (int x = 0; x < itemsToDrop.size(); x++) {
                final ItemStack i = itemsToDrop.get(x);
                final Location locToDrop;
                if (x < locations.size())
                    locToDrop = locations.get(x);
                else
                    locToDrop = locations.get(0);

                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {

                    double offsetX = -Settings.getDropRadius() + (Settings.getDropRadius() + Settings.getDropRadius()) * new Random().nextDouble();
                    double offsetZ = -Settings.getDropRadius() + (Settings.getDropRadius() + Settings.getDropRadius()) * new Random().nextDouble();
                    Location offsetLoc = new Location(locToDrop.getWorld(), locToDrop.getX() + offsetX, (locToDrop.getWorld().getHighestBlockYAt(locToDrop) + 1) + Settings.getDropHeight(), locToDrop.getZ() + offsetZ);

                    playDropFireworks(offsetLoc);
                    playParticleEffects(offsetLoc, i);

                    offsetLoc.getWorld().dropItemNaturally(offsetLoc, i);
                    itemsDropped++;
                    if (itemsDropped >= itemsToDrop.size()) {
                        replaceBeaconCaps();
                    }

                }, 40L * (x + 1));

            }
        } else {
            Bukkit.getServer().getConsoleSender().sendMessage(new FormattedMessage(ChatColor.RED + "Fatal Error: No locations set at time of drop party starting").toString());
        }
    }

    private void replaceBeaconCaps() {
        DropPartyManager.setIsActiveDropParty(false);
        for (Block b : beaconCapBlocks) {
            b.setType(Material.STONE_SLAB);
        }
        Bukkit.getServer().broadcastMessage(new FormattedMessage(Strings.partyEnded.trim()).toString());
    }

}
