package io.github.paldiu.simplexcore.config;

import io.github.paldiu.simplexcore.plugin.SimplexAddon;
import io.github.paldiu.simplexcore.utils.Trio;

import java.io.File;

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
        return setPathways("data.json", plugin.getDataFolder(), "data.json");
    }

    public Trio<String, File, String> pathways() {
        return new Trio<>(resourcePath, directory, fileName);
    }
}