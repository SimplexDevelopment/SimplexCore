package io.github.simplexdev.simplexcore.crafting;

import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.Utilities;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class ItemBuilder {
    private final SimplexModule<?> plugin;

    public ItemBuilder(SimplexModule<?> plugin) {
        this.plugin = plugin;
    }

    public ItemStack createNoBounds(Material material) {
        return new ItemStack(material, 1);
    }

    public ItemStack createWithMeta(Material material, ItemMeta metaData) {
        ItemStack stack = new ItemStack(material, 1);
        stack.setItemMeta(metaData);
        return stack;
    }

    public Worker itemBuilder(Material material) {
        return new Worker(new ItemStack(material, 1));
    }

    public AttributeModifier add(String name, double amount, boolean scalar) {
        if (scalar) {
            return new AttributeModifier(name, amount, AttributeModifier.Operation.ADD_SCALAR);
        }
        return new AttributeModifier(name, amount, AttributeModifier.Operation.ADD_NUMBER);
    }

    public AttributeModifier multiply(String name, double amount) {
        return new AttributeModifier(name, amount, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
    }

    protected final class Worker {
        private final ItemStack stack;
        private final ItemMeta meta;

        public Worker(ItemStack stack) {
            this.stack = stack;
            this.meta = stack.getItemMeta();
        }

        public final Worker addLore(String... lore) {
            meta.setLore(Arrays.asList(lore));
            stack.setItemMeta(meta);
            return this;
        }

        public final Worker setName(String customName) {
            meta.setDisplayName(customName);
            stack.setItemMeta(meta);
            return this;
        }

        public final Worker addEnchant(Enchantment enchantment, int level) {
            meta.addEnchant(enchantment, level, true);
            stack.setItemMeta(meta);
            return this;
        }

        public final Worker addAttribute(Attribute attribute, AttributeModifier modifier) {
            meta.addAttributeModifier(attribute, modifier);
            stack.setItemMeta(meta);
            return this;
        }

        public final Worker addItemFlags(ItemFlag... flags) {
            meta.addItemFlags(flags);
            stack.setItemMeta(meta);
            return this;
        }

        public final ItemStack get() {
            return stack;
        }
    }
}
