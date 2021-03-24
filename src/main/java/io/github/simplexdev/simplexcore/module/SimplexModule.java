package io.github.simplexdev.simplexcore.module;

import io.github.simplexdev.simplexcore.command.CommandLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;

/**
 * This class represents a SimplexCore module.
 * You should extend this instead of JavaPlugin if you are using the core as a dependency.
 * @param <T>
 */
public abstract class SimplexModule<T extends SimplexModule<T>> extends JavaPlugin {
    /**
     * Gets your plugin as an addon.
     *
     * @return The addon.
     */
    public abstract T getPlugin();

    @Override
    public void onLoad() {
        init();
    }

    @Override
    public void onEnable() {
        start();
    }

    @Override
    public void onDisable() {
        stop();
    }

    /**
     * Plugin startup logic.
     */
    public abstract void start();

    /**
     * Plugin shutdown logic.
     */
    public abstract void stop();

    /**
     * Plugin initialization logic.
     */
    public void init() {
    }

    public PluginManager getManager() {
        return this.getServer().getPluginManager();
    }

    public BukkitScheduler getScheduler() {
        return this.getServer().getScheduler();
    }

    public File getParentFolder() {
        return this.getDataFolder();
    }

    public synchronized ModuleRegistry getRegistry() {
        return ModuleRegistry.getInstance();
    }

    public synchronized CommandLoader getCommandLoader() {
        return CommandLoader.getInstance();
    }
}
