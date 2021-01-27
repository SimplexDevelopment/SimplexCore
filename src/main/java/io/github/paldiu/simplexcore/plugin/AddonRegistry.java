package io.github.paldiu.simplexcore.plugin;

import java.util.HashSet;
import java.util.Set;

public class AddonRegistry {
    private final Set<Addon<?>> components = new HashSet<>();
    private static final AddonRegistry instance = new AddonRegistry();

    protected AddonRegistry() {
    }

    public static AddonRegistry getInstance() {
        return instance;
    }

    public <T extends Addon<T>>void register(T addon) {
        getComponents().add(addon);
    }

    public Set<Addon<?>> getComponents() {
        return components;
    }
}