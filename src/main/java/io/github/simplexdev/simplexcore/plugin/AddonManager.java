package io.github.simplexdev.simplexcore.plugin;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;

public final class AddonManager {
    public AddonManager() {
    }

    public void disable(SimplexAddon<?> simplexAddon) {
        SimplexCorePlugin.getInstance().getManager().disablePlugin(simplexAddon);
    }

    public void enable(SimplexAddon<?> simplexAddon) {
        SimplexCorePlugin.getInstance().getManager().enablePlugin(simplexAddon);
    }

    public void reload(SimplexAddon<?> simplexAddon) {
        disable(simplexAddon);
        enable(simplexAddon);
    }
}
