package me.mancy.alphadrops.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class DPCommand implements CommandExecutor {


    String label;
    String[] args;

    public String getLabel() {
        return label;
    }

    public String[] getArgs() {
        return args;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        return false;
    }
}
