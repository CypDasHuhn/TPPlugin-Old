package de.CypDasHuhn.TpLc.commands;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Teleport {
	public static void teleport(Player p, String Name) {
		UUID id = p.getUniqueId();
		FileConfiguration Location = CustomFiles.gfc(Name, id+"/Locations");
		if (Location.getString("Parent") != null) {
			org.bukkit.World W = (org.bukkit.World) Bukkit.getWorld(Location.getString("Coords.World"));
			double X =  Location.getDouble("Coords.X");
			double Y =  Location.getDouble("Coords.Y");
			double Z =  Location.getDouble("Coords.Z");
			float pitch = (float) Location.getDouble("Coords.Pitch");
			float yaw = (float) Location.getDouble("Coords.Yaw");
			Location l = new Location(W, X, Y, Z, yaw, pitch);
			p.teleport(l);
		}else p.sendMessage("�cDie �6Location '"+Name+"' �cgibt es nicht.");
	}
}
