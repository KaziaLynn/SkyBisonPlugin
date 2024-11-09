package me.dugiedugdug.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GUICommand implements CommandExecutor {
	private Main main;
	
	public GUICommand(Main m) {
		this.main = m;
	}
	
	// Handles the command to bring up the GUI inventory
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (sender instanceof Player) {
			main.applyUI((Player) sender);
		}
		
		
		return false;
	}
}
