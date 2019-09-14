package me.Miracles.Hg;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class HgChest implements Listener {
	public HashMap<Location, String> LocChest = new HashMap<Location, String>();
	public HashMap<Location, Integer> LocChestt = new HashMap<Location, Integer>();
	public HashMap<Integer, Entity> IdChest = new HashMap<Integer, Entity>();
	public HashMap<Player, Location> PlayerLatestChest = new HashMap<Player, Location>();
	public int Number = 0;
	
	@EventHandler
	public void ChestOpen(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType() == Material.CHEST) {
				PlayerLatestChest.put(e.getPlayer(), e.getClickedBlock().getLocation());
				if(!LocChest.containsKey(e.getClickedBlock().getLocation())) {
					Location loc = e.getClickedBlock().getLocation();
					Entity en = loc.getWorld().spawnEntity(loc.add(0.5, -1.2, 0.5), EntityType.ARMOR_STAND);
					en.setCustomName("§eOpened");
					en.setCustomNameVisible(true);
					ArmorStand am = (ArmorStand) en;
					am.setGravity(false);
					am.setVisible(false);
					am.setBasePlate(false);
					Number = Number + 1;
					loc = e.getClickedBlock().getLocation();
					LocChest.put(loc, "Opened");
					IdChest.put(Number, en);
					LocChestt.put(loc, Number);
					PlayerLatestChest.put(e.getPlayer(), loc);
					return;
				}
			}
		}
	}
	
	@EventHandler
	public void ChestClose(InventoryCloseEvent e) {
		if(e.getInventory().getHolder() instanceof Chest) {
			Location loc = PlayerLatestChest.get(e.getPlayer());
			Boolean full = CheckInvFull(e.getInventory());
			if(full == true) {
				int number = LocChestt.get(loc);
				Entity en = IdChest.get(number);
				ArmorStand am = (ArmorStand) en;
				am.setCustomName("§cEmpty");
			}
			if(full == false) {
				int number = LocChestt.get(loc);
				Entity en = IdChest.get(number);
				ArmorStand am = (ArmorStand) en;
				am.setCustomName("§eOpened");
			}
		}
	}
	
	public Boolean CheckInvFull(Inventory inv) {
		for(ItemStack it : inv.getContents()) {
		    if(it != null) return false;
		}
		return true;
	}
}
