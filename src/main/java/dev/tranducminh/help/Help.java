package dev.tranducminh.help;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.List;

public class Help extends JavaPlugin implements Listener {
    private FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
        getCommand("help").setExecutor((sender, command, label, args) -> {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command is for players only.");
                return true;
            }
            Player player = (Player) sender;
            getConfig().getList("help").forEach(b -> player.sendMessage(ChatColor.translateAlternateColorCodes('&', (String) b)));
            return true;
        });

        getCommand("help").setTabCompleter(new TabCompleter() {
            @Override
            public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
                return Collections.emptyList();
            }
        });
    }
}