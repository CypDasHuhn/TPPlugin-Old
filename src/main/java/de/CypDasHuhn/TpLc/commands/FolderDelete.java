package de.CypDasHuhn.TpLc.commands;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.FileManagment.ParentUpdater;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FolderDelete {
	public static void allDelete(Player p, String Name) {
		FileConfiguration pConfig = CustomFiles.gfc(Name, p.getUniqueId()+"/Folders");
		
		String Parent = pConfig.getString("Parent.Name");
		int slot = pConfig.getInt("Parent.Slot");
		
		ParentUpdater.updateParent(p, "EMPTY", Parent, slot, "EMPTY");
		
		FileConfiguration lConfig = CustomFiles.gfc("List", p.getUniqueId()+"");
		
		int lAmmount = lConfig.getInt("Location.Id.Ammount");
		int fAmmount = lConfig.getInt("Folder.Id.Ammount");
		
		for (int i = 0; i < lAmmount; i++) {
			String tName = lConfig.getString("Location.Id."+i);
			FileConfiguration tConfig = CustomFiles.gfc(tName, p.getUniqueId()+"/Locations");
			String tParent = tConfig.getString("Parent.Name");
			if (tParent.equals(Name)) {
				CustomFiles.delete(tName, p.getUniqueId()+"/Locations");
				deleteList(p, tName);
			}
		}for (int i = 0; i < fAmmount-1; i++) {
			String fName = lConfig.getString("Folder.Id."+i);
			FileConfiguration fConfig = CustomFiles.gfc(fName, p.getUniqueId()+"/Folders");
			String fParent = fConfig.getString("Parent.Name");
			if (fParent.equals(Name)) {
				CustomFiles.delete(fName, p.getUniqueId()+"/Folders");
				deleteList(p, fName);
			}
		}deleteList(p, Name);
		p.sendMessage("�aDer Ordner wurde gel�scht.");
	}public static void folderDelete(Player p, String Name) {
		FileConfiguration pConfig = CustomFiles.gfc(Name, p.getUniqueId()+"/Folders");
		
		String Parent = pConfig.getString("Parent.Name");
		int slot = pConfig.getInt("Parent.Slot");
		
		ParentUpdater.updateParent(p, "EMPTY", Parent, slot, "EMPTY");
		
		FileConfiguration lConfig = CustomFiles.gfc("List", p.getUniqueId()+"");
		
		int lAmmount = lConfig.getInt("Location.Id.Ammount");
		int fAmmount = lConfig.getInt("Folder.Id.Ammount");
		for (int i = 0; i < lAmmount; i++) {
			String tName = lConfig.getString("Location.Id."+i);
			FileConfiguration tConfig = CustomFiles.gfc(tName, p.getUniqueId()+"/Locations");
			String tParent = tConfig.getString("Parent.Name");
			if (tParent.equals(Name)) {
				int ID = getNewID(p, Parent);
				
				ParentUpdater.updateChild(p, tName, "Location", Parent, ID);
				
				ParentUpdater.updateParent(p, tName, Parent, ID, "Location");
			}
		}for (int i = 0; i < fAmmount; i++) {
			String fName = lConfig.getString("Folder.Id."+i);
			FileConfiguration fConfig = CustomFiles.gfc(fName, p.getUniqueId()+"/Folders");
			String fParent = fConfig.getString("Parent.Name");
			if (fParent.equals(Name)) {
				int ID = getNewID(p, Parent);
				
				ParentUpdater.updateChild(p, fName, "Folder", Parent, ID);
				
				ParentUpdater.updateParent(p, fName, Parent, ID, "Folder");
			}
		}
		CustomFiles.delete(Name, p.getUniqueId()+"/Folders");
		deleteList(p, Name);
		p.sendMessage("�aDer Ordner wurde gel�scht.");
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
	}public static void deleteList(Player p, String Name) {
		FileConfiguration lConfig = CustomFiles.gfc("List", p.getUniqueId()+"");
		int IdAmmount = lConfig.getInt("Folder.Id.Ammount");
		int Id = 0;
		for (int ii = 0; ii < IdAmmount-1; ii++) {
			if (lConfig.getString("Folder.Id."+ii).equalsIgnoreCase(Name)) Id = ii;
		}for (int ii = Id; ii < IdAmmount; ii++) {
			lConfig.set("Folder.Id."+ii, lConfig.getString("Folder.Id."+(ii+1)));
		}lConfig.set("Folder.Id.Ammount", IdAmmount-1);
		CustomFiles.save();
	}
}
