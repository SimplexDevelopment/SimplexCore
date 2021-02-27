package io.github.simplexdev.simplexcore;

import io.github.simplexdev.simplexcore.command.defaults.Command_info;
import io.github.simplexdev.simplexcore.concurrent.Announcer;
import io.github.simplexdev.simplexcore.listener.DependencyListener;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.plugin.SimplexAddon;
import io.github.simplexdev.simplexcore.utils.Constants;
import io.github.simplexdev.simplexcore.utils.Instances;

public final class SimplexCorePlugin extends SimplexAddon<SimplexCorePlugin> {
    protected static boolean debug = false;
    protected static boolean suspended = false;

    protected Instances instances;

    @Override
    public SimplexCorePlugin getPlugin() {
        return this;
    }

    @Override
    public void init() {
        instances = new Instances();
    }

    @Override
    public void start() {
        try {
            instances.getRegistry().register(this);
            instances.getCommandLoader().classpath(Command_info.class).load();
            instances.getConfig().reload();
            //
            SimplexListener.register(new DependencyListener(), this);
            new Announcer();
        } catch (Exception ex) {
            suspended = true;
            // TODO: Write an output to a file with why it suspended.
        }

        CoreState state = new CoreState();
        Constants.getLogger().info(state.getMessage());
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

    public final Instances getInstances() {
        return instances;
    }
}