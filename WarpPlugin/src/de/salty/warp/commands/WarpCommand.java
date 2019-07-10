package de.salty.warp.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.salty.warp.utils.MsgTemplates;
import de.salty.warp.utils.WarpFunktions;

public class WarpCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 1) {
				if (WarpFunktions.doesWarpExist(player, args[0])) {
					WarpFunktions.warp(player, args[0]);
				} else
					player.sendMessage(MsgTemplates.getPrefix() + "§4Der Warp§3 " + args[0] + "§4 existiert nicht!");

			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("create")) {
					if (!WarpFunktions.doesWarpExist(player, args[1])) {
						WarpFunktions.createWarp(player, args[1]);
					} else
						player.sendMessage(
								MsgTemplates.getPrefix() + "§4Der Warp§3 " + args[1] + "§4 existiert schon!");

				} else if (args[0].equalsIgnoreCase("delete")) {
					if (WarpFunktions.doesWarpExist(player, args[1])) {
						WarpFunktions.deleteWarp(player, args[1]);
					} else
						player.sendMessage(
								MsgTemplates.getPrefix() + "§4Der Warp§3 " + args[1] + "§4 existiert nicht!");

				} else if (args[0].equalsIgnoreCase("info")) {
					if (WarpFunktions.doesWarpExist(player, args[1])) {
						WarpFunktions.warpInfo(player, args[1]);
					} else
						player.sendMessage(
								MsgTemplates.getPrefix() + "§4Der Warp§3 " + args[1] + "§4 existiert nicht!");

				} else
					player.sendMessage(MsgTemplates.getPrefix()
							+ "§7Bitte benutze §3/warp create|delete|info <NAME> §7oder §3/warp <NAME>§7!");

			} else
				player.sendMessage(MsgTemplates.getPrefix()
						+ "§7Bitte benutze §3/warp create|delete|info <NAME> §7oder §3/warp <NAME>§7!");

		}

		return false;
	}

}
