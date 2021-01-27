package io.github.paldiu.simplexcore.plugin;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Addon<T extends Addon<T>> extends JavaPlugin {
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
}
