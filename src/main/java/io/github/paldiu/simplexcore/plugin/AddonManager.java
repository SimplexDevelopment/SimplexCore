package io.github.paldiu.simplexcore.plugin;

import io.github.paldiu.simplexcore.utils.Constants;

public final class AddonManager {
    public AddonManager() { }

    public void disable(SimplexAddon<?> simplexAddon) {
        Constants.getManager().disablePlugin(simplexAddon);
    }

    public void enable(SimplexAddon<?> simplexAddon) {
        Constants.getManager().enablePlugin(simplexAddon);
    }

    public void reload(SimplexAddon<?> simplexAddon) {
        disable(simplexAddon);
        enable(simplexAddon);
    }
}
