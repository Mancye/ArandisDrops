package me.mancy.arandisdrops.commands;

import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.menus.MainMenu;
import me.mancy.arandisdrops.menus.editor.EditorMainMenu;
import me.mancy.arandisdrops.parties.DropLocation;
import me.mancy.arandisdrops.parties.LocationManager;
import me.mancy.arandisdrops.utils.FormattedMessage;
import net.md_5.bungee.api.chat.*;
import org.apache.commons.lang.math.NumberUtils;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class BaseCMD implements CommandExecutor {

    private void sendHelpMsg(Player p) {
        String helpPrefix = ChatColor.GRAY + "-";
        p.sendMessage(" ");
        p.sendMessage(new FormattedMessage(ChatColor.AQUA + "Drop Party" + ChatColor.GRAY + " help page:").toString());
        p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops" + ChatColor.GRAY + " Opens the main drop menu");
        if (p.hasPermission("dropparty.edit") || p.hasPermission("dropparty.*") || p.hasPermission("*")) {
            p.sendMessage( helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops edit" + ChatColor.GRAY + " To edit drop party settings");
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops list" + ChatColor.GRAY + " To view available drop locations");
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops loc add" + ChatColor.GRAY + " To add a new drop location, must be standing on a beacon");
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops loc remove (number)" + ChatColor.GRAY + " To remove a drop location");
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /drops reload" + ChatColor.GRAY + " To reload strings file if changes are made");
        }
        p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /dtokens" + ChatColor.GRAY + " To view your tokens");

        if (p.hasPermission("dropparty.edittokens") || p.hasPermission("dropparty.*")) {
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /dtokens (player name) (add/remove/set) (tier) (amount)" + ChatColor.GRAY + " To modify a player's tokens");
        }
    }
    private void listLocations(Player p) {
        if (LocationManager.getValidatedLocations().size() > 0) {
            p.sendMessage(new FormattedMessage(ChatColor.RED + "Valid Drop Locations ").toString());
            for (int x = 0; x < LocationManager.getValidatedLocations().size(); x++) {
                BaseComponent message = new TextComponent(ChatColor.DARK_GRAY + "-" + ChatColor.GRAY + " Location #" + (x + 1));
                Location loc = LocationManager.getValidatedLocations().get(x);
                loc.setY(loc.getWorld().getHighestBlockYAt(loc));
                message.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + p.getName() + " " + loc.getX() + " " + loc.getY() + " " + loc.getZ()));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Click To Go To Location #" + (x + 1)).color(net.md_5.bungee.api.ChatColor.RED).create()));
                p.spigot().sendMessage(message);
            }
        } else {
            p.sendMessage(new FormattedMessage(ChatColor.RED + "No locations found").toString());
        }

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("drops")) {
            if (!(sender instanceof Player)) return false;
            Player p = (Player) sender;

            switch (args.length) {
                case 0:
                    MainMenu menu = new MainMenu();
                    menu.displayTokens(p);
                    menu.displayPriceDesc(p);
                    p.openInventory(menu.getInventory());
                    return true;
                case 1:
                    switch (args[0]) {
                        case "help":
                            sendHelpMsg(p);
                            return true;
                        case "list":
                            if (p.hasPermission("dropparty.list") || p.hasPermission("dropparty.*") || p.hasPermission("*")) {
                                listLocations(p);
                                return true;
                            } else {
                                p.sendMessage(new FormattedMessage(Strings.noPermission.trim()).toString());
                            }
                            break;
                        case "edit":
                            if (p.hasPermission("dropparty.edit") || p.hasPermission("dropparty.*") || p.hasPermission("*"))
                            p.openInventory(new EditorMainMenu().getInventory());
                            break;
                        case "reload":
                            if (p.hasPermission("dropparty.edit") || p.hasPermission("dropparty.*") || p.hasPermission("*")) {
                                Strings.reloadConfig();
                                p.sendMessage(new FormattedMessage(ChatColor.GRAY + "Reloaded strings file").toString());
                            }
                            break;
                        default:
                            break;

                    }
                    break;
                case 2:
                    if (args[0].equalsIgnoreCase("loc") && args[1].equalsIgnoreCase("add")) {
                        if (p.hasPermission("dropparty.edit") || p.hasPermission("dropparty.*") || p.hasPermission("*")) {
                            if (p.getLocation().getBlock().getRelative(BlockFace.DOWN).getType().equals(Material.BEACON)) {
                                if (LocationManager.getValidatedLocations().contains(LocationManager.getBlockLocation(p.getLocation())) || LocationManager.getUnValidatedLocations().contains(LocationManager.getBlockLocation(p.getLocation()))) {
                                    p.sendMessage(new FormattedMessage(ChatColor.RED + "A drop location has already been set at this location!").toString());
                                    return false;
                                }
                                DropLocation.playersEditing.add(p.getUniqueId());
                                LocationManager.addUnvalidatedLocation(p.getLocation());
                                p.sendMessage(new FormattedMessage(ChatColor.GRAY + "Location set, to validate it you must place a non-stained glass block directly above the beacon").toString());

                            } else {
                                p.sendMessage(new FormattedMessage(ChatColor.RED + "You must be standing on a beacon").toString());
                                return false;
                            }
                        } else {
                            p.sendMessage(new FormattedMessage(Strings.noPermission.trim()).toString());
                            return false;
                        }
                    } else {
                        p.sendMessage(new FormattedMessage(Strings.invalidArguments.trim()).toString());
                        return false;
                    }
                    break;
                case 3:
                    if (args[0].equalsIgnoreCase("loc") && args[1].equalsIgnoreCase("remove") && NumberUtils.isNumber(args[2])) {
                        if (p.hasPermission("dropparty.edit") || p.hasPermission("dropparty.*") || p.hasPermission("*")) {
                            int index = Integer.parseInt(args[2]) - 1;
                            if ((index + 1) <= 0) {
                                p.sendMessage(new FormattedMessage(ChatColor.RED + "Location index must be greater than 0, view a list of locations with " + ChatColor.AQUA + "/drops list").toString());
                                return false;
                            }
                            if (LocationManager.getValidatedLocations().isEmpty()) {
                                p.sendMessage(new FormattedMessage(ChatColor.RED + "No locations found").toString());
                                return false;
                            }
                            if (LocationManager.getValidatedLocations().size() - 1 >= index) {
                                Location loc = LocationManager.getValidatedLocations().get(index);
                                LocationManager.getValidatedLocations().remove(index);
                                LocationManager.getUnValidatedLocations().remove(loc);
                                p.sendMessage(new FormattedMessage(ChatColor.GRAY + "Removed location " + (index + 1) + "successfully").toString());
                                return true;
                            } else {
                                p.sendMessage(new FormattedMessage(ChatColor.RED + "Invalid location index, view a list of locations with " + ChatColor.AQUA + "/drops list").toString());
                                return false;
                            }
                        } else {
                            p.sendMessage(new FormattedMessage(Strings.noPermission.trim()).toString());
                            return false;
                        }
                    } else {
                        p.sendMessage(new FormattedMessage(Strings.invalidArguments.trim()).toString());
                        return false;
                    }

            }
        }
        return false;
    }
}
