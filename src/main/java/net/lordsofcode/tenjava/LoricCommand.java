package net.lordsofcode.tenjava;

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
					if (player.getWorld().getName().equals("loric")) {
						sender.sendMessage("You are already on earth. You are number "
								+ plugin.players.indexOf(player.getName()));
					} else if (plugin.players.size() < 9) {
						join(player);
					} else {
						sender.sendMessage("You can't join the battle...");
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
	
	public void join(Player player) {
		
	}

}
