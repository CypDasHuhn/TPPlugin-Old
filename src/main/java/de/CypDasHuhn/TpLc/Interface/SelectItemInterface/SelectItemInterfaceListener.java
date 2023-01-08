package de.CypDasHuhn.TpLc.Interface.SelectItemInterface;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.Interface.EditInterface.eInterface;
import de.CypDasHuhn.TpLc.Listeners.CloseInvListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SelectItemInterfaceListener implements Listener{
	@EventHandler
	public static void selectItemInterfaceListener(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		if (config.getString("WhichInv").equals("ItemInterface")) {
			int slot = e.getRawSlot();
			int prevSlot = config.getInt("Slot");
			FileConfiguration pConfig = CustomFiles.gfc(config.getString("Parent"), p.getUniqueId()+"/Folders");
			String Name = pConfig.getString("Child."+prevSlot+".Name");
			String Type = pConfig.getString("Child."+prevSlot+".Type");
			
			switch (slot) {
			case 13:
				
				break;
			case 27:
				e.setCancelled(true);
				CloseInvListener.exception = true;
				eInterface.Interface(p, Name, Type);
				break;
			case 34:
				e.setCancelled(true);
				if (e.getInventory().getItem(13) == null) {
					p.sendMessage("�cBitte tu ein item in");
					p.sendMessage("�cdas markierte Feld");
				}
				else setItem(p, Name, Type, e.getInventory());
				break;
				
				default:
					if (slot<36) e.setCancelled(true);
					break;
			}
		}
		CloseInvListener.exception = false;
	}public static void setItem(Player p, String Name, String Type, Inventory inv) {
		FileConfiguration lConfig = CustomFiles.gfc(Name, p.getUniqueId()+"/"+Type+"s");
		
		ItemStack falseItem = lConfig.getItemStack("Item");
		ItemMeta itemMeta = falseItem.getItemMeta();
		
		ItemStack Item = inv.getItem(13);
		Item.setItemMeta(itemMeta);
		
		lConfig.set("Item",Item);
		CustomFiles.save();
		
		CloseInvListener.exception = true;
		eInterface.Interface(p, Name, Type);
		p.sendMessage("�aDas Item wurde gew�chselt.");
	}
}
