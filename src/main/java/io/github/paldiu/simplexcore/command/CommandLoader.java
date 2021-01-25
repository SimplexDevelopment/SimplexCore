package io.github.paldiu.simplexcore.command;

import io.github.paldiu.simplexcore.Constants;
import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.jetbrains.annotations.NotNull;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class CommandLoader {
    private final Reflections reflections;

    public CommandLoader(Class<?> clazz) {
        if (!clazz.isAnnotationPresent(CommandInfo.class)) {
            throw new MissingResourceException("Cannot register this class as the main resource location!", clazz.getSimpleName(), "@CommandInfo");
        }

        if (!clazz.isAssignableFrom(CommandExecutor.class)) {
            throw new RuntimeException("Unable to assign an executor!");
        }

        reflections = new Reflections(clazz);
    }

    public void load() {
        reflections.getTypesAnnotatedWith(CommandInfo.class).forEach(annotated -> {
            CommandInfo info = annotated.getDeclaredAnnotation(CommandInfo.class);

            if (info == null) return;
            if (!CommandExecutor.class.isAssignableFrom(annotated)) return;

            PluginCommand objectToRegister = Registry.create(Constants.getPlugin(), info.name());
            objectToRegister.setDescription(info.description());
            objectToRegister.setUsage(info.usage());
            objectToRegister.setAliases(Arrays.asList(info.aliases().split(",")));
            objectToRegister.setPermission(info.permission());
            objectToRegister.setExecutor(getFromSetName(info.name()));
            Registry.registerCommand(objectToRegister);
        });
    }

    public CommandExecutor getFromSetName(String name) {
        for (Class<? extends CommandExecutor> obj : reflections.getSubTypesOf(CommandExecutor.class)) {
            if (name.equalsIgnoreCase(obj.getSimpleName())) {
                try {
                    Constructor<? extends CommandExecutor> constr = obj.getDeclaredConstructor();
                    return constr.newInstance();
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        throw new RuntimeException("Unable to get a command executor! Terminating!");
    }

    @SuppressWarnings("unchecked")
    private static class Registry {
        private static final Constructor<PluginCommand> constructor;
        private static final Field cmdMapField;
        private static final Field knownCmdsField;

        static {
            Constructor<PluginCommand> temp;
            try {
                temp = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
                temp.setAccessible(true);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
            constructor = temp;

            Field cmf;
            try {
                cmf = SimplePluginManager.class.getDeclaredField("commandMap");
                cmf.setAccessible(true);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            cmdMapField = cmf;

            Field kcf;
            try {
                kcf = SimpleCommandMap.class.getDeclaredField("knownCommands");
                kcf.setAccessible(true);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            knownCmdsField = kcf;
        }

        public static PluginCommand create(@NotNull Plugin plugin, @NotNull String name) {
            try {
                return constructor.newInstance(name, plugin);
            } catch (IllegalAccessException | InstantiationException | InvocationTargetException e) {
                throw new RuntimeException(e);
            }
        }

        public static void registerCommand(PluginCommand command) {
            try {
                CommandMap map = (CommandMap) cmdMapField.get(Bukkit.getPluginManager());
                Map<String, Command> knownCommands = map.getKnownCommands();

                if (knownCommands.containsKey(command.getName())) {
                    knownCommands.replace(command.getName(), command);
                }

                map.register(command.getName(), command);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
