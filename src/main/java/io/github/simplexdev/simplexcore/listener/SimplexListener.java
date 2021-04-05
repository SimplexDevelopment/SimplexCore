package io.github.simplexdev.simplexcore.listener;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.ReflectionTools;
import org.bukkit.event.Listener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class SimplexListener implements Listener {
    /**
     * This will register your listener by its class. This requires your class to have a single constructor which takes no parameters.
     * This also requires the class in question to extend SimplexListener.
     *
     * @param cls The class to register.
     */
    public static void registerFromClass(Class<? extends SimplexListener> cls, SimplexModule<?> plugin) {
        if (!SimplexListener.class.isAssignableFrom(cls)) {
            SimplexCorePlugin.getInstance().getLogger().severe("You can only register subclasses of SimplexListener with this method.");
            return;
        }

        Constructor<? extends SimplexListener> constr = ReflectionTools.getDeclaredConstructor(cls);
        register(ReflectionTools.initConstructor(constr), plugin);
    }

    /**
     * Registers your listener with the server plugin manager.
     *
     * @param listener The listener instance
     * @param plugin   Your plugin instance
     * @param <T>      Type of which extends SimplexListener.
     */
    public static <T extends SimplexListener> void register(T listener, SimplexModule<?> plugin) {
        SimplexCorePlugin.getInstance().getManager().registerEvents(listener, plugin);
    }
}
