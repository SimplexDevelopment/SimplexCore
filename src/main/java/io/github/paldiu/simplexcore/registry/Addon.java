package io.github.paldiu.simplexcore.registry;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class Addon<T extends Addon<T>> extends JavaPlugin {
    private final T plugin;

    protected Addon(T plugin) {
        this.plugin = plugin;
    }

    /**
     *  Gets your plugin as an addon.
     *
     * @return  The addon.
     */
    public T getPlugin() {
        return plugin;
    }

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
     *  Plugin startup logic.
     */
    public abstract void start();

    /**
     *  Plugin shutdown logic.
     */
    public abstract void stop();

    /**
     * Plugin initialization logic.
     */
    public void init() {
    }
}
