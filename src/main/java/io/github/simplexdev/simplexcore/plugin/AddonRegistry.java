package io.github.simplexdev.simplexcore.plugin;

import java.util.HashSet;
import java.util.Set;

public final class AddonRegistry {
    private static final AddonRegistry instance = new AddonRegistry();
    private final Set<SimplexAddon<?>> components = new HashSet<>();

    protected AddonRegistry() {
    }

    public static AddonRegistry getInstance() {
        return instance;
    }

    public <T extends SimplexAddon<T>> void register(T addon) {
        getComponents().add(addon);
    }

    public Set<SimplexAddon<?>> getComponents() {
        return components;
    }
}