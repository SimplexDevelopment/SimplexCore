package io.github.simplexdev.simplexcore.enchanting;

import io.github.simplexdev.api.IEnchant;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

public abstract class SimplexEnch extends Enchantment implements IEnchant {
    public SimplexEnch(@NotNull NamespacedKey key) {
        super(key);
    }
}
