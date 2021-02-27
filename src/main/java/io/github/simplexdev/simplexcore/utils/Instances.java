package io.github.simplexdev.simplexcore.utils;

import io.github.simplexdev.simplexcore.command.CommandLoader;
import io.github.simplexdev.simplexcore.config.Yaml;
import io.github.simplexdev.simplexcore.config.YamlFactory;
import io.github.simplexdev.simplexcore.plugin.AddonRegistry;
import io.github.simplexdev.simplexcore.plugin.DependencyManagement;

public final class Instances {
    private final DependencyManagement dpm = new DependencyManagement();
    private final Yaml config = new YamlFactory(Constants.getPlugin()).setDefaultPathways();
    private final TimeValues time = new TimeValues();

    public synchronized AddonRegistry getRegistry() {
        return AddonRegistry.getInstance();
    }

    public synchronized CommandLoader getCommandLoader() {
        return CommandLoader.getInstance();
    }

    public DependencyManagement getDependencyManager() {
        return dpm;
    }

    public TimeValues getTimeValues() {
        return time;
    }

    public Yaml getConfig() {
        return config;
    }
}
