package io.github.paldiu.simplexcore.plugin;

import java.util.HashSet;
import java.util.Set;

public final class AddonRegistry {
    private final Set<SimplexAddon<?>> components = new HashSet<>();
    private static final AddonRegistry instance = new AddonRegistry();

    protected AddonRegistry() {
    }

    public static AddonRegistry getInstance() {
        return instance;
    }

    public <T extends SimplexAddon<T>>void register(T addon) {
        getComponents().add(addon);
    }

    public Set<SimplexAddon<?>> getComponents() {
        return components;
    }
}