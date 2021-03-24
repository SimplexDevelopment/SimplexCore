package io.github.simplexdev.simplexcore.config;

import io.github.simplexdev.api.IConfig;
import io.github.simplexdev.api.func.Path;
import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.NotDirectoryException;
import java.util.List;

public final class Yaml implements IConfig {
    private final SimplexModule<?> plugin;
    private final String fileName;
    private final File directory;
    private final String resourcePath;
    private FileConfiguration config;
    private File file;

    // Package private ;)
    Yaml(SimplexModule<?> plugin, String fileName, File directory, String resourcePath) {
        if (!fileName.endsWith(".yml")) {
            fileName += ".yml";
        }

        if (!resourcePath.endsWith(".yml")) {
            resourcePath += ".yml";
        }

        this.plugin = plugin;
        this.fileName = fileName;
        this.directory = directory;
        this.resourcePath = resourcePath;
    }

    @Override
    public final void set(Path path, Object value) {
        this.getConfig().set(path.getPath(), value);
    }

    public boolean contains(Path path) {
        return this.getConfig().contains(path.getPath());
    }

    public ConfigurationSection getConfigurationSection(Path path) {
        return this.getConfig().getConfigurationSection(path.getPath());
    }

    public List<String> getStringList(Path path) {
        return this.getConfig().getStringList(path.getPath());
    }

    public long getLong(Path path) {
        return this.getConfig().getLong(path.getPath());
    }

    public List<?> getList(Path path) {
        return this.getConfig().getList(path.getPath());
    }

    public boolean getBoolean(Path path) {
        return this.getConfig().getBoolean(path.getPath());
    }

    public int getInt(Path path) {
        return this.getConfig().getInt(path.getPath());
    }

    public double getDouble(Path path) {
        return this.getConfig().getDouble(path.getPath());
    }

    public String getString(Path path) {
        return this.getConfig().getString(path.getPath());
    }

    public long getLong(Path path, long def) {
        return this.getConfig().getLong(path.getPath(), def);
    }

    public List<?> getList(Path path, List<?> def) {
        return this.getConfig().getList(path.getPath(), def);
    }

    public boolean getBoolean(Path path, boolean def) {
        return this.getConfig().getBoolean(path.getPath(), def);
    }

    public int getInt(Path path, int def) {
        return this.getConfig().getInt(path.getPath(), def);
    }

    public double getDouble(Path path, double def) {
        return this.getConfig().getDouble(path.getPath(), def);
    }

    public String getString(Path path, String def) {
        return this.getConfig().getString(path.getPath(), def);
    }

    public Object get(Path path, Object def) {
        return this.getConfig().get(path.getPath(), def);
    }

    public final void reload() {
        if (this.directory.exists()) { // Directory exist?
            if (!this.directory.isDirectory()) { // File is directory?
                try { // Not directory may contain important info!
                    throw new NotDirectoryException("'" + this.directory.getName() + "' is not a directory.");
                } catch (NotDirectoryException e) {
                    e.printStackTrace();
                }
            }
        } else { // Directory doesn't exist.
            //noinspection ResultOfMethodCallIgnored
            this.directory.mkdir(); // Make directory
        }
        this.file = new File(this.directory, this.fileName);
        boolean created = false;
        if (!file.exists()) {
            if (this.resourcePath != null) {
                if (plugin.getResource(resourcePath) != null) {
                    plugin.getLogger().info("Attempting to create '" + this.fileName + "' from resources.");
                    plugin.saveResource(resourcePath, false);
                }
            }
            created = true;
        }
        this.config = YamlConfiguration.loadConfiguration(this.file);
        if (created) create();
        onReload();
    }

    public final void save() throws IOException {
        config.save(file);
    }

    public final boolean exists() {
        return this.file.exists();
    }

    public final FileConfiguration getConfig() {
        return this.config;
    }

    /**
     * Called when a file is created.
     */
    public void create() {
        SimplexCorePlugin.getInstance().getLogger().info("File created!");
    }

    /**
     * Called when then file is reloaded
     */
    public void onReload() {
        SimplexCorePlugin.getInstance().getLogger().info("The plugin configuration has been reloaded!");
    }
}
