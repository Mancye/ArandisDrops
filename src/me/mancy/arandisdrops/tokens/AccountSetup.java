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
            if (AccountManager.getAccount(event.getPlayer()) == null) {
                Account account = new Account(event.getPlayer(), 0);
                AccountManager.addAccount(account);
            }
        }, 10L);
    }

}
