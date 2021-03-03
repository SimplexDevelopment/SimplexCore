package io.github.simplexdev.simplexcore.gui;

import io.github.simplexdev.api.func.ClickAction;
import io.github.simplexdev.api.IGUI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public abstract class AbstractGUI implements InventoryHolder, IGUI {
    private final Inventory INV;
    private final Map<Integer, ClickAction> actions;
    private final UUID uuid;

    private final List<Integer> validSize = new ArrayList<>(){{
        add(9);
        add(18);
        add(27);
        add(36);
        add(45);
        add(54);
    }};

    public static final Map<UUID, IGUI> invByUUId = new HashMap<>();
    public static final Map<UUID, UUID> openInvs = new HashMap<>();

    public AbstractGUI(int size, String name) {
        uuid = UUID.randomUUID();
        if (!validSize.contains(size)) {
            throw new NumberFormatException("Inventory sizes must be a multiple of nine!");
        }
        INV = Bukkit.createInventory(null, size, name);
        actions = new HashMap<>();
        invByUUId.put(getInvUUId(), this);
    }

    @Override
    public UUID getInvUUId() {
        return uuid;
    }

    @NotNull
    @Override
    public Inventory getInventory() {
        return INV;
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack, @Nullable ClickAction clickAction) {
        INV.setItem(slot, stack);
        if (clickAction != null) {
            actions.put(slot, clickAction);
        }
    }

    @Override
    public void setItem(int slot, @NotNull ItemStack stack) {
        setItem(slot, stack, null);
    }

    @Override
    public void open(@NotNull Player player) {
        player.openInventory(INV);
        openInvs.put(player.getUniqueId(), getInvUUId());
    }

    @Override
    public void close(@NotNull Player player) {
        player.closeInventory();
        openInvs.remove(player.getUniqueId());
    }

    @Override
    public void delete() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            UUID id = openInvs.get(player.getUniqueId());
            if (id.equals(getInvUUId())) {
                player.closeInventory();
            }
        });
        invByUUId.remove(getInvUUId());
    }

    public static Map<UUID, IGUI> getInvByUUId() {
        return invByUUId;
    }

    public static Map<UUID, UUID> getOpenInvs() {
        return openInvs;
    }

    @Override
    public Map<Integer, ClickAction> getActions() {
        return actions;
    }

    @Override
    public ItemStack newItem(@NotNull Material material, @NotNull String name, String... lore) {
        ItemStack item = new ItemStack(material, 1);
        ItemMeta meta = item.getItemMeta();
        if (meta == null) {
            return item;
        }
        meta.setDisplayName(name);
        ArrayList<String> metaLore = new ArrayList<>(Arrays.asList(lore));
        meta.setLore(metaLore);
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack newItem(@NotNull Material material, @NotNull String name) {
        return newItem(material, name, "");
    }
}
