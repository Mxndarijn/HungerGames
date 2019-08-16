package me.Miracles.Hg;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;


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

}
