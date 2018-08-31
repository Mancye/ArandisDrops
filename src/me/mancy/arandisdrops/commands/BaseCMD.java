package me.mancy.arandisdrops.commands;

import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.menus.MainMenu;
import me.mancy.arandisdrops.menus.editor.EditorMainMenu;
import me.mancy.arandisdrops.parties.DropLocation;
import me.mancy.arandisdrops.parties.LocationManager;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class BaseCMD implements CommandExecutor {

    private void sendHelpMsg(Player p) {
        String helpPrefix = ChatColor.GRAY + "-";
        p.sendMessage(" ");
        p.sendMessage(Strings.prefix + ChatColor.AQUA + " Drop Party" + ChatColor.GRAY + " help page:");
        p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops" + ChatColor.GRAY + " Opens the main drop menu");
        if (p.hasPermission("dropparty.edit") || p.hasPermission("dropparty.*")) {
            p.sendMessage( helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops edit" + ChatColor.GRAY + " To edit drop party settings");
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops list" + ChatColor.GRAY + " To view available drop locations");
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops reload" + ChatColor.GRAY + " To reload strings file if changes are made");
        }
        p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /dtokens" + ChatColor.GRAY + " To view your tokens");

        if (p.hasPermission("dropparty.edittokens") || p.hasPermission("dropparty.*")) {
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /dtokens (player name) (add/remove/set) (tier) (amount)" + ChatColor.GRAY + " To modify a player's tokens");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("drops")) {
            if (!(sender instanceof Player)) return false;
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
                        case "reload":
                            Strings.reloadConfig();
                            break;
                        default:
                            break;

                    }
                    break;
                case 2:
                    if (args[0].equalsIgnoreCase("loc") && args[1].equalsIgnoreCase("add")) {
                        if (p.hasPermission("dropparty.editlocations") || p.hasPermission("dropparty.*")) {
                            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.BEACON)) {

                                DropLocation.playersEditing.add(p.getUniqueId());
                                LocationManager.addUnvalidatedLocation(p.getLocation());
                                p.sendMessage(new FormattedMessage(ChatColor.GRAY + "Location set, to validate it you must place a non-stained glass block directly above the beacon").toString());

                            } else {
                                p.sendMessage(new FormattedMessage(ChatColor.RED + "You must be standing on a beacon").toString());
                                return false;
                            }
                        } else {
                            p.sendMessage(new FormattedMessage(Strings.noPermission).toString());
                            return false;
                        }
                    } else {
                        p.sendMessage(new FormattedMessage(Strings.invalidArguments).toString());
                        return false;
                    }
                    break;
                case 3:
                    if (args[0].equalsIgnoreCase("loc") && args[1].equalsIgnoreCase("remove") && NumberUtils.isNumber(args[2])) {
                        if (p.hasPermission("dropparty.editlocations") || p.hasPermission("dropparty.*")) {
                            int index = Integer.parseInt(args[2]) - 1;
                            if (LocationManager.getValidatedLocations().size() >= index) {
                                LocationManager.getValidatedLocations().remove(1);
                            } else {
                                p.sendMessage(new FormattedMessage(ChatColor.RED + "Invalid location index.").toString());
                            }
                        } else {
                            p.sendMessage(new FormattedMessage(Strings.noPermission).toString());
                            return false;
                        }
                    } else {
                        p.sendMessage(new FormattedMessage(Strings.invalidArguments).toString());
                        return false;
                    }
                    break;
            }
        }
        return false;
    }
}
