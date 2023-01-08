package de.CypDasHuhn.TpLc.Interface.ConfirmingInterface;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class confirmingInterface {
	public static void cInterface(Player p) {
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		config.set("WhichInv", "ConfirmingInterface");
		CustomFiles.save();
		
		Inventory inv = Bukkit.createInventory(null, 54, "�6�lBest�tige deine Aktivit�t");
		
		ItemStack item = createItem(Material.RED_CONCRETE,"�cAbbrechen",false,null);
		for (int i = 0; i < 54; i++) {
			inv.setItem(i, item);
		}
		
		int min = 0;
		int max = 53;
		int RandomInt = (int)(Math.random()*(max-min+1)+min);
		
		inv.setItem(RandomInt, createItem(Material.LIME_CONCRETE,"�aBest�tigen",false,null));
		
		p.openInventory(inv);
	}public static ItemStack createItem(Material m, String Name, boolean Glitzer, List<String> Lore) {
		ItemStack item = new ItemStack(m);
		ItemMeta itemMeta = item.getItemMeta();
		itemMeta.setDisplayName(Name);
		if (Glitzer == true) {
			itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 0,true);
			itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}if (Lore != null) itemMeta.setLore(Lore);
		item.setItemMeta(itemMeta);
		return item;
	}
}
