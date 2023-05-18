package dev.skyphi.models;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Clutcher {
    
    public static final ItemStack[] items = {
        new ItemStack(Material.WATER_BUCKET),
        new ItemStack(Material.HAY_BLOCK, 16),
        new ItemStack(Material.DARK_OAK_BOAT),
        new ItemStack(Material.SLIME_BLOCK, 16),
        new ItemStack(Material.LADDER, 16),
        new ItemStack(Material.RED_BED),
        new ItemStack(Material.SCAFFOLDING, 64),
        new ItemStack(Material.POWDER_SNOW_BUCKET),
        new ItemStack(Material.RED_CONCRETE)
    };

    public Player player;
    public Location spawn;
    private ArrayList<Block> blocks = new ArrayList<Block>();

    public Clutcher(Player player) {
        this.player = player;
        this.spawn = player.getLocation();
        giveItems();
    }

    public void reset() {
        clearBlocks();
        removeBoats();
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.teleport(spawn);
        giveItems();
    }

    public void giveItems() {
        Inventory inv = player.getInventory();
        inv.clear();
        for(ItemStack item : items) inv.addItem(item);
    }

    public void addBlock(Block block) {
        blocks.add(block);
    }

    private void clearBlocks() {
        for(Block block : blocks) block.setType(Material.AIR);
        blocks.clear();
    }

    private void removeBoats() {
        List<Entity> entities = player.getNearbyEntities(10, 10, 10);
        for(Entity e : entities) {
            if(e.getType() == EntityType.BOAT) e.remove();
        }
    }

}
