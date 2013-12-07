package net.lordsofcode.tenjava;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;

public class LoricListener implements Listener {

	private Loric plugin;

	public LoricListener(Loric plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			if (plugin.game.isSafe(player)) {
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onMove(PlayerMoveEvent event) {
		if (event.getTo().getX() > 1000 || event.getTo().getX() < -1000
				|| event.getTo().getZ() > 1000 || event.getTo().getZ() < -1000) {
			event.getPlayer().sendMessage("You have reached the edge");
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onDeath(PlayerDeathEvent event) {
		plugin.game.onDeath(event);
	}

}
