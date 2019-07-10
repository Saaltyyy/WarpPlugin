package de.salty.warp.main;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.salty.warp.commands.WarpCommand;
import de.salty.warp.listener.PlayerJoinListener;
import de.salty.warp.listener.WarpGUIListener;

public class Main extends JavaPlugin {
	private static Main plugin;

	@Override
	public void onEnable() {
		plugin = this;

		File warpsFolder = new File("plugins/warps");

		if (!warpsFolder.exists()) {
			warpsFolder.mkdir();
		}

		System.out.println("Das Warp-Plugin wurde erfolgreich gestartet!");
		getCommand("warp").setExecutor(new WarpCommand());

		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new WarpGUIListener(), this);
		pluginManager.registerEvents(new PlayerJoinListener(), this);
	}

	public static Main getPlugin() {
		return plugin;
	}

}
