package me.Miracles.Hg;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class HgQuitListener implements Listener {
	
	@EventHandler
	public void Join(PlayerQuitEvent e) {
		e.setQuitMessage("§e" + e.getPlayer().getName() + " has left!");
		HgMain.PlayersInGame--;
		
	}

}
