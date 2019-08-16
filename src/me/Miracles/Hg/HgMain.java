package me.Miracles.Hg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;



public class HgMain extends JavaPlugin implements Listener {
	public static HgMain Main;
	public static String GameProgress = "Wachtend";
	public static int PlayersInGame = 0;
	
	
	
	public void onEnable() {
		Main = this;
		getServer().getPluginManager().registerEvents(new HgKleineEvents(), this);
		getServer().getPluginManager().registerEvents(new HgJoinListener(), this);
		getServer().getPluginManager().registerEvents(new HgQuitListener(), this);
		getServer().getPluginManager().registerEvents(new HgChest(), this);
		getServer().getPluginManager().registerEvents(new HgDeath(), this);
		CreateConfigs();
		updatescore();
		
		
	}
	
	public static String GetString(String path) {
		return Main.getConfig().getString(path);
	}
	public static int GetInt(String path) {
		return Main.getConfig().getInt(path);
	}
	public static Double GetDouble(String path) {
		return Main.getConfig().getDouble(path);
	}
	public static Boolean GetBoolean(String path) {
		return Main.getConfig().getBoolean(path);
	}
	public static void SetConfig(String path, Object to) {
		Main.getConfig().set(path, to);
	}
	public static void SaveConfig() {
		Main.saveConfig();
	}
	
	public void CreateConfigs() {
		SetConfig("World", "world");
		SetConfig("MaxPlayers", 24);
		SetConfig("Locations.Lobby.x", 100);
		SetConfig("Locations.Lobby.y", 100);
		SetConfig("Locations.Lobby.z", 100);
		SetConfig("MapName", "Default");
		SaveConfig();

	}
	
	public static void updatescore() {
		new BukkitRunnable() {
		    public void run() {
		    	for (Player pla : Bukkit.getServer().getOnlinePlayers()) {
                   HgScoreboard.createscoreboard(pla);

                }
		    	
		    	
		    }
		}.runTaskTimer(Main, 0, 10);
		}

}
