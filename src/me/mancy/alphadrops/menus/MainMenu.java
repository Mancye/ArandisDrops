package me.mancy.alphadrops.menus;

import javafx.scene.effect.DropShadow;
import me.mancy.alphadrops.tokens.AccountManager;
import me.mancy.alphadrops.tokens.DropCosts;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class MainMenu extends GUI {

   public MainMenu() {
       super(27, ChatColor.GREEN + "Start a drop party!");
       List<String> tier1Desc = new ArrayList<>();
       tier1Desc.add(ChatColor.GREEN + ChatColor.ITALIC.toString() + "Click To Start A Tier One Drop Party!");
       tier1Desc.add(ChatColor.GRAY + ChatColor.ITALIC.toString() + "    COST: " + ChatColor.GREEN + DropCosts.getCost(1) + " Token(s)");
       setItem(10, new ItemStack(Material.COAL_ORE), (ChatColor.AQUA + "Tier 1"), tier1Desc, player -> {
        if (AccountManager.getAccount(player).getBalance(1) >= DropCosts.getCost(1)) {

        }
       });
   }


}
