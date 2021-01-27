package io.github.paldiu.simplexcore.plugin;

import io.github.paldiu.simplexcore.utils.Constants;
import io.github.paldiu.simplexcore.utils.Utilities;

public class AddonManager {
    public AddonManager() { }

    public void disable(Addon<?> addon) {
        Constants.getManager().disablePlugin(addon);
    }

    public void enable(Addon<?> addon) {
        Constants.getManager().enablePlugin(addon);
    }

    public void reload(Addon<?> addon) {
        disable(addon);
        enable(addon);
    }
}
