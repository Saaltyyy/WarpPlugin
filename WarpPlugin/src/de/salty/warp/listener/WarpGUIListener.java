package de.salty.warp.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import de.salty.warp.utils.WarpFunktions;
import de.salty.warp.utils.WarpGUI;

public class WarpGUIListener implements Listener {

	int invIndex;
	Inventory[] inventories;

	@EventHandler
	private void onClick(PlayerInteractEvent event) {
		if (event.getItem() == null || event.getItem().getType() != Material.COMPASS)
			return;
		if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			inventories = WarpGUI.createInvetory(event.getPlayer());
			event.getPlayer().openInventory(inventories[0]);
			invIndex = 0;
		}
	}

	@EventHandler
	private void onInventoryInteract(InventoryClickEvent event) {
		if (!(event.getWhoClicked() instanceof Player))
			return;

		Player player = (Player) event.getWhoClicked();

		if (event.getCurrentItem() == null || event.getCurrentItem().getType().equals(Material.AIR))
			return;

		if (event.getClickedInventory().getTitle().equals(inventories[invIndex].getName())) {
			event.setCancelled(true);

			if (event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) {
				if (event.getCurrentItem().getItemMeta().getDisplayName().equals("BACK")) {
					if (invIndex > 0) {
						invIndex--;
						player.openInventory(inventories[invIndex]);
					}

				} else if (event.getCurrentItem().getItemMeta().getDisplayName().equals("NEXT")) {
					if (invIndex < inventories.length - 1) {
						invIndex++;
						player.openInventory(inventories[invIndex]);
					}
				}

			} else {
				WarpFunktions.warp(player, event.getCurrentItem().getItemMeta().getDisplayName());
			}

		}

	}

}
