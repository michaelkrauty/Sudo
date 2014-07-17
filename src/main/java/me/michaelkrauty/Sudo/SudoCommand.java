package me.michaelkrauty.Sudo;

import me.michaelkrauty.Sudo.commands.*;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created on 7/16/2014.
 *
 * @author michaelkrauty
 */
class SudoCommand implements CommandExecutor {

	private static Main main;

	public SudoCommand(Main main) {
		SudoCommand.main = main;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]) {
		if (args.length == 0) {
			new Help(sender, null);
			return true;
		}
		if (args[0].equalsIgnoreCase("help")) {
			new Help(sender, args);
			return true;
		}
		if (args[0].equalsIgnoreCase("op")) {
			new Op(main, sender, args);
			return true;
		}
		if (args[0].equalsIgnoreCase("console")) {
			new Console(main, sender, args);
			return true;
		}
		if (args[0].equalsIgnoreCase("say")) {
			new Say(main, sender, args);
			return true;
		}
		if (args[0].equalsIgnoreCase("all")) {
			new All(main, sender, args);
			return true;
		}
		if (main.getServer().getPlayer(args[0]) instanceof Player) {
			new Sudo(main, sender, args);
			return true;
		}
		sender.sendMessage(ChatColor.GREEN + "Couldn't find that player!");
		return true;
	}
}
