package io.github.paldiu.simplexcore.config;

import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Trio;

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
        return new Yaml(plugin, resourcePath, directory, fileName);
    }

    public Yaml setDefaultPathways() {
        return from("config.yml", plugin.getDataFolder(), "config.yml");
    }

    public Trio<String, File, String> pathways() {
        return new Trio<>(resourcePath, directory, fileName);
    }
}
