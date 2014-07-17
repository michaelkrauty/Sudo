package me.michaelkrauty.Sudo.commands;

import me.michaelkrauty.Sudo.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created on 7/16/2014.
 *
 * @author michaelkrauty
 */
public class Sudo {

	public Sudo(Main main, CommandSender sender, String[] args) {
		if (sender.hasPermission("sudo.sudo")) {
			if (args.length == 1) {
				sender.sendMessage(ChatColor.GREEN + "Usage: /sudo <player> <command>.");
				return;
			}
			int arg;
			String string = "";
			for (arg = 1; arg < args.length; arg++) {
				string = string + args[arg] + " ";
			}
			main.getServer().dispatchCommand(main.getServer().getPlayer(args[0]), string);
			sender.sendMessage(ChatColor.GREEN + "Successfully executed command as " + ChatColor.GOLD + main.getServer().getPlayer(args[0]).getName());
			return;
		}
		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	}
}
