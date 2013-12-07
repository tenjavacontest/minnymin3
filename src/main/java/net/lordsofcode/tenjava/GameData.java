package net.lordsofcode.tenjava;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GameData {

	private List<String> players;
	private int dead;
	private Loric plugin;

	public GameData(Loric plugin) {
		this.plugin = plugin;
	}

	public boolean addPlayer(Player player) {
		if (players.size() < 9) {
			players.add(player.getName());
			Bukkit.broadcastMessage(player.getName()
					+ " has joined the loric fight!");
			player.teleport(Bukkit.getWorld("loric").getSpawnLocation());
			return true;
		}
		return false;
	}

	public boolean isSafe(Player player) {
		if (isInGame(player)) {
			return players.indexOf(player.getName()) > dead;
		}
		return false;
	}

	public boolean isInGame(Player player) {
		return players.contains(player.getName())
				&& player.getWorld().getName().equals("loric");
	}

	public int getNumber(Player player) {
		if (isInGame(player)) {
			return players.indexOf(player.getName()) + 1;
		}
		return 0;
	}

	public void onDeath(PlayerDeathEvent event) {
		if (isInGame(event.getEntity())) {
			event.setDeathMessage("The " + getNumber(event.getEntity())
					+ "th loric has died...");
			this.dead++;
			if (this.dead >= 9) {
				this.dead = 0;
				this.players = new ArrayList<String>();
				Bukkit.broadcastMessage("The fight has ended. No more loric remain.");
			}
		}
	}

}
