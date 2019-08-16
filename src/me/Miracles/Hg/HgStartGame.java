package me.Miracles.Hg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class HgStartGame {
	
	
	public void StartGame() {
		World world = Bukkit.getWorld(HgMain.GetString("World"));
		int number = 0;
		for(Player p : Bukkit.getOnlinePlayers()) {
			number++;
			Location loc = new Location(world, HgMain.GetDouble("Locations.Spawns." + number + ".x"), HgMain.GetDouble("Locations.Spawns." + number + ".y"), HgMain.GetDouble("Locations.Spawns." + number + ".z"), HgMain.GetInt("Locations.Spawns." + number + ".h1"), HgMain.GetInt("Locations.Spawns." + number + ".h2"));
			p.teleport(loc);
			
			
		}
	}
}
