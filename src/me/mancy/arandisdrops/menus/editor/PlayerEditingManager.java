package me.mancy.arandisdrops.menus.editor;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerEditingManager {

    public static PlayerEditingManager instance = new PlayerEditingManager();

    public Map<Player, SettingType> playersEditingMap = new HashMap<>();
    public Map<Player, Integer> playerTierEditingMap = new HashMap<>();
    public Map<Player, Integer> playerRarityEditingMap = new HashMap<>();
}
