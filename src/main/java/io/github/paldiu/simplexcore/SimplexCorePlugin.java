package io.github.paldiu.simplexcore;

import io.github.paldiu.simplexcore.command.defaults.Command_info;
import io.github.paldiu.simplexcore.concurrent.Announcer;
import io.github.paldiu.simplexcore.listener.DependencyListener;
import io.github.paldiu.simplexcore.listener.SimplexListener;
import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Constants;

// This comment was added to force a test on Jenkins AutoBuild
// https://jenkins.coomware.dev/job/SimplexCore
public final class SimplexCorePlugin extends SimplexAddon<SimplexCorePlugin> {
    protected static boolean debug = false;
    protected static boolean suspended = false;

    public static boolean isDebug() {
        return debug;
    }

    public static void setDebug(boolean enable) {
        debug = enable;
    }

    public static boolean isSuspended() {
        return suspended;
    }

    @Override
    public SimplexCorePlugin getPlugin() {
        return this;
    }

    @Override
    public void start() {
        try {
            Constants.getRegistry().register(this);
            Constants.getCommandLoader().classpath(Command_info.class).load();
            Constants.getConfig().reload();
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
}