package io.github.simplexdev.simplexcore.module;

import io.github.simplexdev.api.annotations.ReqType;
import io.github.simplexdev.api.annotations.Requires;
import io.github.simplexdev.simplexcore.SimplexCorePlugin;

import java.util.HashSet;
import java.util.Set;

public final class ModuleRegistry {
    private static final ModuleRegistry instance = new ModuleRegistry();
    private final Set<SimplexModule<?>> modules = new HashSet<>();

    protected ModuleRegistry() {
    }

    public static synchronized ModuleRegistry getInstance() {
        return instance;
    }

    public <T extends SimplexModule<T>> boolean isPaper(T addon) {
        try {
            Class.forName(com.destroystokyo.paper.Namespaced.class.getName());
            return true;
        } catch (ClassNotFoundException ignored) {
            addon.stop();
            SimplexCorePlugin.getInstance().getLogger().severe(addon.getName() + " has been disabled: This module requires Paper!");
            return false;
        }
    }

    private <T extends SimplexModule<T>> boolean isBungee(T addon) {
        try {
            Class.forName(net.md_5.bungee.Util.class.getName());
            return true;
        } catch (ClassNotFoundException ignored) {
            addon.stop();
            SimplexCorePlugin.getInstance().getLogger().severe(addon.getName() + " has been disabled: This module requires Bungeecord!");
            return false;
        }
    }

    private <T extends SimplexModule<T>> boolean isWaterfall(T addon) {
        try {
            Class.forName(io.github.waterfallmc.waterfall.utils.Hex.class.getName());
            return true;
        } catch (ClassNotFoundException ignored) {
            addon.stop();
            SimplexCorePlugin.getInstance().getLogger().severe(addon.getName() + " has been disabled: This module requires Waterfall!");
            return false;
        }
    }

    private boolean checkAnnotation(Requires info, ReqType type) {
        return info.value() == type;
    }

    public <T extends SimplexModule<T>> void register(T addon) {
        if (addon.getClass().isAnnotationPresent(Requires.class)) {
            Requires info = addon.getClass().getDeclaredAnnotation(Requires.class);
            if (checkAnnotation(info, ReqType.PAPER)
                    && !isPaper(addon)) {
                return;
            }
            if (checkAnnotation(info, ReqType.BUNGEECORD)
                    && !isBungee(addon)) {
                return;
            }
            if (checkAnnotation(info, ReqType.WATERFALL)
                    && !isWaterfall(addon)) {
                return;
            }
        }
        getModules().add(addon);
    }

    public Set<SimplexModule<?>> getModules() {
        return modules;
    }
}