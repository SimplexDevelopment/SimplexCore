package io.github.simplexdev.simplexcore;

import io.github.simplexdev.simplexcore.command.CommandLoader;
import io.github.simplexdev.simplexcore.command.defaults.Command_info;
import io.github.simplexdev.simplexcore.config.Yaml;
import io.github.simplexdev.simplexcore.config.YamlFactory;
import io.github.simplexdev.simplexcore.plugin.AddonRegistry;
import io.github.simplexdev.simplexcore.plugin.DependencyManagement;
import io.github.simplexdev.simplexcore.task.Announcer;
import io.github.simplexdev.simplexcore.listener.DependencyListener;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.plugin.SimplexAddon;
import io.github.simplexdev.simplexcore.utils.TimeValues;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitScheduler;

import java.io.File;
import java.util.logging.Logger;

public final class SimplexCorePlugin extends SimplexAddon<SimplexCorePlugin> {
    protected static boolean debug = false;
    protected static boolean suspended = false;
    private DependencyManagement dpm;
    private Yaml config;
    private TimeValues time;
    private Yaml internals;

    protected static SimplexCorePlugin instance; // = getPlugin(SimplexCorePlugin.class);
    public static SimplexCorePlugin getInstance() {
        return instance;
    }

    @Override
    public SimplexCorePlugin getPlugin() {
        return this;
    }

    @Override
    public void init() {
        instance = this;
        this.dpm = new DependencyManagement();
        this.config = new YamlFactory(this).setDefaultPathways();
        this.time = new TimeValues();
        this.internals = new YamlFactory(this).from("internals.yml", getParentFolder(), "internals.yml");
    }

    @Override
    public void start() {
        try {
            getRegistry().register(this);
            getCommandLoader().classpath(Command_info.class).load(this);
            getYamlConfig().reload();
            getInternals().reload();
            //
            SimplexListener.register(new DependencyListener(), this);
            new Announcer();
        } catch (Exception ex) {
            suspended = true;
            // TODO: Write an output to a file with why it suspended.
        }

        CoreState state = new CoreState();
        getLogger().info(state.getMessage());
    }

    @Override
    public void stop() {

    }

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean enable) {
        debug = enable;
    }

    public static boolean isSuspended() {
        return suspended;
    }

    public Logger getLogger() {
        return this.getServer().getLogger();
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

    public synchronized AddonRegistry getRegistry() {
        return AddonRegistry.getInstance();
    }

    public synchronized CommandLoader getCommandLoader() {
        return CommandLoader.getInstance();
    }

    public DependencyManagement getDependencyManager() {
        return dpm;
    }

    public TimeValues getTimeValues() {
        return time;
    }

    public Yaml getYamlConfig() {
        return config;
    }

    public Yaml getInternals() { return internals; }
}