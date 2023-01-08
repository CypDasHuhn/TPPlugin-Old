package de.CypDasHuhn.TpLc.commands;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Update {
	public static void update(Player p) {
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		int Slot = config.getInt("Slot");
		String Parent = config.getString("Parent");
		FileConfiguration pConfig = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");
		String Name = pConfig.getString("Child."+Slot+".Name");
		
		FileConfiguration tConfig = CustomFiles.gfc(Name, p.getUniqueId()+"/Locations");
		Location l = p.getLocation();
		tConfig.set("Parent", "General");
		tConfig.set("Coords.X", l.getBlockX());
		tConfig.set("Coords.Y", l.getBlockY());
		tConfig.set("Coords.Z", l.getBlockZ());
		tConfig.set("Coords.Yaw", l.getYaw());
		tConfig.set("Coords.Pitch", l.getPitch());
		tConfig.set("Coords.World", l.getWorld().getName());
		CustomFiles.save();
		p.sendMessage("�aKoordinaten wurden aktualisiert.");
	}
}
