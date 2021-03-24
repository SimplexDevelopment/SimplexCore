package io.github.simplexdev.simplexcore;

import io.github.simplexdev.simplexcore.command.defaults.Command_info;
import io.github.simplexdev.simplexcore.config.Yaml;
import io.github.simplexdev.simplexcore.config.YamlFactory;
import io.github.simplexdev.simplexcore.module.DependencyManagement;
import io.github.simplexdev.simplexcore.task.Announcer;
import io.github.simplexdev.simplexcore.listener.DependencyListener;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.module.SimplexModule;

public final class SimplexCorePlugin extends SimplexModule<SimplexCorePlugin> {
    private static boolean debug = false;
    private static boolean suspended = false;
    private static SimplexCorePlugin instance;
    private DependencyManagement dpm;
    private Yaml config;
    private Yaml internals;

    public static SimplexCorePlugin getInstance() {
        return instance;
    }

    @Override
    public SimplexCorePlugin getPlugin() {
        return this;
    }

    @Override
    public void init() {
        SimplexCorePlugin.instance = this;
        this.dpm = new DependencyManagement();
        this.config = new YamlFactory(this).setDefaultPathways();
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

        CoreState state = new CoreState(this);
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

    public DependencyManagement getDependencyManager() {
        return dpm;
    }

    public Yaml getYamlConfig() {
        return config;
    }

    public Yaml getInternals() { return internals; }
}