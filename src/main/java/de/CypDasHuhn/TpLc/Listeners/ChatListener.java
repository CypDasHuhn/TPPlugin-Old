package de.CypDasHuhn.TpLc.Listeners;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.FileManagment.RegisterFolder;
import de.CypDasHuhn.TpLc.Interface.FolderInterface.fInterface;
import de.CypDasHuhn.TpLc.commands.TeleportSet;
import de.CypDasHuhn.TpLc.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener{
	static int TaskId;
	@EventHandler
	public static void chatListener(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String m = e.getMessage();
		FileConfiguration dConfig = CustomFiles.gfc("Data", p.getUniqueId()+"");
		if (dConfig.getString("Chat") == null) return;
		if (dConfig.getString("Chat").equals("Empty")) return;
		e.setCancelled(true);
		if (m.equals("EMPTY")) {
			p.sendMessage("�c\"EMPTY\" kann wegen technischen Limitationen nicht genutzt werden.");
			p.sendMessage("�cSchreib den gew�nschten Namen noch einmal in den Chat");
			return;
		}
		if (m.contains("/") || m.contains("\\") || m.contains(":") || m.contains("*") || m.contains("?") || m.contains("\"") || m.contains("<") || m.contains(">") || m.contains("|")) {
			repeate(p);
			return;
		}CustomFiles.save();
		TaskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getPlugin(), new Runnable(){
			int countdown = 1;
			@Override
			public void run(){
				if (countdown == 0){
				chatDecoder(e.getPlayer(),e.getMessage());
				Bukkit.getScheduler().cancelTask(TaskId);				 
				} countdown--;
			}
		}, 0, 1);
	}public static void chatDecoder(Player p, String Message) {
		FileConfiguration dConfig = CustomFiles.gfc("Data", p.getUniqueId()+"");
		
		String Type = dConfig.getString("Chat");
		String Parent = dConfig.getString("Parent");
		int ID = dConfig.getInt("Slot");
		
		if (Type.equals("Location")) {
			TeleportSet.registerLocation(p, Message, ID, Parent);
			fInterface.finterface(p, Parent, dConfig.getInt("Page"));
		}else if (Type.equals("Folder")) {
			RegisterFolder.registerFolder(p, Message, ID, Parent);
			fInterface.finterface(p, Message, 1);
		}dConfig = CustomFiles.gfc("Data", p.getUniqueId()+"");
		dConfig.set("Chat", "Empty");
		CustomFiles.save();
	}public static void repeate(Player p) {
		p.sendMessage("�cDu hast einen der folgenden Zeichen genutzt:");
		p.sendMessage("�c\\, /, :, *, ?, \", <, >, |");
		p.sendMessage("�cSchreib den gew�nschten Namen noch einmal in den Chat, ohne die Zeichen.");
	}
}

