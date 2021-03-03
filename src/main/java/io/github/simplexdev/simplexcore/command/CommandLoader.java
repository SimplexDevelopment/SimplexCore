package io.github.simplexdev.simplexcore.command;

import io.github.simplexdev.api.annotations.CommandInfo;
import io.github.simplexdev.simplexcore.command.defaults.DefaultCommand;
import io.github.simplexdev.simplexcore.utils.Constants;
import io.github.simplexdev.simplexcore.utils.ReflectionTools;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.MissingResourceException;

public final class CommandLoader {
    private static final CommandLoader instance = new CommandLoader();
    private Reflections reflections;

    protected CommandLoader() {
    }

    public static CommandLoader getInstance() {
        return instance;
    }

    public synchronized CommandLoader classpath(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(CommandInfo.class)) {
            throw new MissingResourceException("Cannot register this class as the main resource location!", clazz.getSimpleName(), "@CommandInfo");
        }

        if (!clazz.isAssignableFrom(CommandExecutor.class)) {
            throw new RuntimeException("Unable to assign an executor!");
        }

        reflections = new Reflections(clazz);
        return this;
    }

    public synchronized void load() {
        reflections.getTypesAnnotatedWith(CommandInfo.class).forEach(annotated -> {
            CommandInfo info = annotated.getDeclaredAnnotation(CommandInfo.class);

            if (info == null) return;
            if (!SimplexCommand.class.isAssignableFrom(annotated)) return;

            PluginCommand objectToRegister = Registry.create(Constants.getPlugin(), info.name().toLowerCase());
            objectToRegister.setAliases(Arrays.asList(info.aliases().split(",")));
            objectToRegister.setDescription(info.description());
            objectToRegister.setExecutor(getExecutorFromName(info.name()));
            objectToRegister.setLabel(info.name().toLowerCase());
            objectToRegister.setPermission(info.permission());
            objectToRegister.setPermissionMessage(info.permissionMessage());
            objectToRegister.setTabCompleter(getTabFromName(info.name()));
            objectToRegister.setUsage(info.usage());
            Registry.registerCommand(objectToRegister);
        });
    }

    public synchronized CommandExecutor getExecutorFromName(String name) {
        for (Class<? extends CommandExecutor> obj : reflections.getSubTypesOf(CommandExecutor.class)) {
            if (!obj.isAnnotationPresent(CommandInfo.class)) {
                throw new RuntimeException("Missing annotation CommandInfo!");
            }

            CommandInfo info = obj.getDeclaredAnnotation(CommandInfo.class);

            if (name.equalsIgnoreCase(info.name())) {
                try {
                    Constructor<? extends CommandExecutor> constr = obj.getDeclaredConstructor();
                    constr.setAccessible(true);
                    return constr.newInstance();
                } catch (ReflectiveOperationException ignored) {
                    return new DefaultCommand();
                }
            }
        }
        throw new RuntimeException("Unable to get a command executor! Terminating!");
    }

    @Nullable
    public synchronized TabCompleter getTabFromName(String name) {
        for (Class<? extends TabCompleter> obj : reflections.getSubTypesOf(TabCompleter.class)) {
            if (!obj.isAnnotationPresent(CommandInfo.class)) {
                throw new RuntimeException("Missing annotation CommandInfo!");
            }

            CommandInfo info = obj.getDeclaredAnnotation(CommandInfo.class);
            if (name.equalsIgnoreCase(info.name())) {
                try {
                    Constructor<? extends TabCompleter> constr = obj.getDeclaredConstructor();
                    constr.setAccessible(true);
                    return constr.newInstance();
                } catch (ReflectiveOperationException ignored) {
                    return new DefaultCommand();
                }
            }
        }
        return null;
    }

    private static class Registry {
        private static final Constructor<PluginCommand> constructor;
        private static final Field cmdMapField;
        private static final Field knownCmdsField;

        static {
            constructor = ReflectionTools.getDeclaredConstructor(PluginCommand.class, String.class, Plugin.class);
            cmdMapField = ReflectionTools.getDeclaredField(SimplePluginManager.class, "commandMap");
            knownCmdsField = ReflectionTools.getDeclaredField(SimpleCommandMap.class, "knownCommands");
        }

        public static PluginCommand create(@NotNull Plugin plugin, @NotNull String name) {
            return ReflectionTools.initConstructor(constructor, name, plugin);
        }

        public static void registerCommand(PluginCommand command) {
            try {
                CommandMap map = (CommandMap) cmdMapField.get(Bukkit.getPluginManager());
                Map<String, Command> knownCommands = map.getKnownCommands();

                if (knownCommands.containsKey(command.getName().toLowerCase())) {
                    knownCommands.replace(command.getName().toLowerCase(), command);
                }

                map.register(command.getName().toLowerCase(), command);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
