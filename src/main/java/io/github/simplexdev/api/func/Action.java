package io.github.simplexdev.api.func;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface Action {
    void onClick(Player player);
}
