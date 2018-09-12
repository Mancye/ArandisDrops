package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.utils.Messager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitScheduler;


public class Countdown {
    private static Main plugin;
    private Settings settings = Settings.getInstance();
    private int time = 0;
    private int taskID;

    public Countdown() {}

    public Countdown(Main main) {
        Countdown.plugin = main;
    }

    public void setTimer(int amount, int tier) {
        time = amount;
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage(ChatColor.GREEN + "          TIER " + tier + " DROP PARTY         ");
        Bukkit.broadcastMessage(ChatColor.AQUA + "           BEGINS IN " + settings.getCountdownTime() + " SECONDS");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }

    public void startTimer(int dropTier) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if (time == 0) {
                Bukkit.broadcastMessage(Messager.toFormatted(Strings.partyStarted.trim()));
                new DropParty(dropTier).start();
                stopTimer();
                return;
            }
            if (time % 5 == 0 && time <= 20) {
                Bukkit.broadcastMessage(Messager.toFormatted(ChatColor.GRAY + "Time Until Drop party" + ChatColor.DARK_GRAY + ": " + ChatColor.AQUA + time + " Seconds!"));
            }


            time -= 1;
        }, 0L, 20L);
    }

    private void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

}
