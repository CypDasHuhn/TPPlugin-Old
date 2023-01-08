package de.CypDasHuhn.TpLc.FileManagment;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class RegisterFolder {
	public static void registerFolder(Player p, String Name, int ID, String Parent) {
		FileConfiguration pConfig = CustomFiles.gfc(Name, p.getUniqueId()+"/Folders");
		pConfig.set("Parent.Name", Parent);
		pConfig.set("Parent.Slot", ID);
		pConfig.set("Item", createItem(Material.BOOK, "�f"+Name,true,null));
		CustomFiles.save();
		ParentUpdater.updateParent(p, Name, Parent, ID, "Folder");
		FileConfiguration lConfig = CustomFiles.gfc("List", p.getUniqueId()+"");
		int id = lConfig.getInt("Folder.Id.Ammount");
		lConfig.set("Folder.Id.Ammount", id+1);
		lConfig.set("Folder.Id."+id, Name);
		CustomFiles.save();
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
