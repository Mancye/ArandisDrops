package me.mancy.arandisdrops.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    private final Material EXIT_BUTTON = Material.BARRIER;
    private final Material BACK_BUTTON = Material.ARROW;

    protected abstract Inventory getInventory();

    protected void setButton(int slot, Material type, String name, List<String> lore) {
        if (type != null) {
            if (slot >= 0 && slot < getInventory().getSize()) {
                ItemStack itemStack = new ItemStack(type);
                ItemMeta itemMeta = itemStack.getItemMeta();
                if (lore == null)
                    lore = new ArrayList<>();
                itemMeta.setLore(lore);
                itemMeta.setDisplayName(name);
                itemStack.setItemMeta(itemMeta);
                getInventory().setItem(slot, itemStack);
            }
        }
    }

    protected abstract void setUp();

    protected abstract void handleInput(int slot, Player player);

    protected void fillEmptySlots(Inventory inv) {
        ItemStack emptySlot = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = emptySlot.getItemMeta();
        emptyMeta.setDisplayName("");
        emptySlot.setItemMeta(emptyMeta);

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null)
                inv.setItem(i, emptySlot);
        }
    }

    protected void setBackButton(int slot) {
        setButton(slot, BACK_BUTTON, ChatColor.RED + "Back", new ArrayList<>());
    }

    protected void setExitButton(int slot) {
        setButton(slot, EXIT_BUTTON, ChatColor.RED + "Exit", new ArrayList<>());
    }

}
