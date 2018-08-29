package me.mancy.arandisdrops.commands;

import me.mancy.arandisdrops.data.Settings;
import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.menus.MainMenu;
import me.mancy.arandisdrops.menus.editor.EditorMainMenu;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
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
        }
        p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /dtokens" + ChatColor.GRAY + " To view your tokens");

        if (p.hasPermission("dropparty.edittokens") || p.hasPermission("dropparty.*")) {
            p.sendMessage(helpPrefix + ChatColor.AQUA + ChatColor.ITALIC.toString() + " /dtokens (player name) (add/remove/set) (tier) (amount)" + ChatColor.GRAY + " To modify a player's tokens");
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return false;

        if (label.equalsIgnoreCase("drops")) {
            Player p = (Player) sender;
            p.sendMessage("Drop Radius: " + Settings.getDropRadius());

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
                case 2:
                    /*
                    loc add
                     */
                case 3:
                    /*
                    loc remove (index)
                     */
            }
        } else if (label.equalsIgnoreCase("dtokens")) {
            Player p = (Player) sender;
            switch (args.length) {
                case 0:
                    if (p.hasPermission("dtokens.view")) {
                        Account account = AccountManager.getAccount(p);
                        if (account == null) {
                            account = new Account(p, 0);
                        } else {
                            AccountManager.addAccount(account);
                        }

                        p.sendMessage(new FormattedMessage(ChatColor.AQUA + "Your Tokens:").toString());
                        p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 1: " + ChatColor.AQUA + account.getBalance(1));
                        p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 2: " + ChatColor.AQUA + account.getBalance(2));
                        p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 3: " + ChatColor.AQUA + account.getBalance(3));
                        p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 4: " + ChatColor.AQUA + account.getBalance(4));
                    } else {
                        p.sendMessage(Strings.noPermission);
                    }
                    break;
                case 4:
                    if (p.hasPermission("dtokens.edit")) {
                        if (Bukkit.getPlayer(args[0]) != null) {
                            Player target = Bukkit.getPlayer(args[0]);
                            Account account = AccountManager.getAccount(target);
                            if (account == null) {
                                account = new Account(Bukkit.getPlayer(args[0]), 0);
                                AccountManager.addAccount(account);
                                System.out.println("New account created");
                            }
                            if (args[1].equalsIgnoreCase("add")) {
                                if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                    account.addTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                    target.sendMessage(new FormattedMessage(ChatColor.AQUA + "You have received " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " Tokens").toString());
                                } else {
                                    p.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                                }
                            } else if (args[1].equalsIgnoreCase("remove")) {
                                if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                    account.removeTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                    target.sendMessage(new FormattedMessage(ChatColor.RED + "You have lost " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " Tokens").toString());
                                } else {
                                    p.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                                }
                            } else if (args[1].equalsIgnoreCase("set")) {
                                if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                    account.setBalance(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                    target.sendMessage(new FormattedMessage(ChatColor.AQUA + "Your Tier " + Integer.parseInt(args[2]) + " Tokens Balance Has Been Set To " + Integer.parseInt(args[3]) + " Tokens").toString());

                                } else {
                                    p.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                                }
                            } else {
                                p.sendMessage(Strings.invalidArguments);
                            }
                        } else {
                            p.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a valid player name.").toString());
                            return true;
                        }
                    } else {
                        p.sendMessage(Strings.noPermission);
                    }
                    break;
                    default:
                        p.sendMessage(Strings.invalidArguments);
                        return true;
            }
        }

        return false;
    }
}
