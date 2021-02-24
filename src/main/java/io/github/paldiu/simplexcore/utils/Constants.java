package io.github.paldiu.simplexcore.utils;

import io.github.paldiu.simplexcore.SimplexCore;
import io.github.paldiu.simplexcore.command.CommandLoader;
import io.github.paldiu.simplexcore.config.Yaml;
import io.github.paldiu.simplexcore.config.YamlFactory;
import io.github.paldiu.simplexcore.plugin.AddonRegistry;
import io.github.paldiu.simplexcore.plugin.DependencyManagement;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.logging.Logger;

public final class Constants {
    private static final SimplexCore plugin = JavaPlugin.getPlugin(SimplexCore.class);
    private static final Server server = plugin.getServer();
    private static final Logger logger = plugin.getLogger();
    private static final PluginManager manager = server.getPluginManager();
    private static final BukkitScheduler scheduler = server.getScheduler();
    private static final DependencyManagement dpm = new DependencyManagement();
    private static final Yaml config = new YamlFactory(plugin).setDefaultPathways();
    private static final TimeValues time = new TimeValues();
    // Utility class should not be instantiated.
    private Constants() {
        throw new AssertionError();
    }

    public static SimplexCore getPlugin() {
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

    public static synchronized AddonRegistry getRegistry() {
        return AddonRegistry.getInstance();
    }

    public static synchronized CommandLoader getCommandLoader() {
        return CommandLoader.getInstance();
    }

    public static DependencyManagement getDependencyManager() {
        return dpm;
    }

    public static TimeValues getTimeValues() {
        return time;
    }

    public static Yaml getConfig() {
        return config;
    }

    public static class TimeValues {
        public long SECOND() {
            return 20L;
        }

        public long MINUTE() {
            return 1200L;
        }

        public long HOUR() {
            return 72000L;
        }

        public long DAY() {
            return 1728000L;
        }

        public long MONTH() {
            return 51840000L;
        }

        public long YEAR() {
            return 622080000L;
        }
    }
}

