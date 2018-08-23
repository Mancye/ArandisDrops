package me.mancy.arandisdrops.commands;

import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.menus.MainMenu;
import me.mancy.arandisdrops.menus.editor.EditorMainMenu;
import me.mancy.arandisdrops.utils.FormattedMessage;
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
                    if (p.hasPermission("dropparty.mainmenu") || p.hasPermission("dropparty.*")) {
                        p.openInventory(new MainMenu().getInventory());
                        return true;
                    } else {
                        p.sendMessage(Strings.noPermission);
                        return false;
                    }
                case 1:
                    /*
                    help, list, edit
                     */
                    switch (args[0]) {
                        case "help":
                            sendHelpMsg(p);
                            return true;
                        case "list":
                            if (p.hasPermission("dropparty.list") || p.hasPermission("dropparty.*")) {

                            } else {
                                p.sendMessage(Strings.noPermission);
                            }
                            break;
                        case "edit":
                            p.openInventory(new EditorMainMenu().getInventory());
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
