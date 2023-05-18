package dev.skyphi;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import dev.skyphi.models.Clutcher;

public class Events implements Listener {
    
    @EventHandler
    public void onPlayerDeath(EntityDamageEvent event) {
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player)event.getEntity();
        Clutcher clutcher = null;
        for(Clutcher c : App.clutchers) {
            if(c.player.equals(player)) {
                clutcher = c;
                break;
            }
        };
        if(clutcher == null) return;

        if(player.getHealth() - event.getFinalDamage() <= 0) {
            event.setCancelled(true);
            clutcher.reset();
            player.teleport(clutcher.spawn);
        }
    }

    @EventHandler
    public void onClickReset(PlayerInteractEvent event) {
        if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
        Player player = event.getPlayer();
        Clutcher clutcher = null;
        for(Clutcher c : App.clutchers) {
            if(c.player.equals(player)) {
                clutcher = c;
                break;
            }
        };
        if(clutcher == null) return;

        ItemStack item = event.getItem();
        if(item != null && item.equals(Clutcher.items[8])) {
            event.setCancelled(true);
            clutcher.reset();
            player.teleport(clutcher.spawn);
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Clutcher clutcher = null;
        for(Clutcher c : App.clutchers) {
            if(c.player.equals(player)) {
                clutcher = c;
                break;
            }
        };
        if(clutcher == null) return;

        clutcher.addBlock(event.getBlock());
    }

}
