package de.salty.warp.listener;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		File playerWarpFolder = new File("plugins/warps/" + player.getName().toLowerCase());
		if (!playerWarpFolder.exists()) {
			playerWarpFolder.mkdir();
		}
	}

}
