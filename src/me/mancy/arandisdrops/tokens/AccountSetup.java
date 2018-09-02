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
                new Account(event.getPlayer().getUniqueId(), 0);
            }
        }, 20L);
    }

}
