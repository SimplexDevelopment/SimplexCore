package io.github.simplexdev.simplexcore.command;

import io.github.simplexdev.api.annotations.CommandInfo;
import io.github.simplexdev.simplexcore.command.defaults.DefaultCommand;
import io.github.simplexdev.simplexcore.plugin.SimplexAddon;
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
    private Reflections reflections;
    private static final CommandLoader instance = new CommandLoader();

    /**
     * @return A Singleton Pattern instance of this class.
     */
    public static CommandLoader getInstance() {
        return instance;
    }

    /**
     * Prepares the CommandLoader to load your plugin's commands from its own package location.
     * This is synchronized, so it only registers commands from one plugin at a time.
     * All your commands MUST be placed in their own package.
     * <p>
     * If your command classes do not have the {@link CommandInfo} annotation, they will not be loaded.
     * If the class provided does not have the {@link CommandInfo} annotation, the loader will throw a new
     * {@link MissingResourceException} and will not load your plugin's commands.
     * If the class provided does not extend {@link SimplexCommand}, the loader will throw a new
     * {@link RuntimeException} and your commands will not be loaded.
     * </p>
     *
     * @param clazz The command class to load from
     * @return An instance of this where the classpath has been prepared for loading the commands.
     */
    public <T extends SimplexCommand> CommandLoader classpath(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalStateException("The class provided cannot be found!");
        }

        if (!clazz.isAnnotationPresent(CommandInfo.class)) {
            throw new MissingResourceException("Cannot register this class as the main resource location!", clazz.getSimpleName(), "@CommandInfo");
        }

        if (!SimplexCommand.class.isAssignableFrom(clazz)) {
            throw new RuntimeException("Your command must extend SimplexCommand.class for it to be used as the reference point for loading commands.");
        }

        reflections = ReflectionTools.reflect(clazz);

        return this;
    }

    /**
     * Loads all the commands from the specified classpath.
     * This should be used immediately after {@link CommandLoader#classpath(Class)} has been called.
     * If used before, an exception will be thrown, and your commands will not be loaded.
     *
     * @param plugin An instance of your plugin to assign as the parent plugin for each command.
     */
    public void load(SimplexAddon<?> plugin) {
        reflections.getTypesAnnotatedWith(CommandInfo.class).forEach(annotated -> {
            CommandInfo info = annotated.getDeclaredAnnotation(CommandInfo.class);

            if (info == null) return;
            if (!SimplexCommand.class.isAssignableFrom(annotated)) return;

            PluginCommand command = Registry.create(plugin, info.name().toLowerCase());
            command.setAliases(Arrays.asList(info.aliases().split(",")));
            command.setDescription(info.description());
            command.setExecutor(getExecutorFromName(info.name()));
            command.setLabel(info.name().toLowerCase());
            command.setPermission(info.permission());
            command.setPermissionMessage(info.permissionMessage());
            command.setTabCompleter(getTabFromName(info.name()));
            command.setUsage(info.usage());
            Registry.registerCommand(command);
        });
    }

    /**
     * Gets the command class as a child of {@link CommandExecutor} from the {@link CommandInfo#name()} annotation.
     * This is for registering the CommandExecutor of the provided command with Bukkit.
     * This should only be used by the CommandLoader.
     *
     * @param name The name of the command.
     * @return An instance of the command class as a CommandExecutor.
     */
    public CommandExecutor getExecutorFromName(String name) {
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

    /**
     * Gets the command class as a child of {@link TabCompleter} from the {@link CommandInfo#name()} annotation.
     * This is for registering the TabCompleter of the provided command with Bukkit.
     * This should only be used by the CommandLoader.
     *
     * @param name The name of the command
     * @return The command as an instance of TabCompleter
     */
    @Nullable
    public TabCompleter getTabFromName(String name) {
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

    /**
     * Registry class, which forces all necessary fields to accessible.
     */
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
