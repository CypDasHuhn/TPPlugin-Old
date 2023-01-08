package de.CypDasHuhn.TpLc.FileManagment;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class ParentUpdater {
	public static void updateParent(Player p, String Child, String Parent, int ID, String Type) {
		FileConfiguration config = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");
		config.set("Child."+ID+".Name", Child);
		config.set("Child."+ID+".Type", Type);
		CustomFiles.save();
	}public static void updateChild(Player p, String Child, String Type, String Parent, int ID) {
		FileConfiguration config = CustomFiles.gfc(Child, p.getUniqueId()+"/"+Type+"s");
		config.set("Parent.Name", Parent);
		config.set("Parent.Slot", ID);
		CustomFiles.save();
	}
}
