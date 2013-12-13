package me.michaelkrauty.Sudo;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public class Sudo extends JavaPlugin{
	Logger log = Logger.getLogger("Minecraft");
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String args[]){
		if(commandLabel.equalsIgnoreCase("sudo")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.GREEN + "You didn't specify any arguments! Use /sudo help for help.");
				return true;
			}
			if(args[0].equalsIgnoreCase("help")){
				if(sender.hasPermission("sudo.help")){
					if(args.length == 0){
						sender.sendMessage(ChatColor.GREEN + "Usage: /sudo help.");
						return true;
					}
					if(args.length == 1){
						sender.sendMessage(ChatColor.GREEN + "Sudo commands:");
						sender.sendMessage(ChatColor.GREEN + "/sudo <player> <command>: Make a player execute a command as themself.");
						sender.sendMessage(ChatColor.GREEN + "/sudo op <player> <command>: Make a player execute a command as op.");
						sender.sendMessage(ChatColor.GREEN + "/sudo console <command>: Execute a command as console.");
						sender.sendMessage(ChatColor.GREEN + "/sudo say <player> <message>: Make a player say something.");
						sender.sendMessage(ChatColor.GREEN + "/sudo help: Show this help dialogue.");
						sender.sendMessage(ChatColor.GREEN + "Use /sudo help permissions for a list of permissions.");
						return true;
					}
					if(args.length == 2){
						if(args[1].equalsIgnoreCase("permissions")){
							sender.sendMessage(ChatColor.GREEN + "Sudo permissions:");
							sender.sendMessage(ChatColor.GREEN + "sudo.sudo: Allow a player to sudo another player.");
							sender.sendMessage(ChatColor.GREEN + "sudo.*: Allow a player to use all Sudo commands.");
							sender.sendMessage(ChatColor.GREEN + "sudo.op: Allow a player to sudo another player as op.");
							sender.sendMessage(ChatColor.GREEN + "sudo.console: Allow a player to execute as console.");
							sender.sendMessage(ChatColor.GREEN + "sudo.say: Allow a player to force another player to say something.");
							sender.sendMessage(ChatColor.GREEN + "sudo.help: Give player access to this help dialogue.");
							return true;
						}else{
							sender.sendMessage(ChatColor.GREEN + "Usage: /sudo help permissions.");
							return true;
						}
					}
					sender.sendMessage(ChatColor.GREEN + "Usage: /sudo help.");
					return true;
				}else{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}
			if(getServer().getPlayer(args[0]) instanceof Player){
				if(args.length == 1){
					sender.sendMessage(ChatColor.GREEN + "Usage: /sudo <player> <command>.");
					return true;
				}
				if(sender.hasPermission("sudo.sudo")){
					int arg = 0;
					String string = "";
					for(arg = 1; arg < args.length; arg++){
						string = string + args[arg] + " ";
					}
					sender.sendMessage(ChatColor.GREEN + "Successfully executed command as " + ChatColor.GOLD + getServer().getPlayer(args[0]).getName());
					getServer().dispatchCommand(getServer().getPlayer(args[0]), string);
					log.info(sender.getName() + " made " + getServer().getPlayer(args[0]).getName() + " run the command " + string);
					return true;
				}else{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}
			if(args[0].equalsIgnoreCase("op")){
				if(args.length == 1 || args.length == 2){
					sender.sendMessage(ChatColor.GREEN + "Usage: /sudo op <player> <command>.");
					return true;
				}
				if(sender.hasPermission("sudo.op")){
					if(getServer().getPlayer(args[1]) instanceof Player){
						int arg = 0;
						String string = "";
						for(arg = 2; arg < args.length; arg++){
							string = string + args[arg] + " ";
						}
						sender.sendMessage(ChatColor.GREEN + "Successfully executed command as " + ChatColor.GOLD + getServer().getPlayer(args[1]).getName() + " (OP)");
						log.info(sender.getName() + " made " + getServer().getPlayer(args[1]).getName() + " run the command " + string + "as OP");
						if(!getServer().getPlayer(args[1]).isOp()){
							getServer().getPlayer(args[1]).setOp(true);
							getServer().dispatchCommand(getServer().getPlayer(args[1]), string);
							getServer().getPlayer(args[1]).setOp(false);
							return true;
						}else{
							getServer().dispatchCommand(getServer().getPlayer(args[1]), string);
							return true;
						}
					}else{
						sender.sendMessage(ChatColor.GREEN + "Couldn't find that player!");
						return true;
					}
				}else{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}
			if(args[0].equalsIgnoreCase("console")){
				if(args.length == 1){
					sender.sendMessage(ChatColor.GREEN + "Usage: /sudo console <command>.");
					return true;
				}
				if(sender.hasPermission("sudo.console")){
					int arg = 0;
					String string = "";
					for(arg = 1; arg < args.length; arg++){
						string = string + args[arg] + " ";
					}
					sender.sendMessage(ChatColor.GREEN + "Sucessfully executed command as " + ChatColor.GOLD + "CONSOLE");
					log.info(sender.getName() + " ran the console command: " + string);
					getServer().dispatchCommand(getServer().getConsoleSender(), string);
					return true;
				}else{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}
			if(args[0].equalsIgnoreCase("say")){
				if(args.length == 1 || args.length == 2){
					sender.sendMessage(ChatColor.GREEN + "Usage: /sudo say <player> <message>");
					return true;
				}
				if(sender.hasPermission("sudo.say")){
					if(getServer().getPlayer(args[1]) instanceof Player){
						int arg = 0;
						String string = "";
						for(arg = 2; arg < args.length; arg++){
							string = string + args[arg] + " ";
						}
						sender.sendMessage(ChatColor.GREEN + "Successfully said message as " + ChatColor.GOLD + getServer().getPlayer(args[1]).getName());
						log.info(sender.getName() + " made " + getServer().getPlayer(args[1]).getName() + " say " + string);
						getServer().getPlayer(args[1]).chat(string);
						return true;
					}else{
						sender.sendMessage(ChatColor.GREEN + "Couldn't find that player!");
						return true;
					}
				}else{
					sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
					return true;
				}
			}
			sender.sendMessage(ChatColor.GREEN + "Couldn't find that player!");
			return true;
		}
		return true;
	}
}