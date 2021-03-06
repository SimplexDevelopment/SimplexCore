package io.github.simplexdev.simplexcore.listener;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.plugin.SimplexAddon;
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
    public static void registerFromClass(Class<? extends SimplexListener> cls, SimplexAddon<?> plugin) {
        if (!SimplexListener.class.isAssignableFrom(cls)) {
            SimplexCorePlugin.getInstance().getLogger().severe("You can only register subclasses of SimplexListener with this method.");
            return;
        }

        try {
            Constructor<? extends SimplexListener> constr = cls.getDeclaredConstructor();
            register(constr.newInstance(), plugin);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            SimplexCorePlugin.getInstance().getLogger().severe("Could not register this listener!");
        }
    }

    /**
     * Registers your listener with the server plugin manager.
     *
     * @param listener The listener instance
     * @param plugin   Your plugin instance
     * @param <T>      Type of which extends SimplexListener.
     */
    public static <T extends SimplexListener> void register(T listener, SimplexAddon<?> plugin) {
        SimplexCorePlugin.getInstance().getManager().registerEvents(listener, plugin);
    }
}
