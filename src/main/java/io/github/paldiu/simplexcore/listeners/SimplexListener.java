package io.github.paldiu.simplexcore.listeners;

import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Constants;
import io.github.paldiu.simplexcore.utils.Utilities;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;

public abstract class SimplexListener implements Listener {
    @EventHandler
    public void pluginRegister(PluginEnableEvent event) {
        if (SimplexAddon.class.isAssignableFrom(event.getPlugin().getClass())) {
            if (!Constants.getRegistry().getComponents().contains(event.getPlugin())) {
                Constants.getRegistry().getComponents().add((SimplexAddon<?>) event.getPlugin());
            }
        }
    }

    public static <T extends SimplexListener> void registerAll(T[] sl, SimplexAddon<?> plugin) {
        Utilities.primitiveFE(sl, action -> {
            SimplexListener.register(action, plugin);
        });
    }

    public static <T extends SimplexListener> void register(T listener, SimplexAddon<?> plugin) {
        Constants.getManager().registerEvents(listener, plugin);
    }
}
