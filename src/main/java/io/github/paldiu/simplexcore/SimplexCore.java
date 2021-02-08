package io.github.paldiu.simplexcore;

import io.github.paldiu.simplexcore.command.defaults.Command_info;
import io.github.paldiu.simplexcore.future.Announcer;
import io.github.paldiu.simplexcore.listener.ServerPluginListener;
import io.github.paldiu.simplexcore.listener.SimplexListener;
import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Constants;

public final class SimplexCore extends SimplexAddon<SimplexCore> {
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
    public SimplexCore getPlugin() {
        return this;
    }

    @Override
    public void start() {
        try {
            Constants.getRegistry().register(this);
            Constants.getCommandLoader().classpath(Command_info.class).load();
            SimplexListener.register(new ServerPluginListener(), this);
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