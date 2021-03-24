package io.github.simplexdev.api;

import org.bukkit.NamespacedKey;

public interface IEnchant {
    NamespacedKey getNamespacedKey();

    String getName();

    Integer getMaximumLevel();

    Boolean isGlowing();
}
