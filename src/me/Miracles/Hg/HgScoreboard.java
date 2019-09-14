package me.Miracles.Hg;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;


public class HgScoreboard {
	
	public static void createscoreboard(Player pla) {
        final ScoreboardManager sbm = Bukkit.getServer().getScoreboardManager();
         final Scoreboard smb = sbm.getNewScoreboard();
         final Objective obj = smb.registerNewObjective("test", "potato");
        obj.setDisplayName("§eHunger Games");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score a = obj.getScore(" §4");
        Score b = obj.getScore(" §ePlayers§f: §f" + HgMain.PlayersInGame + "§e/§f" + HgMain.GetInt("MaxPlayers"));
        Score c = obj.getScore(" §3");
        Score d = obj.getScore(" §eMap§f: §a" + HgMain.GetString("MapName"));
        Score e = obj.getScore(" §1");  
        Score f = obj.getScore(" §eKit§f: §7None §4[]");  
        Score h = obj.getScore(" §2");  
        Score j = obj.getScore(" §eCoins earned");  
        Score i = obj.getScore(" §ethis game§f: 0 §4[]");  
        Score g = obj.getScore("§eplay.Merijn<3I.nl");  
        a.setScore(10);
        b.setScore(7);
        c.setScore(8);
        d.setScore(9);
        e.setScore(6);
        f.setScore(5);
        g.setScore(1);
        h.setScore(4);
        i.setScore(2);
        j.setScore(3);
        pla.setScoreboard(smb);
    }
	public static void createscoreboardp(Player p) {
		Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = board.registerNewObjective("dummy", "Test Server");
        obj.setDisplayName("§eHunger Games");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score a = obj.getScore(" §4");
        a.setScore(15);
        
        Score b = obj.getScore(" §3");
        b.setScore(13);
        
        Score c = obj.getScore(" §1");
        c.setScore(11);
        
        Score d = obj.getScore(" §eKit§f: §7None §4[]");
        d.setScore(10);
        
        Score e = obj.getScore(" §2");
        e.setScore(9);
        
        Score f = obj.getScore(" §eCoins earned");
        f.setScore(8);
        
        Score g = obj.getScore(" §ethis game§f: 0 §4[]");
        g.setScore(7);
        
        Score h = obj.getScore("§eplay.ILoveMerijn.nl");
        h.setScore(6);
        
        Team Players = board.registerNewTeam("Players");
        Players.addEntry(ChatColor.BLACK + "" + ChatColor.WHITE);
        Check16(board.getTeam("Players")," §ePlayers§f: §f" + HgMain.PlayersInGame + "§e/§f" + HgMain.GetInt("MaxPlayers"));
        obj.getScore(ChatColor.BLACK + "" + ChatColor.WHITE).setScore(14);
        
        Team Map = board.registerNewTeam("Map");
        Map.addEntry(ChatColor.BLACK + "" + ChatColor.BLACK);
        Check16(board.getTeam("Map")," §eMap§f: §a" + HgMain.GetString("MapName"));
        obj.getScore(ChatColor.BLACK + "" + ChatColor.BLACK).setScore(12);

        p.setScoreboard(board);
		
	}
	public static void updateScoreBoard(Player player) {

        Scoreboard board = player.getScoreboard();
        
        Check16(board.getTeam("Players")," §ePlayers§f: §f" + HgMain.PlayersInGame + "§e/§f" + HgMain.GetInt("MaxPlayers"));
        Check16(board.getTeam("Map")," §eMap§f: §a" + HgMain.GetString("MapName"));
	}
	
	public static void Check16(Team team, String rank) {
	        team.addEntry(ChatColor.AQUA.toString() + ChatColor.DARK_AQUA);

	        // The text doesn't have more than 16 characters, so we are fine
	        if (rank.length() <= 16) {
	            team.setPrefix(rank);
	            return;
	        }
	        // If the text actually goes above 32, cut it to 32 to prevent kicks and errors
	        if (rank.length() > 32) {
	            rank = rank.substring(32);
	        }
	        // Set the prefix to the first 16 characters
	        
	        team.setPrefix(rank.substring(0, 16));
			if(team.getName().equalsIgnoreCase("Map")) {
				team.setPrefix(rank.substring(0, 12));
			}
	        // Now use the last 16 characters and put them into the suffix
	        team.setSuffix(rank.substring(16));
	        
	        if(team.getName().equalsIgnoreCase("Map")) {
	        	team.setSuffix(rank.substring(12));
			}
		
	}
}
