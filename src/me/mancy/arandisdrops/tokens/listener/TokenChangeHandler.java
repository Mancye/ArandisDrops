package me.mancy.arandisdrops.tokens.listener;

import me.mancy.arandisdrops.data.AccountsDataManager;
import me.mancy.arandisdrops.main.Main;
import me.mancy.arandisdrops.tokens.event.TokenModifyEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class TokenChangeHandler implements Listener {

    public TokenChangeHandler(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }

    @EventHandler
    private void onTokenChange(TokenModifyEvent event) {
        AccountsDataManager.safeSave();
    }
}
