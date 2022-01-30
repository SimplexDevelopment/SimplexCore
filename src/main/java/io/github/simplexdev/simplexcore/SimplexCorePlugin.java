package io.github.simplexdev.simplexcore;

import io.github.simplexdev.api.annotations.ReqType;
import io.github.simplexdev.api.annotations.Requires;
import io.github.simplexdev.simplexcore.command.defaults.Command_info;
import io.github.simplexdev.simplexcore.config.Yaml;
import io.github.simplexdev.simplexcore.config.YamlFactory;
import io.github.simplexdev.simplexcore.module.SimplexModule;

@Requires(ReqType.SPIGOT)
public final class SimplexCorePlugin extends SimplexModule<SimplexCorePlugin> {
    private static boolean debug = false;
    private static boolean suspended = false;
    private Yaml config;
    private Yaml internals;

    private static SimplexCorePlugin instance;

    public static SimplexCorePlugin getInstance() {
        return instance;
    }

    @Override
    public SimplexCorePlugin getPlugin() {
        return this;
    }

    @Override
    public void init() {
        instance = getPlugin();
        config = new YamlFactory(this).setDefaultPathways();
        internals = new YamlFactory(this).from("internals.yml", getParentFolder(), "internals.yml");
    }

    @Override
    public void start() {
        try {
            getRegistry().register(this);
            getCommandLoader().load(this, Command_info.class);
            getYamlConfig().reload();
            getInternals().reload();
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

    public Yaml getYamlConfig() {
        return config;
    }

    public Yaml getInternals() {
        return internals;
    }
}