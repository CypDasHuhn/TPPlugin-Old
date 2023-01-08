package de.CypDasHuhn.TpLc.commands;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.FileManagment.ParentUpdater;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class TeleportDelete {
	public static void deleteTeleport(Player p, String Name) {
		FileConfiguration lConfig = CustomFiles.gfc(Name, p.getUniqueId()+"/Locations");
		if (lConfig.getString("Parent.Name") == null || lConfig.getString("Parent.Name").equals("EMPTY")) {
			p.sendMessage("�cDie Location �6'"+Name+"' �c existiert nicht.");
			return;
		}ParentUpdater.updateParent(p, "EMPTY", lConfig.getString("Parent.Name"), lConfig.getInt("Parent.Slot"), "EMPTY");
		CustomFiles.delete(Name, p.getUniqueId()+"/Locations");
		//TabComplete-ID's////////////
		FileConfiguration lconfig = CustomFiles.gfc("List", p.getUniqueId()+"");
		int IdAmmount = lconfig.getInt("Location.Id.Ammount");
		int Id = 0;
		for (int i = 0; i <= (IdAmmount-1); i++) {
			if (lconfig.getString("Location.Id."+i).equalsIgnoreCase(Name)) Id = i;
		}for (int i = Id; i <= IdAmmount; i++) {
			lconfig.set("Location.Id."+i, lconfig.getString("Location.Id."+(i+1)));
		}lconfig.set("Location.Id.Ammount", IdAmmount-1);
		CustomFiles.save();
		////////////////////////////////////
		p.sendMessage("�aDie Location �6'"+Name+"' �awurde gel�scht.");
	}
}
