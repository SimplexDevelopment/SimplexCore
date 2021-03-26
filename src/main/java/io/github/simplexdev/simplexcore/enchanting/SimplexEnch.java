package io.github.simplexdev.simplexcore.enchanting;

import io.github.simplexdev.api.IEnchant;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import net.minecraft.server.v1_16_R3.EnchantmentSlotType;
import net.minecraft.server.v1_16_R3.EnumItemSlot;
import net.minecraft.server.v1_16_R3.Enchantment;
import org.bukkit.NamespacedKey;

public abstract class SimplexEnch extends Enchantment implements IEnchant {
    protected final SimplexModule<?> plugin;

    protected SimplexEnch(SimplexModule<?> plugin, Rarity rarity, EnchantmentSlotType type, EnumItemSlot[] enumSlot) {
        super(rarity, type, enumSlot);
        this.plugin = plugin;
    }

    public abstract String name();

    @Override
    public NamespacedKey getNamespacedKey() {
        return new NamespacedKey(plugin, name());
    }
}
