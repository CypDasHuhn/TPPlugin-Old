package de.CypDasHuhn.TpLc.Interface.FolderInterface;

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
import java.util.HashMap;
import java.util.List;

public class fInterface {
	public static HashMap<String, String> Move = new HashMap<String, String>();
	public static void finterface(Player p, String Parent, int Page) {
		if (Move.get(p.getUniqueId()+"")==null) Move.put(p.getUniqueId()+"","false");
		FileConfiguration dConfig = CustomFiles.gfc("Data", p.getUniqueId()+"");
		dConfig.set("WhichInv", "Interface");
		dConfig.set("Page", Page);
		dConfig.set("Parent", Parent);
		CustomFiles.save();
		FileConfiguration pConfig = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");
		Inventory inv = Bukkit.createInventory(null, 54, "�6�l"+Parent+" #"+Page);
		int bonus = 45*(Page-1);
		ItemStack Item;
		if (Move.get(p.getUniqueId()+"").equals("false")) Item = createItem(Material.LIGHT_GRAY_STAINED_GLASS_PANE," ",false,getLore("Empty",p));
		else Item = createItem(Material.LIME_STAINED_GLASS_PANE," ",false,getLore("Empty",p));
		for (int i = 0; i < 45; i++) {
			inv.setItem(i, Item);
		}for (int i = 0; i < 45; i++) {
			if (!(pConfig.getString("Child."+(i+bonus)+".Name") == null || pConfig.getString("Child."+(i+bonus)+".Name").equals("EMPTY"))) {
				String Type = pConfig.getString("Child."+(i+bonus)+".Type");
				String LocationName = pConfig.getString("Child."+(i+bonus)+".Name");
				FileConfiguration lConfig = CustomFiles.gfc(LocationName, p.getUniqueId()+"/"+Type+"s");
				ItemStack item = lConfig.getItemStack("Item");
				ItemMeta itemMeta = item.getItemMeta();
				itemMeta.setLore(getLore(Type,p));
				if (Type.equals("Folder")) {
					itemMeta.addEnchant(Enchantment.DAMAGE_ALL, 0,true);
					itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				}item.setItemMeta(itemMeta);
				inv.setItem(i, item);
			}
		}
		for (int i = 45; i < 54; i++) {
			inv.setItem(i, createItem(Material.BLACK_STAINED_GLASS_PANE," ",false,null));
		}
		if (!Parent.equals("General")) inv.setItem(45, createItem(Material.FEATHER,"�fZur�ck",false,null)); 
		if (Page > 1) inv.setItem(48, createItem(Material.ARROW,"�fSeite zur�ck",false,null));
		inv.setItem(50, createItem(Material.ARROW,"�fN�chste Seite",false,null));
		p.openInventory(inv);
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
	}public static List<String> getLore(String Type, Player p){
		List<String> Lore = new ArrayList<String>();
		if (Move.get(p.getUniqueId()+"").equals("true")) {
			switch (Type) {
			case "Location":
				Lore.add("�5Klick drauf um");
				Lore.add("�5Positionen zu �6tauschen�5.");
				break;
			case "Folder":
				Lore.add("�5Klick drauf um");
				Lore.add("�5Positionen zu �6tauschen�5.");
				break;
			case "Empty":
				Lore.add("�5Klick drauf um hier die");
				Lore.add("�5Location/Ordner �6hinzustellen�5.");
				break;
				default:
					break;
			}
		}else {
			switch (Type) {
			case "Location":
				Lore.add("�6Linksklick �5um sich zu");
				Lore.add("�5der Position zu �6teleportieren�5.");
				Lore.add("�6Rechtsklick �5um die");
				Lore.add("�5Location zu �6verschieben�5.");
				Lore.add("�6Shiftklick �5um die �6Eigenschaften �5zu �ndern.");
				break;
			case "Folder":
				Lore.add("�6Linksklick �5zum");
				Lore.add("�6�ffnen �5des Ordners.");
				Lore.add("�6Rechtsklick �5um den");
				Lore.add("�5Ordner zu �6verschieben�5.");
				Lore.add("�6Shiftklick �5um die");
				Lore.add("�6Eigenschaften �5zu �ndern.");
				break;
			case "Empty":
				Lore.add("�6Shiftklick �5um die");
				Lore.add("�6Eigenschaften �5zu �ndern,");
				Lore.add("�5Hier kannst du einen �6Ordner");
				Lore.add("�5oder eine �6Location �5erstellen.");
				break;
				default:
					break;
			}
		}return Lore;
	}
}
