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
public class Op {

	public Op(Main main, CommandSender sender, String[] args) {
		if (args.length == 1 || args.length == 2) {
			sender.sendMessage(ChatColor.GREEN + "Usage: /sudo op <player> <command>.");
			return;
		}
		if (args[1].equalsIgnoreCase("all")) {
			if (sender.hasPermission("sudo.op.all")) {
				int arg;
				String string = "";
				for (arg = 2; arg < args.length; arg++) {
					string = string + args[arg] + " ";
				}
				for (Player player : main.getServer().getOnlinePlayers()) {
					boolean op = false;
					if (player.isOp())
						op = true;
					else
						player.setOp(true);
					main.getServer().dispatchCommand(player, string.replace("<player>", player.getName()).replace("<name>", player.getName()));
					if (!op)
						player.setOp(false);
				}
				sender.sendMessage(ChatColor.GREEN + "Successfully executed command as all players (OP)");
				return;
			}
			sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
			return;
		}
		if (sender.hasPermission("sudo.op")) {
			if (main.getServer().getPlayer(args[1]) instanceof Player) {
				Player player = main.getServer().getPlayer(args[1]);
				int arg;
				String string = "";
				for (arg = 2; arg < args.length; arg++) {
					string = string + args[arg] + " ";
				}
				sender.sendMessage(ChatColor.GREEN + "Successfully executed command as " + ChatColor.GOLD + player.getName() + " (OP)");
				boolean op = false;
				if (player.isOp())
					op = true;
				else
					player.setOp(true);
				main.getServer().dispatchCommand(player, string.replace("<player>", player.getName()).replace("<name>", player.getName()));
				if (!op)
					player.setOp(false);
			}
			sender.sendMessage(ChatColor.GREEN + "Couldn't find that player!");
			return;
		}
		sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
	}
}
