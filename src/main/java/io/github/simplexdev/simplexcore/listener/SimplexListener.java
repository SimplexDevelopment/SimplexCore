package io.github.simplexdev.simplexcore.listener;

import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public abstract class SimplexListener implements Listener {
    protected final SimplexModule<?> plugin;

    public SimplexListener(@NotNull SimplexModule<?> plugin) {
        this.plugin = plugin;
        plugin.getManager().registerEvents(this, plugin);
    }

    protected final SimplexModule<?> getPlugin() {
        return plugin;
    }
}
