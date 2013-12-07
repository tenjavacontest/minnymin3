package net.lordsofcode.tenjava;

import java.io.IOException;

import net.minecraft.util.org.apache.commons.io.FileUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoricCommand implements CommandExecutor {

	private Loric plugin;

	public LoricCommand(Loric plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 0) {
				sendUsage(sender);
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("join")) {
					if (plugin.game.isInGame(player)) {
						sender.sendMessage(ChatColor.RED
								+ "You are already on earth. You are number "
								+ plugin.game.getNumber(player));
					} else if (plugin.game.addPlayer(player)) {
						sender.sendMessage(ChatColor.GRAY
								+ "Going to the planet Earth...");
					} else {
						sender.sendMessage(ChatColor.RED
								+ "You can't join the battle...");
					}
				} else if (args[0].equalsIgnoreCase("regen")) {
					if (player.hasPermission("loric.regen")) {
						regenWorld();
					} else {
						player.sendMessage(ChatColor.RED
								+ "You don't have permission!");
					}
				}
			}
		} else {
			if (args.length == 0) {
				sendUsage(sender);
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("regen")) {
					regenWorld();
				}
			}
		}
		return true;
	}

	public void sendUsage(CommandSender sender) {

	}
	
	public void regenWorld() {
		Bukkit.broadcastMessage(ChatColor.GRAY
				+ "[LORIC] World being regenerated. Expect lag for a bit...");
		World world = Bukkit.getWorld("loric");
		for (Player p : world.getPlayers()) {
			p.teleport(Bukkit.getWorlds().get(0)
					.getSpawnLocation());
		}
		Bukkit.unloadWorld(world, false);
		try {
			FileUtils.deleteDirectory(world.getWorldFolder());
		} catch (IOException e) {
			e.printStackTrace();
		}
		World newWorld = Bukkit.createWorld(new WorldCreator(
				"loric"));
		newWorld.setMonsterSpawnLimit(1000);
		newWorld.setTicksPerMonsterSpawns(10);
		newWorld.setPVP(false);
		Bukkit.broadcastMessage(ChatColor.GREEN + "[LORIC] Regen done!");
	}

}
