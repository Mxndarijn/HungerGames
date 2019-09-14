package me.Miracles.Hg;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class HgQuitListener implements Listener {
	
	@EventHandler
	public void Join(PlayerQuitEvent e) {
		if(HgMain.PlayersInGame == 24) {
			e.setQuitMessage("");
			return;
		}
		HgMain.PlayersInGame = HgMain.PlayersInGame -1;
		e.setQuitMessage("§e" + e.getPlayer().getName() + " left! (" + HgMain.PlayersInGame + "/" + HgMain.GetInt("MaxPlayers") + ")");
		
		
	}

}
