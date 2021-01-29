package io.github.paldiu.simplexcore;

import io.github.paldiu.simplexcore.command.defaults.Command_info;
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
    }

    @Override
    public void stop() {

    }
}