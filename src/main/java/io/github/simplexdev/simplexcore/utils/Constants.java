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

import java.io.File;
import java.util.logging.Logger;

public final class Constants {
    private static final SimplexCorePlugin plugin = JavaPlugin.getPlugin(SimplexCorePlugin.class);

    public static SimplexCorePlugin getPlugin() {
        return plugin;
    }

    public static Server getServer() {
        return plugin.getServer();
    }

    public static Logger getLogger() {
        return plugin.getServer().getLogger();
    }

    public static PluginManager getManager() {
        return plugin.getServer().getPluginManager();
    }

    public static BukkitScheduler getScheduler() {
        return plugin.getServer().getScheduler();
    }

    public static File getDataFolder() {
        return plugin.getDataFolder();
    }
}

