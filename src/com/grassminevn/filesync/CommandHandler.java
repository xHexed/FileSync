package com.grassminevn.filesync;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (args.length < 1 || (sender instanceof Player && !sender.hasPermission("admin"))) return false;
        switch (args[0].toLowerCase()) {
            case "reload":
                FileSync.reloadSettings();
                sender.sendMessage("Reload xong");
                return true;
            case "sync":
                if (args.length < 2) return false;
                Util.sync(FileSync.getInstance().getConfig().getConfigurationSection(args[1]));
                return true;
            default:
                return true;
        }
    }
}