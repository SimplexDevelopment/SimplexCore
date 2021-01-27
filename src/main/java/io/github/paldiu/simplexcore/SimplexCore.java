package io.github.paldiu.simplexcore;

import io.github.paldiu.simplexcore.command.defaults.Command_info;
import io.github.paldiu.simplexcore.plugin.Addon;
import io.github.paldiu.simplexcore.utils.Constants;

public final class SimplexCore extends Addon<SimplexCore> {
    protected SimplexCore(SimplexCore plugin) {
        super(plugin);
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