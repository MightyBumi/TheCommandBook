package pro.homiecraft.Commands;

import java.io.File;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pro.homiecraft.TheCommandBook;
import pro.homiecraft.Config.warpConfig;

public class tcbSetwarp implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender.hasPermission("tcb.Setwarp") && (sender instanceof Player)) {
			if (cmd.getName().equalsIgnoreCase("setwarp")) {
				if (args.length < 1){
					sender.sendMessage("Missing warp name!");
					sender.sendMessage("usage: /setwarp warpName");
					return true;
				}else if(args.length > 1){
					sender.sendMessage("To many arguments!");
					sender.sendMessage("usage: /setwarp warpName");
					return true;
				}else{
					Player player = (Player) sender;
					
					String cworld = player.getWorld().getName();
					
					double x = player.getLocation().getX();
					double y = player.getLocation().getY();
					double z = player.getLocation().getZ();
					float yaw = player.getLocation().getYaw();
					float pitch = player.getLocation().getPitch();
					
					File warpFile = new File(TheCommandBook.pluginST.getDataFolder() + "/data/warps/" + args[0] + ".yml");
					if (!warpFile.exists()){
						warpConfig.reloadWarpConfig(args[0]);
						//warpConfig.getWarpConfig(args[0]).set(args[0] + "." + "world", cworld);
						warpConfig.getWarpConfig(args[0]).set(args[0] + "." + cworld + ".X", x);
						warpConfig.getWarpConfig(args[0]).set(args[0] + "." + cworld + ".Y", y);
						warpConfig.getWarpConfig(args[0]).set(args[0] + "." + cworld + ".Z", z);
						warpConfig.getWarpConfig(args[0]).set(args[0] + "." + cworld + ".yaw", yaw);
						warpConfig.getWarpConfig(args[0]).set(args[0] + "." + cworld + ".pitch", pitch);
						warpConfig.saveWarpConfig(args[0]);
						warpConfig.reloadWarpConfig(args[0]);
						
						player.sendMessage("Warp: " + args[0] + " is now set!");
						return true;
					}else{
						player.sendMessage("Warp " + args[0] + " already exists!");
						return true;
					}
				}
			}
		}
		return false;
	}
}
