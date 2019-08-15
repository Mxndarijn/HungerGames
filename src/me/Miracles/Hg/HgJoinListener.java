package me.Miracles.Hg;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class HgJoinListener implements Listener {
	
	@EventHandler
	public void Join(PlayerJoinEvent e) {
		e.setJoinMessage("§e" + e.getPlayer().getName() + " has joined!");
		HgMain.PlayersInGame++;
		
	}
	
}
