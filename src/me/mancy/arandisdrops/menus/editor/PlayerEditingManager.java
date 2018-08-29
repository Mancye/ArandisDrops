package me.mancy.arandisdrops.menus.editor;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerEditingManager {

    public static Map<Player, SettingType> playersEditingMap = new HashMap<>();
    public static Map<Player, Integer> playerTierEditingMap = new HashMap<>();
    public static Map<Player, Integer> playerRarityEditingMap = new HashMap<>();
}
