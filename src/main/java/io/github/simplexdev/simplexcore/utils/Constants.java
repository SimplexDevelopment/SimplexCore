package io.github.simplexdev.simplexcore.utils;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.command.CommandLoader;
import io.github.simplexdev.simplexcore.config.Yaml;
import io.github.simplexdev.simplexcore.config.YamlFactory;
import io.github.simplexdev.simplexcore.plugin.AddonRegistry;
import io.github.simplexdev.simplexcore.plugin.DependencyManagement;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

public final class Constants {
    private static final SimplexCorePlugin plugin = JavaPlugin.getPlugin(SimplexCorePlugin.class);
    private static final Server server = plugin.getServer();
    private static final Logger logger = plugin.getLogger();
    private static final PluginManager manager = server.getPluginManager();
    private static final BukkitScheduler scheduler = server.getScheduler();

    public static SimplexCorePlugin getPlugin() {
        return plugin;
    }

    public static Server getServer() {
        return server;
    }

    public static Logger getLogger() {
        return logger;
    }

    public static PluginManager getManager() {
        return manager;
    }

    public static BukkitScheduler getScheduler() {
        return scheduler;
    }
}

