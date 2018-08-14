package me.mancy.alphadrops.menus.editor;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerEditingManager {

    private static Map<UUID, Integer> radiusEdits = new HashMap<>();
    private static Map<UUID, Integer> heightEdits = new HashMap<>();
    private static Map<UUID, Integer> countdownEdits = new HashMap<>();

    public static void setRadiusEdit(Player p, int value) {
        radiusEdits.put(p.getUniqueId(), value);
    }
    public static void setHeightEdits(Player p, int value) {
        heightEdits.put(p.getUniqueId(), value);
    }
    public static void setCountdownEdits(Player p, int value) {
        countdownEdits.put(p.getUniqueId(), value);
    }


}
