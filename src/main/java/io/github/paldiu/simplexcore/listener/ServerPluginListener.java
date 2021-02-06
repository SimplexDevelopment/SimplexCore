package io.github.paldiu.simplexcore.listener;

import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Constants;
import io.github.paldiu.simplexcore.utils.Utilities;
import io.github.paldiu.simplexcore.utils.Validate;
import org.bukkit.event.EventHandler;
import org.bukkit.event.server.PluginEnableEvent;

import java.util.ArrayList;
import java.util.List;

public final class ServerPluginListener extends SimplexListener {
    public List<String> PAPI_NAMES = new ArrayList<>(){{
        add("PlaceholderAPI");
        add("PlaceHolderAPI");
        add("placeholderapi");
        add("PLACEHOLDERAPI");
        add("PAPI");
        add("papi");
    }};

    public List<String> PLIB_NAMES = new ArrayList<>(){{
        add("ProtocolLib");
        add("PLib");
        add("Protocollib");
        add("plib");
    }};

    @EventHandler
    public void pluginRegister(PluginEnableEvent event) {
        Validate temp = () -> {
            if (PLIB_NAMES.contains(event.getPlugin().getName())) {
                return true;
            }

            return PAPI_NAMES.contains(event.getPlugin().getName());
        };

        if (temp.isValid()) {

        }

    }
}
