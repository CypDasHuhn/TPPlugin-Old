package de.CypDasHuhn.TpLc.commands;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.Interface.FolderInterface.fInterface;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {
		Player Unsure = (Player) sender;
		if (Unsure instanceof Player) {
			Player p = Unsure;
			switch (label) {
			case "ts":
				if (args.length != 1) {
					p.sendMessage("�cFalscher Syntax. Bitte schreib �6/ts [Name]�c.");	
					return false;
				}
				if (args[0].equals("EMPTY")) p.sendMessage("�cDieser Name darf wegen technischen Limitationen nicht genutzt werden. Versuch einen anderen.");			
				else TeleportSet.registerLocation(p, args[0], -1, "General");
				break; //core
			case "t":
				FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
				String Parent = "General";
				int Page = 1;
				if (config.getString("Parent") != null) {
					Parent = config.getString("Parent");
					Page = config.getInt("Page");
				}if (args.length == 0) fInterface.finterface(p, Parent, Page);
				else if (args.length > 1) p.sendMessage("�cFalscher Syntax. Bitte schreib �6/t [Name]�c.");		
				else Teleport.teleport(p, args[0]);
				break;
			case "td":
				TeleportDelete.deleteTeleport(p, args[0]);
				break;
			}
		}
		return false;
	}

}
