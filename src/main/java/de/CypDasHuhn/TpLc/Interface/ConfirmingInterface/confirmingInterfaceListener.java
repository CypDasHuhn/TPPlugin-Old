package de.CypDasHuhn.TpLc.Interface.ConfirmingInterface;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.Interface.EditInterface.eInterface;
import de.CypDasHuhn.TpLc.Interface.FolderInterface.fInterface;
import de.CypDasHuhn.TpLc.Listeners.CloseInvListener;
import de.CypDasHuhn.TpLc.commands.FolderDelete;
import de.CypDasHuhn.TpLc.commands.TeleportDelete;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class confirmingInterfaceListener implements Listener{
	@EventHandler
	public static void cInterfaceListener(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		if (config.getString("WhichInv").equals("ConfirmingInterface")) {
			e.setCancelled(true);
			
			FileConfiguration pConfig = CustomFiles.gfc(config.getString("Parent"), p.getUniqueId()+"/Folders");
			String Name = pConfig.getString("Child."+config.getString("Slot")+".Name");
			String Type = pConfig.getString("Child."+config.getString("Slot")+".Type");
			
			if (e.getCurrentItem().getType().equals(Material.LIME_CONCRETE)) {
				String Action = config.getString("ConfirmingType");
				config = CustomFiles.gfc("Data", p.getUniqueId()+"");
				switch (Action) {
				case "DeleteA":
					FolderDelete.folderDelete(p,Name);
					CloseInvListener.exception = true;
					fInterface.finterface(p, config.getString("Parent"), config.getInt("Page"));
					break;				
				case "Delete1A":
					config.set("ConfirmingType", "DeleteA");
					usuall(p);
					break;				
				case "Delete2A":
					config.set("ConfirmingType", "Delete1A");
					usuall(p);
					break;	
				case "DeleteB":
					FolderDelete.allDelete(p,Name);
					CloseInvListener.exception = true;
					fInterface.finterface(p, config.getString("Parent"), config.getInt("Page"));
					break;				
				case "Delete1B":
					config.set("ConfirmingType", "DeleteB");
					usuall(p);
					break;				
				case "Delete2B":
					config.set("ConfirmingType", "Delete1B");
					usuall(p);
					break;	
				case "DeleteC":
					TeleportDelete.deleteTeleport(p, Name);
					CloseInvListener.exception = true;
					fInterface.finterface(p, config.getString("Parent"), config.getInt("Page"));
					break;				
				case "Delete1C":
					config.set("ConfirmingType", "DeleteC");
					usuall(p);
					break;				
				case "Delete2C":
					config.set("ConfirmingType", "Delete1C");
					usuall(p);
					break;	
				case "Update":
					
					break;					
				case "Name":
					
					break;
					
					default:
						
						break;
				}
			}else if (e.getCurrentItem().getType().equals(Material.RED_CONCRETE)){
				eInterface.Interface(p, Name,Type);
			}
		}
		CloseInvListener.exception = false;
	}public static void usuall(Player p) {
		CustomFiles.save();
		CloseInvListener.exception = true;
		confirmingInterface.cInterface(p);
	}
}
