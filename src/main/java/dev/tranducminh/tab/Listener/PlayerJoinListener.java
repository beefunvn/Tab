package dev.tranducminh.tab.Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import dev.tranducminh.tab.Manager.TabManager;

public class PlayerJoinListener implements Listener {
    private final TabManager tabManager;

    public PlayerJoinListener(TabManager tabManager) {
        this.tabManager = tabManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        this.tabManager.updateTabList(event.getPlayer());
    }
}
