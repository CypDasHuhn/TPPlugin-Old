package de.CypDasHuhn.TpLc.FileManagment;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class CustomFiles{
	private static File file;
	private static FileConfiguration customFile;
	
	
	                                              //GetFileConfiguration
	public static FileConfiguration gfc(String Name, String Folder) {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("TpLcRemastered").getDataFolder()+"/"+Folder, Name+".yml");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		customFile = YamlConfiguration.loadConfiguration(file);
		return customFile;
	}
	
	public static void save(){ 
		try {
			customFile.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void reload() {
		customFile = YamlConfiguration.loadConfiguration(file);
	}
	
	public static void delete(String Name, String Folder) {
		file = new File(Bukkit.getServer().getPluginManager().getPlugin("TpLcRemastered").getDataFolder()+"/"+Folder, Name+".yml");
		if (file.exists()) file.delete();
	}
}