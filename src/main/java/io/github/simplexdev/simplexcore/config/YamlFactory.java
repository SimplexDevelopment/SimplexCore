package io.github.simplexdev.simplexcore.config;

import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.Trio;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public final class YamlFactory {
    private final SimplexModule<?> plugin;
    private String resourcePath;
    private File directory;
    private String fileName;

    public YamlFactory(SimplexModule<?> plugin) {
        this.plugin = plugin;
    }

    @Contract("_, _, _ -> new")
    public @NotNull Yaml from(String resourcePath, File directory, String fileName) {
        this.resourcePath = resourcePath;
        this.directory = directory;
        this.fileName = fileName;
        return new Yaml(plugin, fileName, directory, resourcePath);
    }

    @Contract("_ -> new")
    public @NotNull FileConfiguration load(File yamlFile) {
        return YamlConfiguration.loadConfiguration(yamlFile);
    }

    @Contract(" -> new")
    public @NotNull Yaml setDefaultPathways() {
        return from("config.yml", plugin.getDataFolder(), "config.yml");
    }

    @Contract(value = " -> new", pure = true)
    public @NotNull Trio<String, File, String> pathways() {
        return new Trio<>(resourcePath, directory, fileName);
    }
}
