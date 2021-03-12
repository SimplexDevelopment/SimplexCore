package io.github.simplexdev.simplexcore.config;

import io.github.simplexdev.simplexcore.plugin.SimplexAddon;
import io.github.simplexdev.simplexcore.utils.Trio;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public final class YamlFactory {
    private final SimplexAddon<?> plugin;
    private String resourcePath;
    private File directory;
    private String fileName;

    public YamlFactory(SimplexAddon<?> plugin) {
        this.plugin = plugin;
    }

    public Yaml from(String resourcePath, File directory, String fileName) {
        this.resourcePath = resourcePath;
        this.directory = directory;
        this.fileName = fileName;
        return new Yaml(plugin, fileName, directory, resourcePath);
    }

    public FileConfiguration load(File yamlFile) {
        return YamlConfiguration.loadConfiguration(yamlFile);
    }

    public Yaml setDefaultPathways() {
        return from("config.yml", plugin.getDataFolder(), "config.yml");
    }

    public Trio<String, File, String> pathways() {
        return new Trio<>(resourcePath, directory, fileName);
    }
}
