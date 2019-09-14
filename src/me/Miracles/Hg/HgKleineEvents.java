package me.Miracles.Hg;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class HgKleineEvents implements Listener{
	
	@EventHandler
	public void BlockBreak(BlockBreakEvent even) {
		even.setCancelled(false);
		
	}
	@EventHandler
	public void BlockPlace(BlockPlaceEvent even) {
		even.setCancelled(false);
	}
	@EventHandler
	public void WeatherChange(WeatherChangeEvent even) {
		even.setCancelled(true);
	}
	
	@EventHandler
	public void FoodChange(FoodLevelChangeEvent even) {
		if(HgMain.GameProgress.equalsIgnoreCase("Wachtend")) {
			even.setCancelled(true);
		}
	}
	
	@EventHandler
	public void MoveEvent(PlayerMoveEvent even) {
		if(HgMain.CancelMove == true) {
			if(even.getFrom() != even.getTo()) {
				even.setCancelled(true);
			}
		}
	}
	
	
	
}