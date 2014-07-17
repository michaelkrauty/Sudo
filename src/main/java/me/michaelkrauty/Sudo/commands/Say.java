package me.michaelkrauty.Sudo.commands;

import me.michaelkrauty.Sudo.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 7/16/2014.
 *
 * @author michaelkrauty
 */
public class Say {

	public Say(Main main, CommandSender sender, String[] args) {
		if (args.length == 1 || args.length == 2) {
			sender.sendMessage(ChatColor.GREEN + "Usage: /sudo say <player> <message>");
			return;
		}
		if (args[1].equalsIgnoreCase("all")) {
			if (sender.hasPermission("sudo.say.all")) {
				int arg;
				String string = "";
				for (arg = 2; arg < args.length; arg++) {
					string = string + args[arg] + " ";
				}
				for (Player player : main.getServer().getOnlinePlayers()) {
					player.chat(string.replace("<player>", player.getName()).replace("<name>", player.getName()));
				}
				sender.sendMessage(ChatColor.GREEN + "Successfully said message as all");
				return;
			}
			sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			return;
		}
		if (sender.hasPermission("sudo.say")) {
			if (main.getServer().getPlayer(args[1]) instanceof Player) {
				int arg;
				String string = "";
				for (arg = 2; arg < args.length; arg++) {
					string = string + args[arg] + " ";
				}
				main.getServer().getPlayer(args[1]).chat(string);
				sender.sendMessage(ChatColor.GREEN + "Successfully said message as " + ChatColor.GOLD + main.getServer().getPlayer(args[1]).getName());
				return;
			}
			sender.sendMessage(ChatColor.GREEN + "Couldn't find that player!");
			return;
		}
		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	}
}
