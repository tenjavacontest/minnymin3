package net.lordsofcode.tenjava;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GameData {

	public List<String> players;
	public int dead;
	private Loric plugin;

	public GameData(Loric plugin) {
		this.plugin = plugin;
		players = new ArrayList<String>();
		dead = 0;
	}

	public boolean addPlayer(Player player) {
		if (players.size() < 9) {
			players.add(player.getName());
			Bukkit.broadcastMessage(ChatColor.AQUA + player.getName()
					+ " has joined the loric fight!");
			player.teleport(Bukkit.getWorld("loric").getSpawnLocation());
			player.sendMessage(ChatColor.AQUA + "You are number "
					+ getNumber(player));
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
			event.setDeathMessage("");
			Bukkit.broadcastMessage(ChatColor.RED + "The "
					+ getNumberName(getNumber(event.getEntity()))
					+ " loric has died...");
			int index = this.players.indexOf(event.getEntity().getName());
			this.players.set(index, "");
			this.dead++;
			if (this.dead >= 9 || isEmpty()) {
				this.dead = 0;
				this.players = new ArrayList<String>();
				Bukkit.broadcastMessage(ChatColor.AQUA
						+ "The fight has ended. No more loric remain.");
			}
		}
	}
	
	public boolean isEmpty() {
		for (String s : players) {
			if (!s.equals("")) {
				return false;
			}
		}
		return true;
	}

	public String getNumberName(int id) {
		switch (id) {
		case 1:
			return "First";
		case 2:
			return "Second";
		case 3:
			return "Third";
		case 4:
			return "Forth";
		case 5:
			return "Fifth";
		case 6:
			return "Sixth";
		case 7:
			return "Seventh";
		case 8:
			return "Eighth";
		case 9:
			return "Ninth";
		default:
			return "";
		}
	}

}
