package io.github.simplexdev.simplexcore.listener;

import io.github.simplexdev.api.func.Validate;
import io.github.simplexdev.simplexcore.utils.Constants;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.server.PluginEnableEvent;

import java.util.ArrayList;
import java.util.List;

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
        Validate temp = () -> PLIB_NAMES.contains(event.getPlugin().getName());
        Validate temp2 = () -> PAPI_NAMES.contains(event.getPlugin().getName());

        if (temp.isValid()) {
            Constants.getPlugin().getInstances().getDependencyManager().registerProtocolLib();
        }

        if (temp2.isValid()) {
            Constants.getPlugin().getInstances().getDependencyManager().registerPAPI();
        }
    }
}
