package me.mancy.arandisdrops.commands;

import me.mancy.arandisdrops.data.Strings;
import me.mancy.arandisdrops.tokens.Account;
import me.mancy.arandisdrops.tokens.AccountManager;
import me.mancy.arandisdrops.utils.Messager;
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
                    Player p = (Player) sender;
                    Account account = AccountManager.getPlayersAccount(p);
                    if (account == null) {
                        Messager.sendMessage(p, ChatColor.RED + "Error: No account found, rejoin server if you see this error");
                        return false;
                    }
                    Messager.sendMessage(p, ChatColor.AQUA + "Your Tokens:");
                    p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 1: " + ChatColor.AQUA + account.getBalance(1));
                    p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 2: " + ChatColor.AQUA + account.getBalance(2));
                    p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 3: " + ChatColor.AQUA + account.getBalance(3));
                    p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Tier 4: " + ChatColor.AQUA + account.getBalance(4));
                    return true;

                }
            } else if (args.length == 4) {
                if (((sender instanceof Player && (sender.hasPermission("dtokens.edit") || sender.hasPermission("dtokens.*") || sender.hasPermission("*"))) || sender instanceof ConsoleCommandSender)) {
                    if (Bukkit.getPlayer(args[0]) != null) {
                        Player target = Bukkit.getPlayer(args[0]);
                        if (target == null) {
                            Messager.sendMessage(sender, ChatColor.RED + "Player not found");
                            return false;
                        }
                        Account account = AccountManager.getPlayersAccount(target);
                        if (account == null) {
                            Messager.sendMessage(sender, ChatColor.RED + "Error: " + ChatColor.GRAY + "No account found");
                            return false;
                        }
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.addTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                Messager.sendMessage(target,ChatColor.AQUA + "You have received " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " Tokens");
                                Messager.sendMessage(sender, ChatColor.GRAY + "Added " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " tokens to " + target.getName() + "'s balance");
                            } else {
                                Messager.sendMessage(sender, ChatColor.RED + "Enter a tier between 1 and 4");
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.removeTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                Messager.sendMessage(target, ChatColor.RED + "You have lost " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " Tokens");
                                Messager.sendMessage(sender, ChatColor.GRAY + "Removed " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " tokens from " + target.getName() + "'s balance");
                            } else {
                                Messager.sendMessage(sender, ChatColor.RED + "Enter a tier between 1 and 4");
                            }
                        } else if (args[1].equalsIgnoreCase("set")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.setBalance(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                Messager.sendMessage(target, ChatColor.AQUA + "Your Tier " + Integer.parseInt(args[2]) + " Tokens Balance Has Been Set To " + Integer.parseInt(args[3]) + " Tokens");
                                Messager.sendMessage(sender, ChatColor.GRAY + "Set " + target.getName() + "'s Tier " + Integer.parseInt(args[2]) + " tokens to " + Integer.parseInt(args[3]));
                            } else {
                                Messager.sendMessage(sender, ChatColor.RED + "Enter a tier between 1 and 4");
                            }
                        } else {
                            Messager.sendMessage(sender, Strings.invalidArguments.trim());
                        }
                    } else if (Bukkit.getOfflinePlayer(args[0]) != null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                        if (target == null) {
                            Messager.sendMessage(sender, ChatColor.RED + "Player not found");
                            return false;
                        }
                        Account account = AccountManager.getOfflineAccount(target);
                        if (account == null)
                            account = new Account(target.getUniqueId(), 0);
                        if (args[1].equalsIgnoreCase("add")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.addTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                Messager.sendMessage(sender, ChatColor.GRAY + "Added " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " tokens to " + target.getName() + "'s balance");
                                return true;
                            } else {
                                Messager.sendMessage(sender, ChatColor.RED + "Enter a tier between 1 and 4");
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("remove")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.removeTokens(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                Messager.sendMessage(sender, ChatColor.RED + "Removed " + Integer.parseInt(args[3]) + " Tier " + Integer.parseInt(args[2]) + " tokens from " + target.getName() + "'s balance");
                                return true;
                            } else {
                                Messager.sendMessage(sender, ChatColor.RED + "Enter a tier between 1 and 4");
                                return false;
                            }
                        } else if (args[1].equalsIgnoreCase("set")) {
                            if (Integer.parseInt(args[2]) >= 1 && Integer.parseInt(args[2]) <= 4) {
                                account.setBalance(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
                                Messager.sendMessage(sender, ChatColor.GRAY + "Set" + target.getName() + "'s Tier " + Integer.parseInt(args[2]) + " tokens to " + Integer.parseInt(args[3]));
                                return true;
                            } else {
                                Messager.sendMessage(sender, ChatColor.RED + "Enter a tier between 1 and 4");
                                return false;
                            }
                        } else {
                            Messager.sendMessage(sender, Strings.invalidArguments.trim());
                            return false;
                        }
                    } else {
                        Messager.sendMessage(sender, ChatColor.RED + "Player not found");
                        return false;
                    }
                }
            } else {
                Messager.sendMessage(sender, Strings.invalidArguments.trim());
                return false;
            }
        }

        return false;
    }
}
