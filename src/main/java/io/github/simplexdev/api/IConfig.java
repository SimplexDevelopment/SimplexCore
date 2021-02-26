package io.github.simplexdev.api;

import io.github.simplexdev.api.func.Path;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public interface IConfig {
    void set(Path path, Object value);

    boolean contains(Path path);

    ConfigurationSection getConfigurationSection(Path path);

    List<String> getStringList(Path path);

    long getLong(Path path);

    List<?> getList(Path path);

    boolean getBoolean(Path path);

    int getInt(Path path);

    double getDouble(Path path);

    String getString(Path path);

    long getLong(Path path, long def);

    List<?> getList(Path path, List<?> def);

    boolean getBoolean(Path path, boolean def);

    int getInt(Path path, int def);

    double getDouble(Path path, double def);

    String getString(Path path, String def);

    Object get(Path path, Object def);
}
