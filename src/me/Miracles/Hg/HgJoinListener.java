package me.Miracles.Hg;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HgJoinListener implements Listener {
	
	@EventHandler
	public void Join(PlayerJoinEvent e) {
		HgMain.PlayersInGame= HgMain.PlayersInGame + 1;
		HgScoreboard.createscoreboardp(e.getPlayer());
		e.setJoinMessage("§e" + e.getPlayer().getName() + " joined! (" + HgMain.PlayersInGame + "/" + HgMain.GetInt("MaxPlayers") + ")");
		if(HgMain.PlayersInGame > HgMain.GetInt("MaxPlayers")) {
			e.setJoinMessage("");
			HgMain.PlayersInGame--;
			e.getPlayer().kickPlayer("§cTeveel Spelers");
		}
		if(HgMain.PlayersInGame >= HgMain.GetInt("MinPlayers")) {
			HgStartGame.StartGame();
		}
		
		World world = Bukkit.getWorld(HgMain.GetString("World"));
		Location loc = new Location(world, HgMain.GetDouble("Locations.Lobby.x"), HgMain.GetDouble("Locations.Lobby.y"), HgMain.GetDouble("Locations.Lobby.z"));
		e.getPlayer().teleport(loc);
		
	}
	
}
