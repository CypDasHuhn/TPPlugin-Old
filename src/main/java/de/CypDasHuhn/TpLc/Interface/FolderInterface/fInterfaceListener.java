package de.CypDasHuhn.TpLc.Interface.FolderInterface;

import de.CypDasHuhn.TpLc.FileManagment.CustomFiles;
import de.CypDasHuhn.TpLc.Interface.EditInterface.eInterface;
import de.CypDasHuhn.TpLc.Listeners.CloseInvListener;
import de.CypDasHuhn.TpLc.commands.Teleport;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
 
public class fInterfaceListener implements Listener{
	@EventHandler
	public void finterfaceListener(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		//Check if Right Inventory////////////////////////////////////////////
		if (config.getString("WhichInv").equals("Interface")) {
			e.setCancelled(true);
			
			String Parent = config.getString("Parent");
			int Page = config.getInt("Page");
			int rSlot = e.getRawSlot();
			int slot = e.getRawSlot()+(Page-1)*45;
			Material m  = e.getCurrentItem().getType();
			
			config.set("Slot",  slot);
			CustomFiles.save();
			
			FileConfiguration pConfig = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");
			String GrandParent = "";
			String Type = pConfig.getString("Child."+slot+".Type");
			if (!Parent.equals("General")) GrandParent = pConfig.getString("Parent.Name");
			
			CloseInvListener.exception = true;
			
			switch (m) {
			case ARROW:
				if (rSlot == 50) fInterface.finterface(p, Parent, config.getInt("Page")+1);
				else if (rSlot == 48) fInterface.finterface(p, Parent, config.getInt("Page")-1);
				break;
			case FEATHER:
				fInterface.finterface(p, GrandParent, 1);
				break;
			case BLACK_STAINED_GLASS_PANE:
				
				break;	
			case LIGHT_GRAY_STAINED_GLASS_PANE:
				if (e.getClick().isShiftClick()) eInterface.Interface(p, null, "Empty");
				break;	
			case LIME_STAINED_GLASS_PANE:
				fInterface.Move.put(p.getUniqueId()+"","false");
				String MoveType = config.getString("MoveType");
				String MoveParent = config.getString("MoveParent");
				FileConfiguration lConfig = CustomFiles.gfc(config.getString("Move"), p.getUniqueId()+"/"+MoveType+"s");
				lConfig.set("Parent.Name", Parent);
				String prevSlot = lConfig.getString("Parent.Slot");
				lConfig.set("Parent.Slot", slot);
				CustomFiles.save();
				FileConfiguration pConfig2 = CustomFiles.gfc(MoveParent, p.getUniqueId()+"/Folders");
				pConfig2.set("Child."+prevSlot+".Name", "EMPTY");
				pConfig2.set("Child."+prevSlot+".Type", "EMPTY");
				CustomFiles.save();
				pConfig = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");
				pConfig.set("Child."+slot+".Name", config.getString("Move"));
				pConfig.set("Child."+slot+".Type", config.getString("MoveType"));
				CustomFiles.save();
				fInterface.finterface(p, Parent, Page);
				break;	
				
				default:
					String Name = e.getCurrentItem().getItemMeta().getDisplayName().substring(2);
					if (fInterface.Move.get(p.getUniqueId()+"").equals("false")) {
						////////Normal-Location////////////////////////////////////////////
						if (Type.equals("Location")) {
							/////////LeftClick////
							if (e.getClick().isLeftClick() && !e.getClick().isShiftClick()) {
								Teleport.teleport(p, Name);
								CloseInvListener.manuellListener(p);
							}/////////RightClick////
							if (e.getClick().isRightClick() && !e.getClick().isShiftClick()) {
								move(p, Name, Parent, Type);
							}/////////ShiftClick////
							if (e.getClick().isShiftClick()) {
								editor(p, Type, e.getCurrentItem().getItemMeta().getDisplayName().substring(2));
							}
						}///////Normal-Folder///////////////////////////////////////////////////
						if (Type.equals("Folder")) {
							/////////LeftClick////
							if (e.getClick().isLeftClick() && !e.getClick().isShiftClick()) {
								fInterface.finterface(p, Name, 1);
							}/////////RightClick////
							if (e.getClick().isRightClick() && !e.getClick().isShiftClick()) {
								move(p, Name, Parent, Type);
							}/////////ShiftClick////
							if (e.getClick().isShiftClick()) {
								editor(p, Type, e.getCurrentItem().getItemMeta().getDisplayName().substring(2));
							}
						}
					}else {
						if (config.getString("Move").equals(Name) && config.getString("MoveType").equals(Type)) {
							fInterface.Move.put(p.getUniqueId()+"","false");
							CloseInvListener.exception = true;
							fInterface.finterface(p, Parent, Page);
						}
						////////Move-Location////////////////////////////////////////////
						if (Type.equals("Location")) {
							swap(p, Name, Parent, Page, Type);	
						}///////Move-Folder//////////////////////////////////////////////
						if (Type.equals("Folder")) {
							///////////NormalClick////
							if (!e.getClick().isShiftClick()) {
								fInterface.finterface(p, e.getCurrentItem().getItemMeta().getDisplayName().substring(2), 1);
							}//////////ShiftClick////////
							else {
								swap(p, Name, Parent, Page, Type);	
							}
						}
					}
					break;
			}
		}CloseInvListener.exception = false;
	}public static void swap(Player p, String Name2, String Parent, int Page, String Type2) {
		fInterface.Move.put(p.getUniqueId()+"","false");
		
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		String Type1 = config.getString("MoveType");
		String Name1 = config.getString("Move");
		FileConfiguration config1 = CustomFiles.gfc(Name1, p.getUniqueId()+"/"+Type1+"s");
		String MoveParent = config.getString("MoveParent");
		int slot1 = config1.getInt("Parent.Slot");
		
		FileConfiguration config2 = CustomFiles.gfc(Name2, p.getUniqueId()+"/"+Type2+"s");
		int slot2 = config2.getInt("Parent.Slot");
		
		config1 = CustomFiles.gfc(config.getString("Move"), p.getUniqueId()+"/"+Type1+"s");	
		config1.set("Parent.Name", Parent);
		config1.set("Parent.Slot", slot2);
		CustomFiles.save();
		
		config2 = CustomFiles.gfc(Name2, p.getUniqueId()+"/"+Type2+"s");
		config2.set("Parent.Name", MoveParent);
		config2.set("Parent.Slot", slot1);
		CustomFiles.save();
			
		FileConfiguration pConfig1 = CustomFiles.gfc(MoveParent, p.getUniqueId()+"/Folders");
		pConfig1.set("Child."+slot2+".Name", Name1);
		pConfig1.set("Child."+slot2+".Type", Type1);
		CustomFiles.save();
		
		FileConfiguration pConfig2 = CustomFiles.gfc(Parent, p.getUniqueId()+"/Folders");		
		pConfig2.set("Child."+slot1+".Name", Name2);
		pConfig2.set("Child."+slot1+".Type", Type2);
		CustomFiles.save();
		
		CloseInvListener.exception = true;
		fInterface.finterface(p, Parent, Page);
		CloseInvListener.exception = false;
	}///////////Move///////////////////////
	public static void move(Player p, String Name, String Parent, String Type) {
		FileConfiguration config = CustomFiles.gfc("Data", p.getUniqueId()+"");
		config.set("Move", Name);
		config.set("MoveType", Type);
		config.set("MoveParent", Parent);
		CustomFiles.save();
		
		fInterface.Move.put(p.getUniqueId()+"","true");
		
		fInterface.finterface(p, Parent, config.getInt("Page"));
	}///////////Editor////////////////////
	public static void editor(Player p, String Type, String Name) {
		eInterface.Interface(p, Name, Type);
		CloseInvListener.exception = true;
	}
}
