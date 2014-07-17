package me.michaelkrauty.Sudo;

/**
 * Created on 7/16/2014.
 *
 * @author michaelkrauty
 */

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public void onEnable() {
		getServer().getPluginCommand("sudo").setExecutor(new SudoCommand(this));
	}
}