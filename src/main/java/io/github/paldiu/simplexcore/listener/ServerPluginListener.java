package io.github.paldiu.simplexcore.listener;

import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Constants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.PluginEnableEvent;

public final class ServerPluginListener extends SimplexListener {
    @EventHandler
    public void pluginRegister(PluginEnableEvent event) {
        if (SimplexAddon.class.isAssignableFrom(event.getPlugin().getClass())) {
            if (!Constants.getRegistry().getComponents().contains(event.getPlugin())) {
                Constants.getRegistry().getComponents().add((SimplexAddon<?>) event.getPlugin());
            }
        }
    }
}
