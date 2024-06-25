package dev.tranducminh.tab;

import dev.tranducminh.tab.Listener.PlayerJoinListener;
import dev.tranducminh.tab.Manager.TabManager;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.concurrent.TimeUnit;

public class Tab extends JavaPlugin {
    private TabManager tabManager;

    public void onEnable() {
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        this.tabManager = new TabManager(this);
        getServer().getPluginManager().registerEvents((Listener)new PlayerJoinListener(this.tabManager), (Plugin)this);

        getServer().getAsyncScheduler().runAtFixedRate(this, task -> {
            this.tabManager.updateTabListForAllPlayers();
    }, 1L, 50L, TimeUnit.MILLISECONDS);
}

    public TabManager getTabManager() {
        return this.tabManager;
    }
}
