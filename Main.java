package me.dugiedugdug.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
	public List<Horse> bisonList;
	
	@Override
	public void onEnable() {
		System.out.println("load Sky bison plugin successful");
	
		bisonList = new ArrayList<>();
		
		getCommand("opengui").setExecutor(new GUICommand(this));
		getCommand("summonbison").setExecutor(new SummonBison(this));
		Bukkit.getPluginManager().registerEvents(new GUIListener(this), this);
		Bukkit.getPluginManager().registerEvents(new BisonListener(this), this);
	}
	
	@Override
	public void onDisable() {
		System.out.println("disable Sky bison plugin successful");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		return false;
	}
	
	public HashMap<Player, ItemStack> hatSlot = new HashMap<>();
	
	// SKY BISON INTERFACE
	public void applyUI(Player player) {
		
		// INIT
		Inventory gui = Bukkit.createInventory(null,9,ChatColor.AQUA + "Sky bison menu");
		
		// LORES
		// using array lists here to disable lore in rows
		List<String> hatEnableLore = new ArrayList<>();
		hatEnableLore.add(ChatColor.GRAY + "Click to apply");
		hatEnableLore.add(ChatColor.GRAY + "a nice lil hat");
		
		List<String> hatDisableLore = new ArrayList<>();
		hatDisableLore.add(ChatColor.GRAY + "Click to take off");
		hatDisableLore.add(ChatColor.GRAY + "that fancy hat");
		
		List<String> eggLore = new ArrayList<>();
		eggLore.add(ChatColor.GRAY + "Can I offer you an egg");
		eggLore.add(ChatColor.GRAY + "in this trying time");
		
		
		// ITEMSTACKS
		ItemStack toggle;
		ItemMeta toggleMeta;
		if (player.getInventory().getHelmet() != null && player.getInventory().getHelmet().getType().equals(Material.LEATHER_HELMET)) {
			toggle = new ItemStack(Material.RED_WOOL);
			toggleMeta = toggle.getItemMeta();
			toggleMeta.setDisplayName(ChatColor.RED + "Remove Hat");
			toggleMeta.setLore(hatDisableLore);
		} else {
			toggle = new ItemStack(Material.GREEN_WOOL);
			toggleMeta = toggle.getItemMeta();
			toggleMeta.setDisplayName(ChatColor.GREEN + "Put on a hat");
			toggleMeta.setLore(hatEnableLore);
		}
		toggle.setItemMeta(toggleMeta);
		
		
		ItemStack egg = new ItemStack(Material.EGG);
		ItemMeta eggMeta = egg.getItemMeta();
		eggMeta.setDisplayName(ChatColor.YELLOW + "Get Egg");
		eggMeta.setLore(eggLore);
		egg.setItemMeta(eggMeta);

		
		// ITEM CONFIG
		
		gui.setItem(3,toggle);
		gui.setItem(5, egg);
		
		// END
		
		player.openInventory(gui);
		
	}
}
