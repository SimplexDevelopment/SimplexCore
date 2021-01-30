package io.github.paldiu.simplexcore;

import io.github.paldiu.simplexcore.command.defaults.Command_info;
import io.github.paldiu.simplexcore.listener.ServerPluginListener;
import io.github.paldiu.simplexcore.listener.SimplexListener;
import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Constants;

public final class SimplexCore extends SimplexAddon<SimplexCore> {
    @Override
    public SimplexCore getPlugin() {
        return this;
    }

    @Override
    public void start() {
        Constants.getRegistry().register(this);
        Constants.getCommandLoader().classpath(Command_info.class).load();
        SimplexListener.register(new ServerPluginListener(), this);
    }

    @Override
    public void stop() {

    }
}