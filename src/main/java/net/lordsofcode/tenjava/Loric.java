package net.lordsofcode.tenjava;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public class Loric extends JavaPlugin {

	public List<String> players;
	
	public void onEnable() {
		players = new ArrayList<String>();
		getCommand("loric").setExecutor(new LoricCommand(this));
		if (Bukkit.getWorld("loric") == null) {
			Bukkit.createWorld(new WorldCreator("loric"));
		}
	}
	
}
