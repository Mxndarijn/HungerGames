package me.Miracles.Hg;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;





public class HgMain extends JavaPlugin implements Listener {
	public static HgMain Main;
	public static boolean CancelMove = false;
	public static String GameProgress = "Wachtend";
	public static int PlayersInGame = 0;
	public File FileKitsData;
	public FileConfiguration FileKitsConfig;
	
	
	public void onEnable() {
		Main = this;
		this.FileKitsData = new File(this.getDataFolder(), "KitsData.yml");
		this.FileKitsConfig = YamlConfiguration.loadConfiguration(this.FileKitsData);
		getServer().getPluginManager().registerEvents(new HgKleineEvents(), this);
		getServer().getPluginManager().registerEvents(new HgJoinListener(), this);
		getServer().getPluginManager().registerEvents(new HgQuitListener(), this);
		getServer().getPluginManager().registerEvents(new HgChest(), this);
		getServer().getPluginManager().registerEvents(new HgDeath(), this);
		getServer().getPluginManager().registerEvents(new HgKits(), this);
		getCommand("hungergames").setExecutor(new HgKits());
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
	public static void saveKitsData()  {
		try {
	    	HgMain.Main.FileKitsConfig.save(HgMain.Main.FileKitsData); }
	    catch (IOException localIOException) {} 
	}
	
	public void CreateConfigs() {
		SetConfig("World", "world");
		SetConfig("KitsID", 0);
		SetConfig("MaxPlayers", 7);
		SetConfig("MinPlayers", 2);
		SetConfig("Locations.Lobby.x", 100);
		SetConfig("Locations.Lobby.y", 100);
		SetConfig("Locations.Lobby.z", 100);
		
		SetConfig("Locations.Spawns.1.x", 100);
		SetConfig("Locations.Spawns.1.y", 100);
		SetConfig("Locations.Spawns.1.z", 100);
		
		SetConfig("Locations.Spawns.2.x", 101);
		SetConfig("Locations.Spawns.2.y", 100);
		SetConfig("Locations.Spawns.2.z", 100);
		
		SetConfig("Locations.Spawns.3.x", 102);
		SetConfig("Locations.Spawns.3.y", 100);
		SetConfig("Locations.Spawns.3.z", 100);
		
		SetConfig("Locations.Spawns.4.x", 103);
		SetConfig("Locations.Spawns.4.y", 100);
		SetConfig("Locations.Spawns.4.z", 100);
		
		SetConfig("Locations.Spawns.5.x", 104);
		SetConfig("Locations.Spawns.5.y", 100);
		SetConfig("Locations.Spawns.5.z", 100);
		
		SetConfig("Locations.Spawns.6.x", 105);
		SetConfig("Locations.Spawns.6.y", 100);
		SetConfig("Locations.Spawns.6.z", 100);
		
		SetConfig("Locations.Spawns.7.x", 106);
		SetConfig("Locations.Spawns.7.y", 100);
		SetConfig("Locations.Spawns.7.z", 100);
		
		SetConfig("MapName", "Default");
		SaveConfig();

	}
	
	public static void updatescore() {
		new BukkitRunnable() {
		    public void run() {
		    	for (Player pla : Bukkit.getServer().getOnlinePlayers()) {
                   HgScoreboard.updateScoreBoard(pla);

                }
		    	
		    	
		    }
		}.runTaskTimer(Main, 0, 10);
		}

}
