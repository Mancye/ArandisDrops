package me.mancy.alphadrops.menus.editor;

import me.mancy.alphadrops.data.Settings;
import me.mancy.alphadrops.main.Main;
import me.mancy.alphadrops.utils.Menu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class TierSettingsMenu extends Menu implements Listener {

    private final Inventory menu = Bukkit.createInventory(null, 27, ChatColor.AQUA + "Tier Settings");

    TierSettingsMenu() {
        setUp();
    }

    public TierSettingsMenu(Main main) {
        main.getServer().getPluginManager().registerEvents(this, main);
    }


    @Override
    protected Inventory getInventory() {
        return null;
    }

    @Override
    protected void setUp() {
        List<String> lore = new ArrayList<>(2);
        lore.add(ChatColor.AQUA + ChatColor.ITALIC.toString() + "Click To Start A Tier One Drop Party!");
        lore.add(ChatColor.AQUA + ChatColor.ITALIC.toString() + "COST: " + Settings.getCosts().get(1) + " Token(s)");
        setButton(10, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 1", lore);

        lore.clear();
        lore.add(ChatColor.AQUA + ChatColor.ITALIC.toString() + "Click To Start A Tier Two Drop Party!");
        lore.add(ChatColor.AQUA + ChatColor.ITALIC.toString() + "COST: " + Settings.getCosts().get(2) + " Token(s)");
        setButton(12, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 2", lore);

        lore.clear();
        lore.add(ChatColor.AQUA + ChatColor.ITALIC.toString() + "Click To Start A Tier Three Drop Party!");
        lore.add(ChatColor.AQUA + ChatColor.ITALIC.toString() + "COST: " + Settings.getCosts().get(3) + " Token(s)");
        setButton(14, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 3", lore);

        lore.clear();
        lore.add(ChatColor.AQUA + ChatColor.ITALIC.toString() + "Click To Start A Tier Four Drop Party!");
        lore.add(ChatColor.AQUA + ChatColor.ITALIC.toString() + "COST: " + Settings.getCosts().get(4) + " Token(s)");
        setButton(16, Material.COAL_ORE, ChatColor.DARK_GRAY + "Tier 4", lore);
    }

    @Override
    protected void handleInput(InventoryClickEvent event) {

    }
}
