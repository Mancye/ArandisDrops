package me.mancy.arandisdrops.parties;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitScheduler;

public class Countdown {
    private static Main plugin;

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
        Bukkit.broadcastMessage(ChatColor.AQUA + "           BEGINS IN " + Settings.getCountdownTime() + " SECONDS");
        Bukkit.broadcastMessage("");
        Bukkit.broadcastMessage("");
    }

    public void startTimer(int dropTier) {
        BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
        taskID = scheduler.scheduleSyncRepeatingTask(plugin, () -> {
            if (time == 0) {
                Bukkit.broadcastMessage(new FormattedMessage(Strings.partyStarted.trim()).toString());
                new DropParty(dropTier).start();
                stopTimer();
                return;
            }
            if (time % 5 == 0 && time <= 20) {
                Bukkit.broadcastMessage(new FormattedMessage(ChatColor.GRAY + "Time Until Drop Party" + ChatColor.DARK_GRAY + ": " + ChatColor.RED + time + " Seconds!").toString());
            }


            time -= 1;
        }, 0L, 20L);
    }

    private void stopTimer() {
        Bukkit.getScheduler().cancelTask(taskID);
    }

}
