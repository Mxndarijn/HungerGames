package me.Miracles.Hg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class HgStartGame {
	
	
	public static void StartGame() {
		if(HgMain.GameProgress.equalsIgnoreCase("Starting") || HgMain.GameProgress.equalsIgnoreCase("Bezig")) return;
		HgMain.GameProgress =  "Starting";
		new BukkitRunnable() {
			int number = 30;
		    public void run() {
		    	for(Player p : Bukkit.getOnlinePlayers()) {
		    		if(number > 1) {
			    		if (number < 5 || number == 30 || number == 25 || number == 20 || number == 15 || number == 10 && number != 1) {
			    			p.sendMessage("§eGame starting in §6" + number + " seconds" );
			    		}
		    		}
		    		if(number == 1) {
			    		p.sendMessage("§eGame starting in §6" + number + " second" );
			    		}
		    		
		    		
		    	}
		    	if(number == 0) {
		    			HgStartGame.StartGamee();
		    			this.cancel();
		    			return;
		    		}
		    	number--;
		    }
		}.runTaskTimer(HgMain.Main, 0, 20);
		
	}
	
	public static void StartGamee() {
		World world = Bukkit.getWorld(HgMain.GetString("World"));
		int number = 0;
		for(Player p : Bukkit.getOnlinePlayers()) {
			number++;
			Location loc = new Location(world, HgMain.GetDouble("Locations.Spawns." + number + ".x"), HgMain.GetDouble("Locations.Spawns." + number + ".y"), HgMain.GetDouble("Locations.Spawns." + number + ".z"), HgMain.GetInt("Locations.Spawns." + number + ".h1"), HgMain.GetInt("Locations.Spawns." + number + ".h2"));
			p.teleport(loc);
		}
		CountDown();
	}
public static void CountDown() {
		HgMain.CancelMove = true;
		new BukkitRunnable() {
			
			int number = 10;
		    public void run() {
		    	for(Player p : Bukkit.getOnlinePlayers()) {
		    		if(number > 1) {
		    		p.sendMessage("§eStarting in §6" + number + " seconds" );
		    		}
		    		if(number == 1) {
			    		p.sendMessage("§eStarting in §6" + number + " second" );
			    		}
		    		
		    	}
		    	
		    	if(number == 0) {
		    		HgMain.GameProgress = "Bezig";
		    		HgMain.CancelMove = false;
		    		this.cancel();
		    		return;
		    	}
		    	number--;
		    }
		}.runTaskTimer(HgMain.Main, 0, 20);
		
	}
}
