package io.github.simplexdev.simplexcore.module;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;

public final class ModuleManager {
    public ModuleManager() {
    }

    public void disable(SimplexModule<?> simplexModule) {
        SimplexCorePlugin.getInstance().getManager().disablePlugin(simplexModule);
    }

    public void enable(SimplexModule<?> simplexModule) {
        SimplexCorePlugin.getInstance().getManager().enablePlugin(simplexModule);
    }

    public void reload(SimplexModule<?> simplexModule) {
        disable(simplexModule);
        enable(simplexModule);
    }
}
