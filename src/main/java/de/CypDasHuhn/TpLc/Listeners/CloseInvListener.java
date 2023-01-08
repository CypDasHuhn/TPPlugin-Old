package de.CypDasHuhn.TpLc.Listeners;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.Interface.FolderInterface.fInterface;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class CloseInvListener implements Listener{
	public static boolean exception = false;
	@EventHandler
	public static void closeInvListener(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		if (exception == false) {
			config.set("WhichInv", "None");
			fInterface.Move.put(p.getUniqueId()+"","false");
		}
		CustomFiles.save();
	}public static void manuellListener(Player p) {
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		config.set("WhichInv", "None");
		fInterface.Move.put(p.getUniqueId()+"","false");
		CustomFiles.save();
	}
}
