package me.Miracles.Hg;


import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;



@SuppressWarnings("deprecation")
public class HgKits implements CommandExecutor, Listener {
	public HashMap<Player, Boolean> KitCreate1 = new HashMap<Player, Boolean>();
	public HashMap<Player, Integer> KitChangename = new HashMap<Player, Integer>();
	public HashMap<Player, Integer> KitChangedes = new HashMap<Player, Integer>();
	
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg3[0].equalsIgnoreCase("kits") && arg0.hasPermission("HungerGames.Admin") &&  arg0 instanceof Player) {
			arg0.sendMessage("§cOpenening Kits Menu");
			Inventory inv = Bukkit.getServer().createInventory(null, 27, "§eKits§f");
			ItemStack is1 = new ItemStack(Material.WORKBENCH);
			ItemMeta im1 = is1.getItemMeta();
			im1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			im1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			im1.setDisplayName("§eCreate new kits");
			is1.setItemMeta(im1);
			inv.setItem(10, is1);
			
			is1.setType(Material.PAPER);
			im1.setDisplayName("§eModify Kits");
			is1.setItemMeta(im1);
			inv.setItem(13, is1);
			
			ItemStack is2 = new ItemStack(Material.INK_SACK, 1, (short) 1);
			ItemMeta im2 = is2.getItemMeta();
			im2.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			im2.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			im2.setDisplayName("§cDelete Kits");
			is2.setItemMeta(im2);
			inv.setItem(16, is2);
			Player p = (Player) arg0;
			p.openInventory(inv);
			return true;
			
		}
		if(arg3[0].equalsIgnoreCase("kits")) {
			arg0.sendMessage("§cYou are not permitted to do this!");
			return true;
		}
		return false;
		
		}
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		Player player = (Player) e.getWhoClicked();
		if (e.getView().getTopInventory().getTitle().equals("§eKits List")) {
			e.setCancelled(true);
			int slot = e.getSlot();
			player.sendMessage(slot + " ");
			if(e.getCurrentItem().getType() != Material.AIR) {
				int id = e.getSlot() +1;
				MainKitInv(id, player);
			}
		}
		if (e.getView().getTopInventory().getTitle().equals("§eKits§f")) {
			e.setCancelled(true);
			int slot = e.getSlot();
			player.sendMessage(slot + " ");
			if(slot == 10) {
				KitCreate1.put(player, true);
				player.sendMessage("§7[§eKit Creation§7] §cType the name of the new kit in chat! (With & for colors)");
				player.closeInventory();
			}
			if(slot == 13) {
				Inventory inv = Bukkit.getServer().createInventory(null, 27, "§eKits List");
				for(int i = 1; i <= HgMain.GetInt("KitsID"); i++) {
					Bukkit.broadcastMessage(i + "");
					ItemStack Item = HgMain.Main.FileKitsConfig.getItemStack(i + ".SelectorItem");
					String name = HgMain.Main.FileKitsConfig.getString(i + ".Name");
					String des = HgMain.Main.FileKitsConfig.getString(i + ".KitDescription");
					ItemStack is = new ItemStack(Item.getType());
					ItemMeta im = is.getItemMeta();
					im.setDisplayName("§f" + name);
					ArrayList<String> lore = new ArrayList<String>();
					lore.add(des);
					im.setLore(lore);
					Item.setItemMeta(im);
					inv.setItem(i -1, Item);
					
				}
				player.openInventory(inv);
				
				
			}
			if(slot == 16) {
				
			}
		}
		if (e.getInventory().getTitle().contains("§eKits Creation Main ID:")) {
			if(e.getClickedInventory() == player.getInventory()) return;
			if(e.getSlot() == 5) {
				if(e.getCursor().getType() == Material.AIR || e.getCursor().getType() == null) {
					e.setCancelled(true);
					return;
				}
				e.setCancelled(true);
				e.getInventory().setItem(5, e.getCursor());
				e.setCursor(new ItemStack(Material.AIR));
				String[] id1 = e.getClickedInventory().getName().split("ID: ");
				int id = Integer.parseInt(id1[1]);
				HgMain.Main.FileKitsConfig.set(id + ".SelectorItem", e.getInventory().getItem(5));
				HgMain.saveKitsData();
				return;
			}
			e.setCancelled(true);
			int slot = e.getSlot();
			if(slot == 3) {
				String[] id = e.getClickedInventory().getName().split("ID: ");
				KitCreationLevel(id[1], player, 1);
			}
			if(slot == 1) {
				String ids = e.getView().getTopInventory().getTitle();
				String idss[] = ids.split("ID: ");
				int id = Integer.parseInt(idss[1]);
				KitChangename.put(player, id);
				player.sendMessage(id + "");
				player.sendMessage("§7[§eKit Creation§7] §cType the new name of the kit in chat! (With & for colors)");
				player.closeInventory();
			}
			if(slot == 10) {
				String ids = e.getView().getTopInventory().getTitle();
				String idss[] = ids.split("ID: ");
				int id = Integer.parseInt(idss[1]);
				player.sendMessage(id + "");
				KitChangedes.put(player, id);
				player.sendMessage("§7[§eKit Creation§7] §cType the new description of the kit in chat! (With & for colors)");
				player.closeInventory();
			}
			if(slot == 11) {
				String[] id = e.getClickedInventory().getName().split("ID: ");
				KitCreationLevel(id[1], player, 2);
			}
			if(slot == 12) {
				String[] id = e.getClickedInventory().getName().split("ID: ");
				KitCreationLevel(id[1], player, 3);
			}
			if(slot == 13) {
				String[] id = e.getClickedInventory().getName().split("ID: ");
				KitCreationLevel(id[1], player, 4);
			}
			if(slot == 14) {
				String[] id = e.getClickedInventory().getName().split("ID: ");
				KitCreationLevel(id[1], player, 5);
			}
			if(slot == 15) {
				String[] id = e.getClickedInventory().getName().split("ID: ");
				KitCreationLevel(id[1], player, 6);
			}
			
		}
		if (e.getView().getTopInventory().getTitle().contains("§eKits Creation Level 1 ID: ")) {
			int s = e.getSlot();
			if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
				return;
			}
			int id = 0;
			if(s == 3 || s == 5) {
				String ids = e.getView().getTopInventory().getTitle();
				String idss[] = ids.split("ID: ");
				id = Integer.parseInt(idss[1]);
			}
			if(s == 3) {
				MainKitInv(id, (Player) e.getWhoClicked());
			}
			
			if(s == 5) {
				e.getWhoClicked().sendMessage("§7[§eKit Creation§7] §cLevel 1 saved!");
				SaveKitsLevel(1, id, (Player) e.getWhoClicked(), e.getInventory());
				MainKitInv(id, (Player) e.getWhoClicked());
			}
			if(s == 19 || s == 20 || s == 21 || s == 22 || s >= 36 && s <= 54) {
				if(e.getCursor().getType() == Material.AIR || e.getCursor().getType() == null) {
					e.setCancelled(true);
					return;
				}
				if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
					return;
				}
				if(e.getCurrentItem().getType() == Material.BARRIER) {
					if(e.getInventory().getItem(e.getSlot()).getType() == Material.AIR) {
						e.setCancelled(true);
					}
				}
				if(e.getInventory().getItem(e.getSlot()).getType() == Material.BARRIER) {
					e.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				}
				return;
			}
			e.setCancelled(true);
		}
		if (e.getView().getTopInventory().getTitle().contains("§eKits Creation Level 2 ID: ")) {
			int s = e.getSlot();
			if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
				return;
			}
			int id = 0;
			if(s == 3 || s == 5) {
				String ids = e.getView().getTopInventory().getTitle();
				String idss[] = ids.split("ID: ");
				id = Integer.parseInt(idss[1]);
			}
			if(s == 3) {
				MainKitInv(id, player);
			}
			
			if(s == 5) {
				e.getWhoClicked().sendMessage("§7[§eKit Creation§7] §cLevel 2 saved!");
				SaveKitsLevel(2, id, (Player) e.getWhoClicked(), e.getInventory());
				MainKitInv(id, (Player) e.getWhoClicked());
			}
			if(s == 19 || s == 20 || s == 21 || s == 22 || s >= 36 && s <= 54) {
				if(e.getCursor().getType() == Material.AIR || e.getCursor().getType() == null) {
					e.setCancelled(true);
					return;
				}
				if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
					return;
				}
				if(e.getCurrentItem().getType() == Material.BARRIER) {
					if(e.getInventory().getItem(e.getSlot()).getType() == Material.AIR) {
						e.setCancelled(true);
					}
				}
				if(e.getInventory().getItem(e.getSlot()).getType() == Material.BARRIER) {
					e.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				}
				return;
			}
			e.setCancelled(true);
		}
		if (e.getView().getTopInventory().getTitle().contains("§eKits Creation Level 3 ID: ")) {
			int s = e.getSlot();
			if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
				return;
			}
			int id = 0;
			if(s == 3 || s == 5) {
				String ids = e.getView().getTopInventory().getTitle();
				String idss[] = ids.split("ID: ");
				id = Integer.parseInt(idss[1]);
			}
			if(s == 3) {
				MainKitInv(id, player);
			}
			
			if(s == 5) {
				e.getWhoClicked().sendMessage("§7[§eKit Creation§7] §cLevel 3 saved!");
				SaveKitsLevel(3, id, (Player) e.getWhoClicked(), e.getInventory());
				MainKitInv(id, (Player) e.getWhoClicked());
			}
			if(s == 19 || s == 20 || s == 21 || s == 22 || s >= 36 && s <= 54) {
				if(e.getCursor().getType() == Material.AIR || e.getCursor().getType() == null) {
					e.setCancelled(true);
					return;
				}
				if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
					return;
				}
				if(e.getCurrentItem().getType() == Material.BARRIER) {
					if(e.getInventory().getItem(e.getSlot()).getType() == Material.AIR) {
						e.setCancelled(true);
					}
				}
				if(e.getInventory().getItem(e.getSlot()).getType() == Material.BARRIER) {
					e.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				}
				return;
			}
			e.setCancelled(true);
		}
		if (e.getView().getTopInventory().getTitle().contains("§eKits Creation Level 4 ID: ")) {
			int s = e.getSlot();
			if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
				return;
			}
			int id = 0;
			if(s == 3 || s == 5) {
				String ids = e.getView().getTopInventory().getTitle();
				String idss[] = ids.split("ID: ");
				id = Integer.parseInt(idss[1]);
			}
			if(s == 3) {
				MainKitInv(id, player);
			}
			
			if(s == 5) {
				e.getWhoClicked().sendMessage("§7[§eKit Creation§7] §cLevel 4 saved!");
				SaveKitsLevel(4, id, (Player) e.getWhoClicked(), e.getInventory());
				MainKitInv(id, (Player) e.getWhoClicked());
			}
			if(s == 19 || s == 20 || s == 21 || s == 22 || s >= 36 && s <= 54) {
				if(e.getCursor().getType() == Material.AIR || e.getCursor().getType() == null) {
					e.setCancelled(true);
					return;
				}
				if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
					return;
				}
				if(e.getCurrentItem().getType() == Material.BARRIER) {
					if(e.getInventory().getItem(e.getSlot()).getType() == Material.AIR) {
						e.setCancelled(true);
					}
				}
				if(e.getInventory().getItem(e.getSlot()).getType() == Material.BARRIER) {
					e.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				}
				return;
			}
			e.setCancelled(true);
			
			
		}
		if (e.getView().getTopInventory().getTitle().contains("§eKits Creation Level 5 ID: ")) {
			int s = e.getSlot();
			if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
				return;
			}
			int id = 0;
			if(s == 3 || s == 5) {
				String ids = e.getView().getTopInventory().getTitle();
				String idss[] = ids.split("ID: ");
				id = Integer.parseInt(idss[1]);
			}
			if(s == 3) {
				MainKitInv(id, player);
			}
			
			if(s == 5) {
				e.getWhoClicked().sendMessage("§7[§eKit Creation§7] §cLevel 5 saved!");
				SaveKitsLevel(5, id, (Player) e.getWhoClicked(), e.getInventory());
				MainKitInv(id, (Player) e.getWhoClicked());
			}
			if(s == 19 || s == 20 || s == 21 || s == 22 || s >= 36 && s <= 54) {
				if(e.getCursor().getType() == Material.AIR || e.getCursor().getType() == null) {
					e.setCancelled(true);
					return;
				}
				if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
					return;
				}
				if(e.getCurrentItem().getType() == Material.BARRIER) {
					if(e.getInventory().getItem(e.getSlot()).getType() == Material.AIR) {
						e.setCancelled(true);
					}
				}
				if(e.getInventory().getItem(e.getSlot()).getType() == Material.BARRIER) {
					e.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				}
				return;
			}
			e.setCancelled(true);
		}
		if (e.getView().getTopInventory().getTitle().contains("§eKits Creation Level 6 ID: ")) {
			int s = e.getSlot();
			if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
				return;
			}
			int id = 0;
			if(s == 3 || s == 5) {
				String ids = e.getView().getTopInventory().getTitle();
				String idss[] = ids.split("ID: ");
				id = Integer.parseInt(idss[1]);
			}
			if(s == 3) {
				MainKitInv(id, player);
			}
			
			if(s == 5) {
				e.getWhoClicked().sendMessage("§7[§eKit Creation§7] §cLevel 6 saved!");
				SaveKitsLevel(6, id, (Player) e.getWhoClicked(), e.getInventory());
				MainKitInv(id, (Player) e.getWhoClicked());
			}
			if(s == 19 || s == 20 || s == 21 || s == 22 || s >= 36 && s <= 54) {
				if(e.getCursor().getType() == Material.AIR || e.getCursor().getType() == null) {
					e.setCancelled(true);
					return;
				}
				if(e.getClickedInventory() == e.getWhoClicked().getInventory()) {
					return;
				}
				if(e.getCurrentItem().getType() == Material.BARRIER) {
					if(e.getInventory().getItem(e.getSlot()).getType() == Material.AIR) {
						e.setCancelled(true);
					}
				}
				if(e.getInventory().getItem(e.getSlot()).getType() == Material.BARRIER) {
					e.getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
				}
				return;
			}
			e.setCancelled(true);
			
			
		}
	}
	@EventHandler
	public void ChatEvent(PlayerChatEvent e) {
		if(KitCreate1.containsKey(e.getPlayer())) {
			String message = e.getMessage().replaceAll("&", "§");
			e.setCancelled(true);
			KitCreate1.remove(e.getPlayer());
			e.getPlayer().sendMessage(message + " §cset!");
			int id = HgMain.GetInt("KitsID") + 1;
			HgMain.SetConfig("KitsID", id);
			HgMain.Main.FileKitsConfig.set(id + ".Name", message);
			HgMain.Main.FileKitsConfig.set(id + ".KitDescription", "None");
			ItemStack is = new ItemStack(Material.BARRIER);
			ItemMeta im = is.getItemMeta();
			im.setDisplayName("Selector Item");
			is.setItemMeta(im);
			HgMain.Main.FileKitsConfig.set(id + ".SelectorItem", is);
			HgMain.Main.FileKitsConfig.set(id + ".Prices.Level1", 1);
			HgMain.Main.FileKitsConfig.set(id + ".Prices.Level2", 2);
			HgMain.Main.FileKitsConfig.set(id + ".Prices.Level3", 3);
			HgMain.Main.FileKitsConfig.set(id + ".Prices.Level4", 4);
			HgMain.Main.FileKitsConfig.set(id + ".Prices.Level5", 5);
			HgMain.Main.FileKitsConfig.set(id + ".Prices.Level5", 6);
			HgMain.saveKitsData();
			MainKitInv(id, e.getPlayer());
			
		}
		if(KitChangename.containsKey(e.getPlayer())) {
			String message = e.getMessage().replaceAll("&", "§");
			e.setCancelled(true);
			e.getPlayer().sendMessage(message + " §chas been set to the new name of the kit!");
			int id = KitChangename.get(e.getPlayer());
			KitChangename.remove(e.getPlayer());
			HgMain.Main.FileKitsConfig.set(id + ".Name", message);
			HgMain.saveKitsData();
			MainKitInv(id, e.getPlayer());
			
		}
		if(KitChangedes.containsKey(e.getPlayer())) {
			String message = e.getMessage().replaceAll("&", "§");
			e.setCancelled(true);
			e.getPlayer().sendMessage(message + " §chas been set to the new description of the kit!");
			int id = KitChangedes.get(e.getPlayer());
			HgMain.Main.FileKitsConfig.set(id + ".KitDescription", message);
			HgMain.saveKitsData();
			MainKitInv(id, e.getPlayer());
			KitChangedes.remove(e.getPlayer());
		}
		
	}
	
	public void MainKitInv(int id, Player p) {
		p.closeInventory();
		Inventory inv = Bukkit.getServer().createInventory(null, 27, "§eKits Creation Main ID: " + id);
		ItemStack is1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
		ItemMeta im1 = is1.getItemMeta();
		im1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im1.setDisplayName("§e ");
		is1.setItemMeta(im1);
		inv.setItem(0, is1);
		inv.setItem(8, is1);
		inv.setItem(9, is1);
		inv.setItem(17, is1);
		for(int i = 0; i < 10; i++) {
			inv.setItem(17 + i, is1);
		}
		ArrayList<String> lore = new ArrayList<String>();
		
		is1 = new ItemStack(Material.NAME_TAG);
		im1.setDisplayName("§7Change Kit Name");
		lore.add("§8Kit Name: §e" + HgMain.Main.FileKitsConfig.getString(id + ".Name"));
		im1.setLore(lore);
		lore.clear();
		is1.setItemMeta(im1);
		inv.setItem(1, is1);
		im1.setDisplayName("§7Change Kit Description");
		lore.add("§8Kit Description: §e" + HgMain.Main.FileKitsConfig.getString(id + ".KitDescription"));
		im1.setLore(lore);
		lore.clear();
		is1.setItemMeta(im1);
		inv.setItem(10, is1);
		im1.setLore(null);
		
		is1.setType(Material.STICK);
		im1.setDisplayName("§7Change Kit Level 1");
		is1.setItemMeta(im1);
		inv.setItem(3, is1);
		im1.setLore(null);
		
		is1.setType(Material.GOLD_INGOT);
		im1.setDisplayName("§7Change Prices");
		lore.add("§8Prices:");
		lore.add("§8 Level 1: §e" + HgMain.Main.FileKitsConfig.getString(id + ".Prices.Level1"));
		lore.add("§8 Level 2: §e" + HgMain.Main.FileKitsConfig.getString(id + ".Prices.Level2"));
		lore.add("§8 Level 3: §e" + HgMain.Main.FileKitsConfig.getString(id + ".Prices.Level3"));
		lore.add("§8 Level 4: §e" + HgMain.Main.FileKitsConfig.getString(id + ".Prices.Level4"));
		lore.add("§8 Level 5: §e" + HgMain.Main.FileKitsConfig.getString(id + ".Prices.Level5"));
		im1.setLore(lore);
		lore.clear();
		is1.setItemMeta(im1);
		inv.setItem(7, is1);
		im1.setLore(null);
		
		is1.setType(Material.GOLD_SWORD);
		im1.setDisplayName("§7Change Kit Level 2");
		is1.setItemMeta(im1);
		inv.setItem(11, is1);
		im1.setLore(null);
		
		is1.setType(Material.WOOD_SWORD);
		im1.setDisplayName("§7Change Kit Level 3");
		is1.setItemMeta(im1);
		inv.setItem(12, is1);
		
		is1.setType(Material.STONE_SWORD);
		im1.setDisplayName("§7Change Kit Level 4");
		is1.setItemMeta(im1);
		inv.setItem(13, is1);
		
		is1.setType(Material.IRON_SWORD);
		im1.setDisplayName("§7Change Kit Level 5");
		is1.setItemMeta(im1);
		inv.setItem(14, is1);
		
		is1.setType(Material.DIAMOND_SWORD);
		im1.setDisplayName("§7Change Kit Level 6");
		is1.setItemMeta(im1);
		inv.setItem(15, is1);
		
		is1.setType(Material.BOOK);
		im1.setDisplayName("§7Challenges");
		is1.setItemMeta(im1);
		inv.setItem(16, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Selector Item");
		is1.setItemMeta(im1);
		inv.setItem(5, is1);
		ItemStack Item = HgMain.Main.FileKitsConfig.getItemStack(id + ".SelectorItem");
		ItemStack is = new ItemStack(Material.BARRIER);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Selector Item");
		is.setItemMeta(im);
		if(Item == is) { 
			p.openInventory(inv); 
			return;
		}
		im = Item.getItemMeta();
		im.setDisplayName("§7Selector Item");
		Item.setItemMeta(im);
		inv.setItem(5, Item);
		
		p.openInventory(inv);
	}
	
	public void SaveKitsLevel(int Level, int id, Player p, Inventory inv) {
		ItemStack is = new ItemStack(Material.AIR);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("Empty");
		is.setItemMeta(im);
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Helmet", inv.getItem(19));
		if(inv.getItem(19).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Helmet", is);
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Chestplate", inv.getItem(20));
		if(inv.getItem(20).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Chestplate", is);
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Leggings", inv.getItem(21));
		if(inv.getItem(21).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Leggings", is);
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Boots", inv.getItem(22));
		if(inv.getItem(22).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Boots", is);
		HgMain.saveKitsData();
		//HotBar Numbers
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar1", inv.getItem(37));
		if(inv.getItem(37).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar1", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar2", inv.getItem(38));
		if(inv.getItem(38).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar2", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar3", inv.getItem(39));
		if(inv.getItem(39).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar3", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar4", inv.getItem(40));
		if(inv.getItem(40).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar4", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar5", inv.getItem(41));
		if(inv.getItem(41).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar5", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar6", inv.getItem(42));
		if(inv.getItem(42).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar6", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar7", inv.getItem(43));
		if(inv.getItem(43).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar7", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar8", inv.getItem(44));
		if(inv.getItem(44).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar8", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar9", inv.getItem(45));
		if(inv.getItem(45).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".Hotbar9", is);
		HgMain.saveKitsData();
		//Extra Loot
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot1", inv.getItem(46));
		if(inv.getItem(46).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot1", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot2", inv.getItem(47));
		if(inv.getItem(47).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot2", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot3", inv.getItem(48));
		if(inv.getItem(48).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot3", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot4", inv.getItem(49));
		if(inv.getItem(49).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot4", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot5", inv.getItem(50));
		if(inv.getItem(50).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot5", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot6", inv.getItem(51));
		if(inv.getItem(51).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot6", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot7", inv.getItem(52));
		if(inv.getItem(52).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot7", is);
		HgMain.saveKitsData();
		
		HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot8", inv.getItem(53));
		if(inv.getItem(53).getType() == Material.BARRIER) HgMain.Main.FileKitsConfig.set(id + ".Items.Level" + Level + ".ExtraLoot8", is);
		HgMain.saveKitsData();
	}
	
	public void KitCreationLevel(String id, Player p, int level) {
		Inventory inv = Bukkit.getServer().createInventory(null, 54, "§eKits Creation Level " + level + " ID: " + id);
		ItemStack is1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
		ItemMeta im1 = is1.getItemMeta();
		im1.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		im1.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		im1.setDisplayName("§e ");
		is1.setItemMeta(im1);
		inv.setItem(0, is1);
		inv.setItem(8, is1);
		for(int i = 0; i < 10; i++) {
			inv.setItem(9 + i, is1);
		}
		is1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		inv.setItem(1, is1);
		inv.setItem(2, is1);
		inv.setItem(3, is1);
		inv.setItem(7, is1);
		inv.setItem(5, is1);
		inv.setItem(6, is1);
		inv.setItem(4, is1);
		for(int i = 0; i < 13; i++) {
			inv.setItem(23 + i, is1);
		}
		is1 = new ItemStack(Material.BARRIER);
		im1.setDisplayName("§cBack");
		is1.setItemMeta(im1);
		inv.setItem(3, is1);
		
		is1 = new ItemStack(Material.CHEST);
		im1.setDisplayName("§eSave");
		is1.setItemMeta(im1);
		inv.setItem(5, is1);
		
		is1 = new ItemStack(Material.IRON_CHESTPLATE);
		im1.setDisplayName("§7Armor");
		is1.setItemMeta(im1);
		inv.setItem(18, is1);
		
		is1 = new ItemStack(Material.BOOK);
		im1.setDisplayName("§7Loot");
		is1.setItemMeta(im1);
		inv.setItem(36, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Helmet");
		is1.setItemMeta(im1);
		inv.setItem(19, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Chestplate");
		is1.setItemMeta(im1);
		inv.setItem(20, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Leggings");
		is1.setItemMeta(im1);
		inv.setItem(21, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Boots");
		is1.setItemMeta(im1);
		inv.setItem(22, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 1");
		is1.setItemMeta(im1);
		inv.setItem(37, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 2");
		is1.setItemMeta(im1);
		inv.setItem(38, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 3");
		is1.setItemMeta(im1);
		inv.setItem(39, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 4");
		is1.setItemMeta(im1);
		inv.setItem(40, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 5");
		is1.setItemMeta(im1);
		inv.setItem(41, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 6");
		is1.setItemMeta(im1);
		inv.setItem(42, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 7");
		is1.setItemMeta(im1);
		inv.setItem(43, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 8");
		is1.setItemMeta(im1);
		inv.setItem(44, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7HotBar Slot 9");
		is1.setItemMeta(im1);
		inv.setItem(45, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Extra Loot");
		is1.setItemMeta(im1);
		inv.setItem(46, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Extra Loot");
		is1.setItemMeta(im1);
		inv.setItem(47, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Extra Loot");
		is1.setItemMeta(im1);
		inv.setItem(48, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Extra Loot");
		is1.setItemMeta(im1);
		inv.setItem(49, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Extra Loot");
		is1.setItemMeta(im1);
		inv.setItem(50, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Extra Loot");
		is1.setItemMeta(im1);
		inv.setItem(51, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Extra Loot");
		is1.setItemMeta(im1);
		inv.setItem(52, is1);
		
		is1.setType(Material.BARRIER);
		im1.setDisplayName("§7Extra Loot");
		is1.setItemMeta(im1);
		inv.setItem(53, is1);
		
		p.openInventory(inv);
	}

}
