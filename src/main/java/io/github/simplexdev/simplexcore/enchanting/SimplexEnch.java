package io.github.simplexdev.simplexcore.enchanting;

import io.github.simplexdev.simplexcore.module.SimplexModule;
import net.minecraft.server.v1_16_R3.EnchantmentSlotType;
import net.minecraft.server.v1_16_R3.EnumItemRarity;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.Enchantment;
import org.bukkit.NamespacedKey;

public abstract class SimplexEnch extends Enchantment {
    private final SimplexModule<?> plugin;
    private final Rarity rarity;

    protected SimplexEnch(SimplexModule<?> plugin, Rarity rarity, EnchantmentSlotType type, EnumItemSlot[] enumSlot) {
        super(rarity, type, enumSlot);
        this.rarity = rarity;
        this.plugin = plugin;
    }

    public abstract String name();

    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(plugin, name());
    }
}
