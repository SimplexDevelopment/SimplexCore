package io.github.simplexdev.simplexcore.config;

import io.github.simplexdev.api.IConfig;
import io.github.simplexdev.api.func.Path;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class Json implements IConfig {
    // TODO: Write actual JSON implementations.

    @Override
    public void set(Path path, Object value) {

    }

    @Override
    public boolean contains(Path path) {
        return false;
    }

    @Override
    public ConfigurationSection getConfigurationSection(Path path) {
        return null;
    }

    @Override
    public List<String> getStringList(Path path) {
        return null;
    }

    @Override
    public long getLong(Path path) {
        return 0;
    }

    @Override
    public List<?> getList(Path path) {
        return null;
    }

    @Override
    public boolean getBoolean(Path path) {
        return false;
    }

    @Override
    public int getInt(Path path) {
        return 0;
    }

    @Override
    public double getDouble(Path path) {
        return 0;
    }

    @Override
    public String getString(Path path) {
        return null;
    }

    @Override
    public long getLong(Path path, long def) {
        return 0;
    }

    @Override
    public List<?> getList(Path path, List<?> def) {
        return null;
    }

    @Override
    public boolean getBoolean(Path path, boolean def) {
        return false;
    }

    @Override
    public int getInt(Path path, int def) {
        return 0;
    }

    @Override
    public double getDouble(Path path, double def) {
        return 0;
    }

    @Override
    public String getString(Path path, String def) {
        return null;
    }

    @Override
    public Object get(Path path, Object def) {
        return null;
    }
}
