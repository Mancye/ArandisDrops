package me.mancy.arandisdrops.commands;

import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import me.mancy.arandisdrops.utils.FormattedMessage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DTokensCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (label.equalsIgnoreCase("dtokens")) {

            if (args.length == 0) {
                if (sender instanceof Player) {
                    if (sender.hasPermission("dtokens.view") || sender.hasPermission("dtokens.*")) {
                        Player p = (Player) sender;
                        if (AccountManager.getPlayersAccount(p) == null) {
                            p.sendMessage(new FormattedMessage(ChatColor.RED + "Error: No account found, rejoin server if you see this error").toString());
                        }
                        Account account = AccountManager.getPlayersAccount(p);
                        p.sendMessage(new FormattedMessage(ChatColor.AQUA + "Your Tokens:").toString());
                        p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 1: " + ChatColor.AQUA + account.getBalance(1));
                        p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 2: " + ChatColor.AQUA + account.getBalance(2));
                        p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 3: " + ChatColor.AQUA + account.getBalance(3));
                        p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 4: " + ChatColor.AQUA + account.getBalance(4));
                        return true;
                    } else {
                        sender.sendMessage(new FormattedMessage(Strings.noPermission).toString());
                        return false;
                    }
                }
            } else if (args.length == 4) {
                if (((sender instanceof Player && (sender.hasPermission("dtokens.edit") || sender.hasPermission("dtokens.*"))) || sender instanceof ConsoleCommandSender)) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        Account account = AccountManager.getPlayersAccount(target);
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.addTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                target.sendMessage(new FormattedMessage(ChatColor.AQUA + "You have received " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " Tokens").toString());
                            } else {
                                sender.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.removeTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                target.sendMessage(new FormattedMessage(ChatColor.RED + "You have lost " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " Tokens").toString());
                            } else {
                                sender.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                            }
                        } else if (args[1].equalsIgnoreCase("set")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.setBalance(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                target.sendMessage(new FormattedMessage(ChatColor.AQUA + "Your Tier " + Integer.parseInt(args[2]) + " Tokens Balance Has Been Set To " + Integer.parseInt(args[3]) + " Tokens").toString());

                            } else {
                                sender.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                            }
                        } else {
                            sender.sendMessage(Strings.invalidArguments);
                        }
                    } else if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                        if (AccountManager.getOfflineAccount(target) == null)
                            new Account(target.getUniqueId(), 0);
                        Account account = AccountManager.getOfflineAccount(target);
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.addTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.removeTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                            }
                        } else if (args[1].equalsIgnoreCase("set")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.setBalance(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                            } else {
                                sender.sendMessage(new FormattedMessage(ChatColor.RED + "Enter a tier between 1 and 4").toString());
                            }
                        } else {
                            sender.sendMessage(Strings.invalidArguments);
                        }
                    } else {
                        sender.sendMessage(new FormattedMessage(ChatColor.RED + "Player not found").toString());
                        return false;
                    }
                }
            } else {
                sender.sendMessage(new FormattedMessage(Strings.invalidArguments).toString());
                return false;
            }
        }

        return false;
    }
}
