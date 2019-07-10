package de.salty.warp.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WarpFunktions {

	public static void warp(Player player, String name) {
		YamlConfiguration warp = getWarpConfig(player, name);
		Location location = new Location(Bukkit.getWorld(warp.getString("world")), warp.getDouble("x"),
				warp.getDouble("y"), warp.getDouble("z"), (float) warp.getDouble("yaw"),
				(float) warp.getDouble("pitch"));
		player.teleport(location);
		player.sendMessage(MsgTemplates.getPrefix() + "§7Du wurdest teleportiert!");
	}

	public static void createWarp(Player player, String name) {
		try {
			getWarpFile(player, name).createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

		YamlConfiguration warp = getWarpConfig(player, name);
		warp.options().copyDefaults(true);
		warp.addDefault("world", player.getLocation().getWorld().getName());
		warp.addDefault("x", player.getLocation().getX());
		warp.addDefault("y", player.getLocation().getY());
		warp.addDefault("z", player.getLocation().getZ());
		warp.addDefault("yaw", player.getLocation().getYaw());
		warp.addDefault("pitch", player.getLocation().getPitch());
		if (player.getItemInHand().getType().equals(Material.AIR)
				|| player.getItemInHand().getType().equals(Material.STAINED_GLASS_PANE))
			warp.addDefault("item", new ItemStack(Material.WOOL, 1, (short) 7));
		else
			warp.addDefault("item", player.getItemInHand());

		try {
			warp.save(getWarpFile(player, name));
		} catch (IOException e) {
			e.printStackTrace();
		}

		player.sendMessage(MsgTemplates.getPrefix() + "§7Der Warp§3 " + name + " §7wurde erfolgreich erstellt!");
	}

	public static void deleteWarp(Player player, String name) {
		getWarpFile(player, name).delete();
		player.sendMessage(MsgTemplates.getPrefix() + "§7Der Warp§3 " + name + " §7wurde erfolgreich gelöscht!");
	}

	public static void warpInfo(Player player, String name) {
		YamlConfiguration warp = getWarpConfig(player, name);
		player.sendMessage(MsgTemplates.getPrefix() + "§7§l" + name + ": §3[ X= " + (int) warp.getDouble("x") + " | Y= "
				+ (int) warp.getDouble("y") + " | Z= " + (int) warp.getDouble("z") + " ]§7!");

	}

	public static boolean doesWarpExist(Player player, String name) {
		return getWarpFile(player, name).exists();
	}

	public static YamlConfiguration getWarpConfig(Player player, String name) {
		return YamlConfiguration.loadConfiguration(getWarpFile(player, name));
	}

	private static File getWarpFile(Player player, String name) {
		return new File("plugins/warps/" + player.getName().toLowerCase(), name.toLowerCase() + ".yml");
	}

}
