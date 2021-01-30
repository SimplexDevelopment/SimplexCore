package io.github.paldiu.simplexcore.config;

import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Trio;

import java.io.File;
import java.util.Map;

public final class JsonFactory {
    private final SimplexAddon<?> plugin;
    private String resourcePath;
    private File directory;
    private String fileName;

    public JsonFactory(SimplexAddon<?> plugin) {
        this.plugin = plugin;
    }

    public JsonFactory setPathways(String resourcePath, File directory, String fileName) {
        this.resourcePath = resourcePath;
        this.directory = directory;
        this.fileName = fileName;
        return this;
    }

    public JsonFactory setDefaultPathways() {
        return setPathways("config.yml", plugin.getDataFolder(), "config.yml");
    }

    public Trio<String, File, String> getPathways() {
        return new Trio<>(resourcePath, directory, fileName);
    }
}