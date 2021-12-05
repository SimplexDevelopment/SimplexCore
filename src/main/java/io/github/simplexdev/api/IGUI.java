package io.github.simplexdev.api;

import io.github.simplexdev.api.func.ClickAction;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

/**
 * An interface which supplies customizable inventory GUI instances.
 */
public interface IGUI {
    /**
     * @return UUID of the inventory provided by the interface instance.
     */
    UUID getInvUUId();

    /**
     * The actual inventory provided by the interface instance.
     * @return the inventory which is provided by the interface instance.
     */
    Inventory getInventory();

    /**
     * Sets the item for the defined slot to the specified item stack.
     * You may define an action to run when the player clicks on the inventory slot,
     * however it is not required; you may also pass null to the clickAction parameter.
     * @param slot The inventory slot to set; remember that arrays start at 0, so
     *             an inventory size of 9 will range from 0 to 8.
     * @param stack The item to display in the slot. This can be Material.AIR.
     * @param clickAction The action to perform when a player clicks the inventory slot.
     *                    This may be null.
     */
    void setItem(int slot, @NotNull ItemStack stack, @Nullable ClickAction clickAction);

    /**
     * Sets the item for the defined slot to the specified item stack.
     * This method should automatically define the slot action as null.
     * @param slot The inventory slot to set; remember that arrays start at 0, so
     *             an inventory size of 9 will range from 0 to 8.
     * @param stack The item to display in the slot. This can be Material.AIR.
     */
    void setItem(int slot, @NotNull ItemStack stack);

    /**
     * Force a player to open the inventory provided by the interface instance.
     * @param player The player to open the inventory on.
     */
    void open(@NotNull Player player);

    /**
     * Force a player to close the inventory provided by the interface instance.
     * @param player The player to close the inventory on.
     */
    void close(@NotNull Player player);

    /**
     * Delete the inventory provided by the interface instance.
     */
    void delete();

    /**
     * @return A map containing each inventory slot and their respective actions,
     * but only if the slot actually has an action.
     */
    Map<Integer, ClickAction> getActions();

    /**
     * Creates a new ItemStack instance to place in the inventory provided by the interface instance.
     * @param material The item material
     * @param name The name of the item
     * @param lore Optional item descriptions, as components.
     * @return The newly created item
     */
    ItemStack newItem(@NotNull Material material, @NotNull String name, Component... lore);

    /**
     * Creates a new ItemStack instance to place in the inventory provided by the interface instance.
     * @param material The item material
     * @param name The name of the item
     * @return The newly created item with no lore.
     */
    ItemStack newItem(@NotNull Material material, @NotNull String name);
}
