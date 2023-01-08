package de.CypDasHuhn.TpLc;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class tlTabCompleter implements TabCompleter{
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		List <String> arguments = new ArrayList<String>();
		Player p = (Player) sender;
		UUID u = p.getUniqueId();
		FileConfiguration lConfig = CustomFiles.gfc("List", u+"");
		int ammount = lConfig.getInt("Location.Id.Ammount");
		
		if (arguments.isEmpty()) {	
			switch (label) {
			case "t":
			case "td":
				if (ammount != 0) for (int i = 0; i <= (ammount-1); i++) {
					arguments.add(lConfig.getString("Location.Id."+i));
				}
				break;
			case "ts":
				arguments.add(" ");
				break;
				default:
					break;
			}
		}
		
		List <String> result = new ArrayList<String>();
		if (args.length == 1) {
			for (String a : arguments) {
				if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}return result;
		}
		return null;
	}
	
}
