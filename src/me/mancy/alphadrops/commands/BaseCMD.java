package me.mancy.alphadrops.commands;

import me.mancy.alphadrops.menus.editor.EditorMainMenu;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BaseCMD implements CommandExecutor {

    private void sendHelpMsg(Player p) {
        String helpPrefix = ChatColor.GRAY + "-";
        p.sendMessage(" ");
        p.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.WHITE + ChatColor.BOLD.toString() + "P" + ChatColor.RED + ChatColor.BOLD.toString() + "A" + ChatColor.DARK_GRAY + ":" + ChatColor.GRAY + "Events" + ChatColor.DARK_GRAY + "] " + ChatColor.GREEN + "Drop Party" + ChatColor.GRAY + " help page:");
        p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /drops" + ChatColor.GRAY + " Opens the main drop menu");
        if (p.hasPermission("dropparty.edit") || p.hasPermission("dropparty.*")) {
            p.sendMessage( helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /drops edit" + ChatColor.GRAY + " To edit drop party settings");
            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + "/drops list" + ChatColor.GRAY + " To view available drop locations");
        }
        p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /dtokens" + ChatColor.GRAY + " To view your tokens");

        if (p.hasPermission("dropparty.edittokens") || p.hasPermission("dropparty.*")) {
            p.sendMessage(helpPrefix + ChatColor.GREEN + ChatColor.ITALIC.toString() + " /dtokens (player name) (add/remove/set) (tier) (amount)" + ChatColor.GRAY + " To modify a player's tokens");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        if (label.equalsIgnoreCase("drops")) {
            Player p = (Player) sender;
            switch (args.length) {
                case 0:
                    /*
                    Display help menu
                     */
                case 1:
                    /*
                    help, list, edit
                     */
                    switch (args[0]) {
                        case "help":
                            sendHelpMsg(p);
                            break;
                        case "list":
                            //TODO list drop locations
                            break;
                        case "edit":
                            p.openInventory(new EditorMainMenu().getMenu());
                            break;
                    }
                case 2:
                    /*
                    loc add
                     */
                case 3:
                    /*
                    loc remove (index)
                     */
            }
        }

        return false;
    }
}
