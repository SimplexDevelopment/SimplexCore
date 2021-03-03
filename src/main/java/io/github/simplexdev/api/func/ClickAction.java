package io.github.simplexdev.api.func;

import org.bukkit.entity.Player;

/**
 * Functional interface which provides an action to execute
 * when a player clicks on an {@link io.github.simplexdev.api.IGUI} provided inventory slot.
 */
@FunctionalInterface
public interface ClickAction {
    /**
     * Returns a void when a player clicks on an
     * inventory slot provided by the GUI class for the GUIListener.
     * @param player The player who interacted with the inventory slot.
     */
    void onClick(Player player);
}
