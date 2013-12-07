package net.lordsofcode.tenjava;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.java.JavaPlugin;

public class Loric extends JavaPlugin {

	public GameData game;
	
	public void onEnable() {
		game = new GameData(this);
		getServer().getPluginManager().registerEvents(new LoricListener(this), this);
		getCommand("loric").setExecutor(new LoricCommand(this));
		if (Bukkit.getWorld("loric") == null) {
			World world = Bukkit.createWorld(new WorldCreator("loric"));
			world.setMonsterSpawnLimit(10000);
			world.setTicksPerMonsterSpawns(1);
			world.setPVP(false);
		}
	}
	
}
