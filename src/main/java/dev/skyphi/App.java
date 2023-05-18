package dev.skyphi;

import java.util.ArrayList;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import dev.skyphi.models.Clutcher;

public class App extends JavaPlugin {
    
    public static App instance;

    // private static final String TITLE = 
    //     ChatColor.YELLOW + "" + ChatColor.BOLD + "["
    //     + ChatColor.AQUA + "" + ChatColor.BOLD + "Clutch"
    //     + ChatColor.YELLOW + "" + ChatColor.BOLD + "] ";

    public static ArrayList<Clutcher> clutchers = new ArrayList<Clutcher>();

    @Override
    public void onEnable() {
        instance = this;

        // reset block
        ItemMeta meta = Clutcher.items[8].getItemMeta();
        meta.setDisplayName("§c§lRESET");
        Clutcher.items[8].setItemMeta(meta);

        // events
        getServer().getPluginManager().registerEvents(new Events(), this);

        // commands
        this.getCommand("clutch").setExecutor(new ClutchCommand());
    }

}
