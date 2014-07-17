package me.michaelkrauty.Sudo.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Created on 7/16/2014.
 *
 * @author michaelkrauty
 */
public class Help {

	public Help(CommandSender sender, String[] args) {
		if (sender.hasPermission("sudo.help")) {
			if (args == null || args.length != 2) {
				sender.sendMessage(ChatColor.GREEN + "Sudo commands:");
				sender.sendMessage(ChatColor.GREEN + "/sudo <player> <command>: Make a player execute a command as themself.");
				sender.sendMessage(ChatColor.GREEN + "/sudo op <player> <command>: Make a player execute a command as op.");
				sender.sendMessage(ChatColor.GREEN + "/sudo console <command>: Execute a command as console.");
				sender.sendMessage(ChatColor.GREEN + "/sudo say <player> <message>: Make a player say something.");
				sender.sendMessage(ChatColor.GREEN + "/sudo help: Show this help dialogue.");
				sender.sendMessage(ChatColor.GREEN + "Use /sudo help permissions for a list of permissions.");
				return;
			}
			if (args[1].equalsIgnoreCase("permissions")) {
				sender.sendMessage(ChatColor.GREEN + "Sudo permissions:");
				sender.sendMessage(ChatColor.GREEN + "sudo.sudo: Allow a player to sudo another player.");
				sender.sendMessage(ChatColor.GREEN + "sudo.*: Allow a player to use all Sudo commands.");
				sender.sendMessage(ChatColor.GREEN + "sudo.op: Allow a player to sudo another player as op.");
				sender.sendMessage(ChatColor.GREEN + "sudo.console: Allow a player to execute as console.");
				sender.sendMessage(ChatColor.GREEN + "sudo.say: Allow a player to force another player to say something.");
				sender.sendMessage(ChatColor.GREEN + "sudo.help: Give player access to this help dialogue.");
				return;
			}
			sender.sendMessage(ChatColor.GREEN + "Usage: /sudo help permissions.");
			return;
		}
		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	}
}
