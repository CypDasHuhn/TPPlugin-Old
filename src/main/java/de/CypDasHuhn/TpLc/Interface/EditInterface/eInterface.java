package de.CypDasHuhn.TpLc.Interface.EditInterface;

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

import java.util.ArrayList;
import java.util.List;

public class eInterface {
	public static void Interface(Player p, String Name, String Type) {
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		config.set("WhichInv","EditInterface");
		CustomFiles.save();
		if (Name == null) Name = "Leeren Slot";
		Inventory inv = Bukkit.createInventory(null, 27, "�6�l"+Name+" bearbeiten");
		for (int i = 0; i <= 26; i++) {
			inv.setItem(i, createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE," ",false,null));
		}inv.setItem(18, createItem(Material.FEATHER,"�fZur�ck",false,null));
		List<String> Lore1 = new ArrayList<String>();
		List<String> Lore2 = new ArrayList<String>();
		Lore1.add("�eErstell eine �6neue Location");
		Lore2.add("�eErstell einen �6neuen Folder");
		switch (Type) {
		case "Location":
			inv.setItem(10, createItem(Material.WRITABLE_BOOK,"�6Koordinaten Aktuallisieren",false,null));
			inv.setItem(12, createItem(Material.NAME_TAG,"�aLocation Umbenennen",false,null));
			inv.setItem(14, createItem(Material.TNT_MINECART,"�cLocation L�schen",false,null));
			inv.setItem(16, createItem(Material.ITEM_FRAME,"�5Location Item �ndern",false,null));
			break;
		case "Folder":
			inv.setItem(10, createItem(Material.NAME_TAG,"�aFolder Umbenennen",false,null));
			inv.setItem(12, createItem(Material.TNT_MINECART,"�cFolder L�schen",false,null));
			inv.setItem(14, createItem(Material.TNT,"�cFolder zsm. mit Inhalt L�schen",false,null));
			inv.setItem(16, createItem(Material.ITEM_FRAME,"�5Folder Item �ndern",false,null));
			break;
		case "Empty":
			inv.setItem(11, createItem(Material.GRASS_BLOCK,"�aLocation Erstellen",false,Lore1));
			inv.setItem(15, createItem(Material.BOOK,"�5Folder Erstellen",true,Lore2));
			break;
			
			default:
				break;
		}p.openInventory(inv);
	}
	public static ItemStack createItem(Material m, String Name, boolean Glitzer, List<String> Lore) {
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
