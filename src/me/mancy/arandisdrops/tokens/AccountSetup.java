package me.mancy.arandisdrops.tokens;

import me.mancy.arandisdrops.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AccountSetup implements Listener  {

    private Main plugin;

    public AccountSetup(Main main) {
        this.plugin = main;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    private void setupAccount(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            if (event.getPlayer() == null)
                return;
            if (AccountManager.getPlayersAccount(event.getPlayer()) == null) {
                Account ttits = new Account(event.getPlayer(), 0);
                Account ttits1 = new Account(event.getPlayer(), 0);
                if (ttits.equals(ttits1))
                    event.getPlayer().sendMessage("Same");
                event.getPlayer().sendMessage("New Account Made");
            } else {
                event.getPlayer().sendMessage("Loaded Account");
            }
        }, 20L);
    }

}
