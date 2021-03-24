package io.github.simplexdev.simplexcore.module;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.jetbrains.annotations.Nullable;

public class DependencyManagement {
    private ProtocolManager manager;
    private PlaceholderAPIPlugin papi;

    public DependencyManagement() {
        manager = null;
        papi = null;
    }

    public void registerProtocolLib() {
        manager = ProtocolLibrary.getProtocolManager();
    }

    public void registerPAPI() {
        papi = PlaceholderAPIPlugin.getInstance();
    }

    @Nullable
    public PlaceholderAPIPlugin getPAPI() {
        return papi;
    }

    @Nullable
    public ProtocolManager getProtocolManager() {
        return manager;
    }
}
