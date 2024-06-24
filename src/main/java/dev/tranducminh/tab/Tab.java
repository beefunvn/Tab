package dev.tranducminh.tab;

import dev.tranducminh.tab.Listener.PlayerJoinListener;
import dev.tranducminh.tab.Manager.TabManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Tab extends JavaPlugin {
    private TabManager tabManager;

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        this.tabManager = new TabManager(this);
        getServer().getPluginManager().registerEvents((Listener)new PlayerJoinListener(this.tabManager), (Plugin)this);
        BukkitTask task = (new BukkitRunnable() {
            public void run() {
                Tab.this.tabManager.updateTabListForAllPlayers();
            }
        }).runTaskTimer((Plugin)this, 0L, 20L);
    }

    public TabManager getTabManager() {
        return this.tabManager;
    }
}
