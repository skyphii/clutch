package dev.skyphi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.skyphi.models.Clutcher;

public class ClutchCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
        if(!(sender instanceof Player)) return false;

        Player player = (Player)sender;

        Clutcher clutcher = null;
        for(Clutcher c : App.clutchers) {
            if(c.player.equals(player)) {
                clutcher = c;
                break;
            }
        }

        if(clutcher == null) {
            App.clutchers.add(new Clutcher(player));
        }else {
            player.getInventory().clear();
            App.clutchers.remove(clutcher);
        }
        
        return true;
    }
    
}
