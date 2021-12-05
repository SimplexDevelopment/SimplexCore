package io.github.simplexdev.simplexcore.module;

import io.github.simplexdev.api.annotations.ReqType;
import io.github.simplexdev.api.annotations.Requires;
import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

public final class ModuleRegistry {
    private static final ModuleRegistry instance = new ModuleRegistry();
    private final Set<SimplexModule<?>> modules = new HashSet<>();

    private ModuleRegistry() {
    }

    public static ModuleRegistry getInstance() {
        return instance;
    }

    @Contract(pure = true)
    private boolean checkAnnotation(@NotNull Requires info, ReqType type) {
        return info.value() == type;
    }

    public <T extends SimplexModule<T>> void register(T addon) {
        getModules().add(addon);
    }

    public Set<SimplexModule<?>> getModules() {
        return modules;
    }
}