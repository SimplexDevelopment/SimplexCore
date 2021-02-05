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

    public YamlFactory setPathways(String resourcePath, File directory, String fileName) {
        this.resourcePath = resourcePath;
        this.directory = directory;
        this.fileName = fileName;
        return this;
    }

    public YamlFactory setDefaultPathways() {
        return setPathways("config.yml", plugin.getDataFolder(), "config.yml");
    }

    public Trio<String, File, String> pathways() {
        return new Trio<>(resourcePath, directory, fileName);
    }
}
