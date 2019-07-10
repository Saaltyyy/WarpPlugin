package de.salty.warp.utils;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class WarpGUI {

	static Inventory[] inventories;
	static File[] warps;

	public static Inventory[] createInvetory(Player player) {
		File directory = new File("plugins/warps/" + player.getName().toLowerCase());
		warps = directory.listFiles();

		int inventoryCount = (int) Math.ceil(warps.length / 7.0);

		if (inventoryCount == 0)
			inventoryCount = 1;

		inventories = new Inventory[inventoryCount];

		ItemStack back = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
		ItemMeta backMeta = back.getItemMeta();
		backMeta.setDisplayName("BACK");
		back.setItemMeta(backMeta);

		ItemStack next = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 7);
		ItemMeta nextMeta = back.getItemMeta();
		nextMeta.setDisplayName("NEXT");
		next.setItemMeta(nextMeta);

		for (int i = 0; i < inventories.length; i++) {
			inventories[i] = Bukkit.createInventory(null, 9 * 1, "Seite " + i + 1);
		}

		int fileCounter = 0;

		for (Inventory inventory : inventories) {
			inventory.setItem(0, back);

			for (int i = 1; i < 8; i++) {
				if (fileCounter > directory.listFiles().length - 1)
					break;
				inventory.setItem(i, createWarpItem(player, warps[fileCounter].getName()));
				fileCounter++;
			}

			inventory.setItem(8, next);
		}

		return inventories;
	}

	private static ItemStack createWarpItem(Player player, String name) {
		YamlConfiguration configuration = WarpFunktions.getWarpConfig(player, name.substring(0, name.length() - 4));
		ItemStack item = configuration.getItemStack("item");
		item.setAmount(1);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(name.substring(0, name.length() - 4).toUpperCase());
		item.setItemMeta(itemMeta);
		return item;
	}

}
