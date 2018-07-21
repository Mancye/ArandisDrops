package me.mancy.alphadrops.utils;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class MenuUtil {

    public static void fillEmptySlots(Inventory inv) {
        ItemStack emptySlot = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
        ItemMeta emptyMeta = emptySlot.getItemMeta();
        emptyMeta.setDisplayName("");
        emptySlot.setItemMeta(emptyMeta);

        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null || inv.getItem(i).getType().equals(Material.AIR)) {
                inv.setItem(i, emptySlot);
            }
        }
    }

    public static void addExitButton(Inventory inventory) {
        ItemStack exit = new ItemStack(Material.BARRIER);
        ItemMeta exitMeta = exit.getItemMeta();
        exitMeta.setDisplayName(ChatColor.RED + "Exit");
        exit.setItemMeta(exitMeta);
        inventory.setItem((inventory.getStorageContents().length - 1), exit);
    }

    public static void addButton(Inventory inventory, Material material, String name, List<String> desc, int slot) {
        ItemStack btn = new ItemStack(material);
        ItemMeta btnMeta = btn.getItemMeta();
        btnMeta.setDisplayName(name);
        btnMeta.setLore(desc);
        btn.setItemMeta(btnMeta);
        inventory.setItem(slot, btn);
    }

}
