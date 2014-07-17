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
public class All {

	public All(Main main, CommandSender sender, String[] args) {
		if (args.length == 1) {
			sender.sendMessage(ChatColor.GREEN + "Usage: /sudo all <command>");
			return;
		}
		if (sender.hasPermission("sudo.all")) {
			int arg;
			String string = "";
			for (arg = 1; arg < args.length; arg++) {
				string = string + args[arg] + " ";
			}
			for (Player player : main.getServer().getOnlinePlayers()) {
				main.getServer().dispatchCommand(player, string.replace("<player>", player.getName()).replace("<name>", player.getName()));
			}
			sender.sendMessage(ChatColor.GREEN + "Successfully executed command as all players");
			return;
		}
		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	}
}
