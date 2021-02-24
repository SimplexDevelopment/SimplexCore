package io.github.paldiu.simplexcore.gui;

import org.bukkit.entity.Player;

@FunctionalInterface
public interface Action {
    void onClick(Player player);
}
