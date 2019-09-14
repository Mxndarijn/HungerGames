package me.Miracles.Hg;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
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
		Player p = (Player) e.getEntity();
		DamageCause cause = LastDamage.get(p);
		Bukkit.broadcastMessage("" + cause);
		if(cause == DamageCause.ENTITY_ATTACK && Damager.get(p) != p) {
			Player damager = Damager.get(p);
			e.setDeathMessage("§7" + p.getName() + " was killed by " + damager.getName() + "!");
		}
		if(cause == DamageCause.ENTITY_ATTACK && DamagerEn.get(p) != p) {
			Entity damager = DamagerEn.get(p);
			e.setDeathMessage("§7" + p.getName() + " was killed by " + damager.getType() + "!");
		}
		if(cause == DamageCause.FALL) {
			e.setDeathMessage("§7" + p.getName() + " thought he could fly!");
		}
		if(cause == DamageCause.FIRE) {
			e.setDeathMessage("§7" + p.getName() + " was killed by fire!");
		}
		if(cause == DamageCause.BLOCK_EXPLOSION) {
			e.setDeathMessage("§7" + p.getName() + " was killed by an explosion!");
			
		}
		p.spigot().respawn();
		e.getEntity().setGameMode(GameMode.SPECTATOR);
		e.getEntity().setHealth(20);
		e.setKeepInventory(true);
	}
	
	@EventHandler
	public void PlayerDamage(EntityDamageByEntityEvent e) {
		Bukkit.broadcastMessage("" + e.getCause());
		if(e.getEntity() instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getCause() == DamageCause.ENTITY_ATTACK && e.getDamager() instanceof Player) {
				p.sendMessage("Contact Player");
				LastDamage.put(p, DamageCause.ENTITY_ATTACK);
				Damager.put(p, (Player) e.getDamager());
				DamagerEn.put(p, p);
				return;
			}
			if(e.getCause() == DamageCause.ENTITY_ATTACK && e.getDamager() instanceof Entity) {
				p.sendMessage("Contact Entity");
				LastDamage.put(p, DamageCause.CONTACT);
				Damager.put(p, p);
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
			if(e.getCause() == DamageCause.FIRE) {
				LastDamage.put(p, DamageCause.FIRE);
			}
		}
		
	}

}
