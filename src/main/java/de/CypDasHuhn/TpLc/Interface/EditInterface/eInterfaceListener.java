package de.CypDasHuhn.TpLc.Interface.EditInterface;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.Interface.ConfirmingInterface.confirmingInterface;
import de.CypDasHuhn.TpLc.Interface.FolderInterface.fInterface;
import de.CypDasHuhn.TpLc.Interface.SelectItemInterface.SelectItemInterface;
import de.CypDasHuhn.TpLc.Listeners.CloseInvListener;
import de.CypDasHuhn.TpLc.commands.Update;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.List;

public class eInterfaceListener implements Listener{
	@EventHandler
	public void einterfaceListener(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		if (config.getString("WhichInv").equals("EditInterface")) {
			e.setCancelled(true);
			Material m = e.getCurrentItem().getType();
			String Parent = config.getString("Parent");
			int Page = config.getInt("Page");
			FileConfiguration pConfig = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");
			String Type = pConfig.getString("Child."+config.getString("Slot")+".Type");
			List<String> Lore1 = new ArrayList<String>();
			List<String> Lore2 = new ArrayList<String>();
			Lore1.add("�eErstell eine �6neue Location");
			Lore2.add("�eErstell einen �6neuen Folder");
			config = CustomFiles.gfc("Data", p.getUniqueId()+"");
			config.set("Slot", e.getRawSlot());
		
			switch (m) {
			case FEATHER:
				CloseInvListener.exception = true;
				fInterface.finterface(p, Parent, Page);
				break;
			case NAME_TAG:
				p.sendMessage("�cNoch nicht fertig, bald.");
				break;
			case ITEM_FRAME:
				CloseInvListener.exception = true;
				SelectItemInterface.selectItemInterface(p);
				break;
			case WRITABLE_BOOK:
				Update.update(p);
				break;
			case GRASS_BLOCK:
				if (!e.getCurrentItem().getItemMeta().getLore().equals(Lore1)) return;
				p.closeInventory();
				config = CustomFiles.gfc("Data", p.getUniqueId()+"");
				p.sendMessage("�5Schreib den �6Namen �5der neuen Location in den �6chat.");
				p.sendMessage("�5(Dies ist �6nur �5f�r �6dich �5sichtbar.)");
				config.set("Chat", "Location");
				CustomFiles.save();
				break;
			case BOOK:
				if (!e.getCurrentItem().getItemMeta().getLore().equals(Lore2)) return;
				p.closeInventory();
				config = CustomFiles.gfc("Data", p.getUniqueId()+"");
				p.sendMessage("�5Schreib den �6Namen �5des neuen Folders in den �6chat.");
				p.sendMessage("�5(Dies ist �6nur �5f�r �6dich �5sichtbar.)");
				config.set("Chat", "Folder");
				CustomFiles.save();
				break;
			case TNT_MINECART:
				config = CustomFiles.gfc("Data", p.getUniqueId()+"");
				if (Type.equals("Folder")) config.set("ConfirmingType","Delete2A");
				else if (Type.equals("Location")) config.set("ConfirmingType","Delete2C");
				CustomFiles.save();
				CloseInvListener.exception = true;
				confirmingInterface.cInterface(p);
				break;
			case TNT:
				config = CustomFiles.gfc("Data", p.getUniqueId()+"");
				config.set("ConfirmingType","Delete2B");
				CustomFiles.save();
				CloseInvListener.exception = true;
				confirmingInterface.cInterface(p);
				break;
				
				default:
					break;
			}
		}
		CloseInvListener.exception = false;
	}
}
