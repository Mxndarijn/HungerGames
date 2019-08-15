package me.Miracles.Hg;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import com.avaje.ebeaninternal.server.cluster.mcast.IncomingPacketsProcessed.GotAllPoint;

public class HgKleineEvents implements Listener{
	public void block(BlockBreakEvent even) {
		even.setCancelled(true);
		
	}
	
	public void block1(BlockPlaceEvent even) {
		even.setCancelled(true);
	}
	
	public void weatt(WeatherChangeEvent even) {
		even.setCancelled(true);
	}
	
	public void weat1(FoodLevelChangeEvent even) {
		if(HgMain.GameProgress == "Wachtend") {
			even.setCancelled(true);
		}
	}
	public void weat2(PlayerMoveEvent even) {
		if(HgMain.GameProgress == "Starting") {
			Player pla = even.getPlayer();
			if(even.getFrom() != even.getTo()) {
				even.setCancelled(true);
			}
		}
	}
	
}
