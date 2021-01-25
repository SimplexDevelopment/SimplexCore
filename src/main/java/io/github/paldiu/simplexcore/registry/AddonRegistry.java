package io.github.paldiu.simplexcore.registry;

import java.util.HashSet;
import java.util.Set;

public class AddonRegistry {
    private final Set<Addon<?>> components = new HashSet<>();

    public AddonRegistry() {
    }

    public void register(Addon<?> addon) {
        getComponents().add(addon);
    }

    public Set<Addon<?>> getComponents() {
        return components;
    }
}