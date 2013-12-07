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
			world.setMonsterSpawnLimit(1000);
			world.setTicksPerMonsterSpawns(10);
			world.setPVP(false);
		}
		Config conf = new Config("game.yml", this);
		conf.saveDefaultConfig();
		if (conf.getConfig().contains("players")) {
			game.players = conf.getConfig().getStringList("players");
			game.dead = conf.getConfig().getInt("dead");
		}
	}
	
	@Override
	public void onDisable() {
		Config conf = new Config("game.yml", this);
		conf.getConfig().set("players", game.players);
		conf.getConfig().set("dead", game.dead);
		conf.saveConfig();
	}
	
}
