package de.CypDasHuhn.TpLc.Interface.SelectItemInterface;

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

public class SelectItemInterface {
	public static void selectItemInterface(Player p) {
		
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		config.set("WhichInv","ItemInterface");
		CustomFiles.save();
		String Parent = config.getString("Parent");
		int Slot = config.getInt("Slot");
		FileConfiguration pConfig = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");
		String Name = pConfig.getString("Child."+Slot+".Name");
		
		Inventory inv = Bukkit.createInventory(null, 36, "�6�l"+Name+"'s Item �ndern");
		for (int i = 0; i < 36; i++) {
			if (i != 13) inv.setItem(i, createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE," ",false,null));
		}for (int i = 3; i < 6; i++) {
			inv.setItem(i, createItem(Material.GRAY_STAINED_GLASS_PANE," ",false,null));
		}for (int i = 12; i < 15; i++) {
			if (i != 13) inv.setItem(i, createItem(Material.GRAY_STAINED_GLASS_PANE," ",false,null));
		}for (int i = 21; i < 24; i++) {
			inv.setItem(i, createItem(Material.GRAY_STAINED_GLASS_PANE," ",false,null));
		}for (int i = 24; i < 27; i++) {
			inv.setItem(i, createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE," ",false,null));
		}for (int i = 27; i < 36; i++) {
			inv.setItem(i, createItem(Material.BLACK_STAINED_GLASS_PANE," ",false,null));
		}inv.setItem(27, createItem(Material.FEATHER,"�fZur�ck",false,null));
		inv.setItem(34, createItem(Material.EMERALD_BLOCK,"�aFertig",false,null));
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
