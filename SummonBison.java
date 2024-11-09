package me.dugiedugdug.plugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.EulerAngle;

public class SummonBison implements CommandExecutor {

	private Main main;
	
	public SummonBison(Main m) {
		this.main = m;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			// Instantiate new bison (horse entity)
			Player player = (Player) sender;
			Horse newBison = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
			newBison.setGravity(false); newBison.setInvulnerable(true); newBison.setSilent(true); 
			newBison.setAI(false); newBison.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,999999,1,true,false));
			
			// Create bison model entity (armor stand entity)
			ArmorStand newBisonModel = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
			newBisonModel.setMarker(true); newBisonModel.setVisible(false); newBisonModel.setGravity(false);
			newBisonModel.setArms(true); newBisonModel.setLeftArmPose(new EulerAngle(Math.toRadians(270),0,0));
			
			// Add custom model to bison model entity
			ItemStack newBisonItem = new ItemStack(Material.CARROT_ON_A_STICK);
			ItemMeta itemDetails = newBisonItem.getItemMeta();
			itemDetails.setDisplayName("[SkyBison Model]"); itemDetails.setCustomModelData(2);
			newBisonItem.setItemMeta(itemDetails);
			
			newBisonModel.getEquipment().setItemInOffHand(newBisonItem);
			newBison.addPassenger(newBisonModel);
			
			// Track bison in bisonList
			main.bisonList.add(newBison);
			player.sendMessage(main.bisonList.get(0).toString());
			System.out.println(main.bisonList.toString());
		}
				
		
		return false;
	}
}
