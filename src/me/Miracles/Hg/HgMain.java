package me.Miracles.Hg;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;


public class HgMain extends JavaPlugin implements Listener {
	public static HgMain Main;
	public static String GameProgress = "Wachtend";
	public static int PlayersInGame = 0;
	
	
	
	public void onEnable() {
		Main = this;
		getServer().getPluginManager().registerEvents(new HgKleineEvents(), this);
		getServer().getPluginManager().registerEvents(new HgJoinListener(), this);
		getServer().getPluginManager().registerEvents(new HgQuitListener(), this);
		
		
	}
	
	public static String GetString(String path) {
		return Main.getConfig().getString(path);
	}
	public static int GetInt(String path) {
		return Main.getConfig().getInt(path);
	}
	public static Boolean GetBoolean(String path) {
		return Main.getConfig().getBoolean(path);
	}

}
