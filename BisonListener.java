package me.dugiedugdug.plugin;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.util.Vector;
import org.spigotmc.event.entity.EntityDismountEvent;

public class BisonListener implements Listener {
	
	private Main main;
	public BisonListener(Main m) {
		this.main = m;
	}

	// DISMOUNT EVENT LISTENER
	@EventHandler
	public void onDismountTry(EntityDismountEvent e) {
		if(main.bisonList.contains(e.getEntity())) {
			if(e.getDismounted() instanceof Player) {
				Player player = (Player) e.getDismounted();
				Vector dir = player.getLocation().getDirection();
				e.getEntity().setVelocity(dir);
				e.setCancelled(true);
				
			}
		}
		System.out.print("Dismount event test successful");
	} 
}
 