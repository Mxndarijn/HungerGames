package me.Miracles.Hg;

import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.PlayerDeathEvent;

public class HgDeath implements Listener {
	public HashMap<Player, DamageCause> LastDamage = new HashMap<Player, DamageCause>();
	public HashMap<Player, Player> Damager = new HashMap<Player,  Player>();
	public HashMap<Player, Entity> DamagerEn = new HashMap<Player,  Entity>();
	
	
	@EventHandler
	public void PlayerDeath(PlayerDeathEvent e) {
		if(e.getEntityType() == EntityType.PLAYER) {
			
			Player p = (Player) e.getEntity();
			DamageCause cause = LastDamage.get(p);
			if(cause == DamageCause.CONTACT && Damager.get(p) != null) {
				Player damager = Damager.get(p);
				e.setDeathMessage("§7" + p.getName() + " was killed by " + damager.getName() + "!");
				return;
			}
			if(cause == DamageCause.CONTACT && DamagerEn.get(p) != null) {
				Entity damager = DamagerEn.get(p);
				e.setDeathMessage("§7" + p.getName() + " was killed by " + damager.getType() + "!");
				return;
			}
			if(cause == DamageCause.FALL) {
				e.setDeathMessage("§7" + p.getName() + " thought he could fly!");
				return;
			}
			if(cause == DamageCause.BLOCK_EXPLOSION) {
				e.setDeathMessage("§7" + p.getName() + " was killed by an explosion!");
				return;
			}
			p.spigot().respawn();
		}
	}
	
	@EventHandler
	public void PlayerDamage(EntityDamageByEntityEvent e) {
		if(e.getEntityType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.CONTACT && e.getDamager().getType() == EntityType.PLAYER) {
				LastDamage.put(p, DamageCause.CONTACT);
				Damager.put(p, (Player) e.getDamager());
				DamagerEn.put(p, null);
				return;
			}
			if(e.getCause() == DamageCause.CONTACT && e.getDamager().getType() != EntityType.PLAYER) {
				LastDamage.put(p, DamageCause.CONTACT);
				Damager.put(p, null);
				DamagerEn.put(p, e.getDamager());
			}
		}
		
	}
	@EventHandler
	public void PlayerDamage(EntityDamageEvent e) {
		if(HgMain.GameProgress.equalsIgnoreCase("Bezig")) {
			e.setCancelled(true);
		}
		if(e.getEntityType() == EntityType.PLAYER) {
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.FALL) {
				LastDamage.put(p, DamageCause.FALL);
			}
			if(e.getCause() == DamageCause.BLOCK_EXPLOSION) {
				LastDamage.put(p, DamageCause.BLOCK_EXPLOSION);
			}
		}
		
	}

}
