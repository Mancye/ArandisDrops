package me.mancy.alphadrops.menus;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public abstract class GUI {

    public static Map<UUID, GUI> inventoriesByUUID = new HashMap<>();
    public static Map<UUID, UUID> openInventories = new HashMap<>();

    private UUID uuid;
    private Inventory gui;
    private Map<Integer, GUIAction> actions;

    public GUI(int size, String name) {
        uuid = UUID.randomUUID();
        gui = Bukkit.createInventory(null, size, name);
        actions = new HashMap<>();
        inventoriesByUUID.put(getUUID(), this);
    }

    public UUID getUUID() {
        return uuid;
    }

    public Inventory getGui() {
        return this.gui;
    }

    public void setItem(int slot, ItemStack itemStack, String name, List<String> lore, GUIAction action) {
        ItemMeta meta = itemStack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
        gui.setItem(slot, itemStack);
        if (action != null){
            actions.put(slot, action);
        }
    }

    public void setItem(int slot, ItemStack stack, String name, List<String> lore){
        setItem(slot, stack, name, lore, null);
    }

    public void open(Player p) {
        p.openInventory(gui);
        openInventories.put(p.getUniqueId(), getUUID());
    }

    public static Map<UUID, GUI> getInventoriesByUUID() {
        return inventoriesByUUID;
    }

    public static Map<UUID, UUID> getOpenInventories() {
        return openInventories;
    }

    public Map<Integer, GUIAction> getActions() {
        return actions;
    }

    public void delete(){
        for (Player p : Bukkit.getOnlinePlayers()){
            UUID u = openInventories.get(p.getUniqueId());
            if (u.equals(getUUID())){
                p.closeInventory();
            }
        }
        inventoriesByUUID.remove(getUUID());
    }

    public interface GUIAction {
        void click(Player player);
    }

}
