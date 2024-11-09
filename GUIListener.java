package me.dugiedugdug.plugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GUIListener implements Listener {
	
	private Main main;
	private List<String> eggLore;
	
	
	public GUIListener(Main m) {
		this.main = m;
	}
	
	// Event handler for interaction with the GUI items
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		
		if (e.getClickedInventory().getSize() == 9 && e.getClickedInventory().getItem(5) != null && e.getClickedInventory().getItem(5).getItemMeta().getLore().equals(eggLore)) {
			if (e.getCurrentItem() != null) {
				e.setCancelled(true);
				// Identify selected option and execute necessary command
				switch (e.getCurrentItem().getType()) {
				case GREEN_WOOL:
					if (player.getInventory().getHelmet() != null) {
						main.hatSlot.put(player, player.getInventory().getHelmet());
					}
					ItemStack coolHat = new ItemStack(Material.LEATHER_HELMET);
					ItemMeta hatMeta = coolHat.getItemMeta();
					hatMeta.setDisplayName(ChatColor.LIGHT_PURPLE + "H" + ChatColor.AQUA + "A" + ChatColor.GREEN + "T");
					coolHat.setItemMeta(hatMeta);
					player.getInventory().setHelmet(coolHat);
					
					player.sendMessage("Nice hat!");
					break;
				case RED_WOOL:
					if (main.hatSlot.containsKey(player)) {
						player.getInventory().setHelmet(main.hatSlot.get(player));
					} else {
						player.getInventory().setHelmet(null);
					}
					break;
				case EGG:
					player.getInventory().addItem(new ItemStack(Material.EGG));
					break;
				default:
					return;
				}
				
				player.closeInventory();
			}
			
			
		}
	}
}
