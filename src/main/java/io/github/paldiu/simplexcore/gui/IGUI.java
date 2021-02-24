package io.github.paldiu.simplexcore.gui;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;

public interface IGUI {
    UUID getInvUUId();

    Inventory getInventory();

    void setItem(int slot, @NotNull ItemStack stack, @Nullable Action action);

    void setItem(int slot, @NotNull ItemStack stack);

    void open(@NotNull Player player);

    void close(@NotNull Player player);

    void delete();

    Map<Integer, Action> getActions();

    ItemStack newItem(@NotNull Material material, @NotNull String name, String... lore);

    ItemStack newItem(@NotNull Material material, @NotNull String name);
}
