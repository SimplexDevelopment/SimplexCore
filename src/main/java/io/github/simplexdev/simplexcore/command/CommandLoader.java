package io.github.simplexdev.simplexcore.command;

import io.github.simplexdev.api.annotations.CommandInfo;
import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.chat.TextComponentFactory;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.ReflectionTools;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.SimplePluginManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.MissingResourceException;

public final class CommandLoader {
    private Reflections reflections;
    private SimplexModule<?> plugin;
    private final Registry registry = new Registry();

    /**
     * Prepares the CommandLoader to load your plugin's commands from its own package location.
     * All your commands MUST be placed in their own package.
     * <p>
     * If your command classes do not have the {@link CommandInfo} annotation, they will not be loaded.
     * If the class provided does not have the {@link CommandInfo} annotation, the loader will throw a new
     * {@link MissingResourceException} and will not load your plugin's commands.
     * If the class provided does not extend {@link SimplexCommand}, the loader will throw a new
     * {@link CommandLoaderException} and your commands will not be loaded.
     * </p>
     *
     * @param clazz The command class to load from
     */
    public <T extends SimplexCommand> void load(SimplexModule<?> plugin, Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("The class provided cannot be found!");
        }

        if (!clazz.isAnnotationPresent(CommandInfo.class)) {
            throw new MissingResourceException("Cannot register this class as the main resource location!", clazz.getSimpleName(), "@CommandInfo");
        }

        if (!SimplexCommand.class.isAssignableFrom(clazz)) {
            throw new CommandLoaderException("Your command must extend SimplexCommand.class for it to be used as the reference point for loading commands.");
        }

        this.reflections = ReflectionTools.reflect(clazz);
        this.plugin = plugin;
        ClassLoader classLoader = plugin.getClass().getClassLoader();

        if (reflections == null || classLoader == null) {
            throw new CommandLoaderException("Please run CommandLoader#classpath(SimplexModule, Class) first!");
        }

        TextComponentFactory factory = new TextComponentFactory();

        reflections.getTypesAnnotatedWith(CommandInfo.class).forEach(annotated -> {
            CommandInfo info = annotated.getDeclaredAnnotation(CommandInfo.class);

            if (info == null) {
                SimplexCorePlugin.getInstance()
                        .getLogger().warning(annotated.getSimpleName()
                                + " is missing a required annotation: "
                                + CommandInfo.class.getSimpleName()
                                + ". Ignoring.");
                return;
            }

            if (!SimplexCommand.class.isAssignableFrom(annotated)) {
                SimplexCorePlugin.getInstance()
                        .getLogger().warning(annotated.getSimpleName()
                                + " does not extend "
                                + SimplexCommand.class.getSimpleName()
                                + ". Ignoring.");
                return;
            }

            PluginCommand command = registry.create(plugin, info.name().toLowerCase());
            command.setAliases(Arrays.asList(info.aliases().split(",")));
            command.setDescription(info.description());
            command.setExecutor(getExecutorFromName(info.name()));
            command.setLabel(info.name().toLowerCase());
            command.setPermission(info.permission());
            command.permissionMessage(factory.textComponent(info.permissionMessage()));
            command.setTabCompleter(getTabFromName(info.name()));
            command.setUsage(info.usage());
            registry.registerCommand(command);
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
    private CommandExecutor getExecutorFromName(String name) {
        for (Class<? extends SimplexCommand> obj : reflections.getSubTypesOf(SimplexCommand.class)) {
            if (!obj.isAnnotationPresent(CommandInfo.class)) {
                plugin.getLogger().warning(obj.getSimpleName()
                        + " is missing a required annotation: "
                        + CommandInfo.class.getSimpleName());
                continue;
            }

            CommandInfo info = obj.getDeclaredAnnotation(CommandInfo.class);

            if (name.equalsIgnoreCase(info.name())) {
                Constructor<? extends CommandExecutor> constr =
                        ReflectionTools.getDeclaredConstructor(obj, SimplexModule.class);
                if (constr == null) {
                    throw new CommandLoaderException("Constructor does not exist! Are you extending SimplexCommand properly?");
                }
                return ReflectionTools.initConstructor(constr, plugin);
            }
        }
        throw new CommandLoaderException("Unable to assign a CommandExecutor from the provided classes!");
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
    private TabCompleter getTabFromName(String name) {
        for (Class<? extends SimplexCommand> obj : reflections.getSubTypesOf(SimplexCommand.class)) {
            if (!obj.isAnnotationPresent(CommandInfo.class)) {
                plugin.getLogger().warning(obj.getSimpleName()
                        + " is missing required annotation: "
                        + CommandInfo.class.getSimpleName());
                continue;
            }

            CommandInfo info = obj.getDeclaredAnnotation(CommandInfo.class);

            if (name.equalsIgnoreCase(info.name())) {
                Constructor<? extends TabCompleter> constr = ReflectionTools.getDeclaredConstructor(obj, SimplexModule.class);
                if (constr == null) {
                    throw new CommandLoaderException("Constructor does not exist! Are you extending SimplexCommand properly?");
                }
                return ReflectionTools.initConstructor(constr, plugin);
            }
        }
        throw new CommandLoaderException("Unable to assign a TabCompleter from the provided classes!");
    }

    /**
     * Registry class, which forces all necessary fields to accessible.
     */
    private final class Registry {
        private final Constructor<PluginCommand> constructor;
        private final Field cmdMapField;

        public Registry() {
            constructor = ReflectionTools.getDeclaredConstructor(PluginCommand.class, String.class, Plugin.class);
            cmdMapField = ReflectionTools.getDeclaredField(SimplePluginManager.class, "commandMap");
        }

        public PluginCommand create(@NotNull SimplexModule<?> plugin, @NotNull String name) {
            return ReflectionTools.initConstructor(constructor, name, plugin);
        }

        public void registerCommand(PluginCommand command) {
            try {
                CommandMap map = (CommandMap) cmdMapField.get(plugin.getManager());
                map.register(command.getName().toLowerCase(), command);
            } catch (IllegalAccessException e) {
                throw new CommandLoaderException(e);
            }
        }
    }
}
