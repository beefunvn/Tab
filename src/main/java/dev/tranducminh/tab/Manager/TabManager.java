package dev.tranducminh.tab.Manager;

import dev.tranducminh.tab.Tab;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TabManager {
    private final Tab plugin;

    private List<String> header;

    private List<String> footer;

    public TabManager(Tab plugin) {
        this.plugin = plugin;
        this.header = plugin.getConfig().getStringList("header");
        this.footer = plugin.getConfig().getStringList("footer");
    }

    public void updateTabListForAllPlayers() {
        for (Player player : Bukkit.getOnlinePlayers())
            updateTabList(player);
    }

    public void updateTabList(Player player) {
        String headerString = getHeaderFooterString(this.header);
        String footerString = getHeaderFooterString(this.footer);
        String updatedHeader = replacePlaceholders(headerString, player);
        String updatedFooter = replacePlaceholders(footerString, player);
        player.setPlayerListHeaderFooter(updatedHeader, updatedFooter);
    }

    private String getHeaderFooterString(List<String> lines) {
        StringBuilder headerFooterString = new StringBuilder();
        for (String line : lines)
            headerFooterString.append(ChatColor.translateAlternateColorCodes('&', line)).append("\n");
        return headerFooterString.toString();
    }

    private String replacePlaceholders(String input, Player player) {
        input = input.replace("%tps%", String.format("%.2f", new Object[] { Double.valueOf(Bukkit.getServer().getTPS()[0]) }));
        input = input.replace("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()));
        input = input.replace("%ping%", String.valueOf(player.spigot().getPing()));
        return input;
    }
}
