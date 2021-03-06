package io.github.simplexdev.simplexcore.listener;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.PluginEnableEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public final class DependencyListener extends SimplexListener {
    public List<String> PAPI_NAMES = new ArrayList<>() {{
        add("PlaceholderAPI");
        add("PlaceHolderAPI");
        add("placeholderapi");
        add("PLACEHOLDERAPI");
        add("PAPI");
        add("papi");
    }};

    public List<String> PLIB_NAMES = new ArrayList<>() {{
        add("ProtocolLib");
        add("PLib");
        add("Protocollib");
        add("plib");
        add("protocollib");
        add("PROTOCOLLIB");
    }};

    @EventHandler(priority = EventPriority.HIGHEST)
    public void pluginRegister(PluginEnableEvent event) {
        BooleanSupplier temp = () -> PLIB_NAMES.contains(event.getPlugin().getName());
        BooleanSupplier temp2 = () -> PAPI_NAMES.contains(event.getPlugin().getName());

        if (temp.getAsBoolean()) {
            SimplexCorePlugin.getInstance().getDependencyManager().registerProtocolLib();
        }

        if (temp2.getAsBoolean()) {
            SimplexCorePlugin.getInstance().getDependencyManager().registerPAPI();
        }
    }
}
