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
						sender.sendMessage("You are already on earth. You are number "
								+ plugin.game.getNumber(player));
					} else if (plugin.game.addPlayer(player)) {
						sender.sendMessage("Going to the planet Earth...");
					} else {
						sender.sendMessage("You can't join the battle...");
					}
				} else if (args[0].equalsIgnoreCase("regen")) {
					if (player.hasPermission("loric.regen")) {
						World world = Bukkit.getWorld("loric");
						for (Player p : world.getPlayers()) {
							p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
						}
						Bukkit.unloadWorld(world, false);
						try {
							FileUtils.deleteDirectory(world.getWorldFolder());
						} catch (IOException e) {
							e.printStackTrace();
						}
						Bukkit.createWorld(new WorldCreator("loric"));
					} else {
						player.sendMessage(ChatColor.RED + "You don't have permission!");
					}
				}
			}
		} else {
			sender.sendMessage("In game command only");
		}
		return true;
	}
	
	public void sendUsage(CommandSender sender) {
		
	}

}
