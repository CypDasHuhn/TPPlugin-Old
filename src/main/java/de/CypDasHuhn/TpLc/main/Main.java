package de.CypDasHuhn.TpLc.main;

import de.CypDasHuhn.TpLc.Interface.ConfirmingInterface.confirmingInterfaceListener;
import de.CypDasHuhn.TpLc.Interface.EditInterface.eInterfaceListener;
import de.CypDasHuhn.TpLc.Interface.FolderInterface.fInterfaceListener;
import de.CypDasHuhn.TpLc.Interface.SelectItemInterface.SelectItemInterfaceListener;
import de.CypDasHuhn.TpLc.Listeners.ChatListener;
import de.CypDasHuhn.TpLc.Listeners.CloseInvListener;
import de.CypDasHuhn.TpLc.commands.Command;
import de.CypDasHuhn.TpLc.tlTabCompleter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	private static Main plugin; 
	public void onEnable(){
		if (!getDataFolder().exists()) {
			getDataFolder().mkdirs();
		}
		plugin = this;
		getCommand("ts").setExecutor(new Command());
		getCommand("ts").setTabCompleter(new tlTabCompleter());
		getCommand("t").setExecutor(new Command());
		getCommand("t").setTabCompleter(new tlTabCompleter());
		getCommand("td").setExecutor(new Command());
		getCommand("td").setTabCompleter(new tlTabCompleter());
		getCommand("tptest").setExecutor(new Command());
		PluginManager pluginManager = Bukkit.getPluginManager();
		pluginManager.registerEvents(new fInterfaceListener(), this);
		pluginManager.registerEvents(new eInterfaceListener(), this);
		pluginManager.registerEvents(new SelectItemInterfaceListener(), this);
		pluginManager.registerEvents(new confirmingInterfaceListener(), this);
		pluginManager.registerEvents(new CloseInvListener(), this);
		pluginManager.registerEvents(new ChatListener(), this);
	}
	public static Main getPlugin(){
		return plugin;
	}
}