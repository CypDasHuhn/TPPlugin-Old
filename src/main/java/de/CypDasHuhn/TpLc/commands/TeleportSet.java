package de.CypDasHuhn.TpLc.commands;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.FileManagment.ParentUpdater;
import de.CypDasHuhn.TpLc.Interface.FolderInterface.fInterface;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TeleportSet {
	public static void registerLocation(Player p, String Name, int ID, String Parent) {
		UUID uuid = p.getUniqueId();
		if (ID == -1) ID = getNewID(p,Parent);
		FileConfiguration Location = CustomFiles.gfc(Name, uuid+"/Locations");
		if (Location.getString("Parent.Name") == null) {
			Location l = p.getLocation();
			Location.set("Parent", "General");
			Location.set("Coords.X", l.getBlockX());
			Location.set("Coords.Y", l.getBlockY());
			Location.set("Coords.Z", l.getBlockZ());
			Location.set("Coords.Yaw", l.getYaw());
			Location.set("Coords.Pitch", l.getPitch());
			Location.set("Coords.World", l.getWorld().getName());
			Location.set("Parent.Name", Parent);
			Location.set("Parent.Slot", ID);
			Location.set("Item", fInterface.createItem(Material.GRASS_BLOCK, "�f"+Name,false,null));
			CustomFiles.save();
			FileConfiguration lConfig = CustomFiles.gfc("List", p.getUniqueId()+"");
			int id = lConfig.getInt("Location.Id.Ammount");
			lConfig.set("Location.Id.Ammount", id+1);
			lConfig.set("Location.Id."+id, Name);
			CustomFiles.save();
			ParentUpdater.updateParent(p, Name, Parent, ID, "Location");
			p.sendMessage("�aDie �6Location '"+Name+"' �awurde gespeichert.");
		}else p.sendMessage("�cDer �6Name '"+Name+"' �cist bereits �6besetzt.");
	}public static int getNewID(Player p, String Parent) {
		FileConfiguration config = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");		
		int a = 0;
		boolean gotID = false;
		while (gotID == false) {
			if (config.getString("Child."+a+".Name") == null || config.getString("Child."+a+".Name").equals("EMPTY")) {
				gotID = true;
			}else a++;
		}
		return a;
	}
}
