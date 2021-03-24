package io.github.simplexdev.simplexcore.enchanting;

import io.github.simplexdev.api.annotations.Enchant;
import io.github.simplexdev.simplexcore.utils.ReflectionTools;
import org.bukkit.enchantments.EnchantmentWrapper;

import java.lang.reflect.Constructor;

public class EnchUtils {
    public SimplexEnch enchantment;

    public <T extends SimplexEnch> void loadFrom(Class<T> clz) {
        Enchant info = clz.getDeclaredAnnotation(Enchant.class);

        if (info == null) {
            // TODO
            return;
        }

        ReflectionTools.reflect(clz).getTypesAnnotatedWith(info).forEach(cls -> {
            Constructor<?> c = ReflectionTools.getDeclaredConstructor(cls);
            if (c == null) return;
            SimplexEnch ench = (SimplexEnch) ReflectionTools.initConstructor(c);
            load(ench);
        });
    }

    public <T extends SimplexEnch> void load(T enchantment) {
        EnchantmentWrapper.registerEnchantment(enchantment);
    }
}
