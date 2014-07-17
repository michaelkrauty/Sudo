package me.michaelkrauty.Sudo.commands;

import me.michaelkrauty.Sudo.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created on 7/16/2014.
 *
 * @author michaelkrauty
 */
public class Console {

	public Console(Main main, CommandSender sender, String[] args) {
		if (args.length == 1) {
			sender.sendMessage(ChatColor.GREEN + "Usage: /sudo console <command>.");
			return;
		}
		if (sender.hasPermission("sudo.console")) {
			int arg;
			String string = "";
			for (arg = 1; arg < args.length; arg++) {
				string = string + args[arg] + " ";
			}
			main.getServer().dispatchCommand(main.getServer().getConsoleSender(), string);
			sender.sendMessage(ChatColor.GREEN + "Successfully executed command as " + ChatColor.GOLD + "CONSOLE");
			return;
		}
		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	}
}
