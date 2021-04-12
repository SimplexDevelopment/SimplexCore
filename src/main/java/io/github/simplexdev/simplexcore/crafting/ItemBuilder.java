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

    /**
     * Create a new singular ItemStack.
     * @param material The material for the item.
     * @return A new ItemStack from the provided material.
     */
    public ItemStack createNoBounds(Material material) {
        return new ItemStack(material, 1);
    }

    /**
     * Create a new ItemStack.
     * @param material The material for the item.
     * @param amount The amount of items the stack should represent.
     * @return A new ItemStack from the provided material.
     */
    public ItemStack createNoBounds(Material material, int amount) {
        return new ItemStack(material, amount);
    }

    /**
     * Create a new singular ItemStack with the specified ItemMeta.
     * @param material The material for the item.
     * @param metaData The ItemMeta to use for the item.
     * @return A new ItemStack from the provided material with the specified ItemMeta.
     */
    public ItemStack createWithMeta(Material material, ItemMeta metaData) {
        ItemStack stack = new ItemStack(material, 1);
        stack.setItemMeta(metaData);
        return stack;
    }

    /**
     * Create a new ItemStack with the specified ItemMeta.
     * @param material The material for the item.
     * @param metaData The ItemMeta to use for the item.
     * @param amount The amount of items the stack should represent.
     * @return A new ItemStack from the provided material with the specified ItemMeta.
     */
    public ItemStack createWithMeta(Material material, ItemMeta metaData, int amount) {
        ItemStack stack = new ItemStack(material, amount);
        stack.setItemMeta(metaData);
        return stack;
    }

    /**
     * Create a new ItemBuilder Worker
     * @param material The material for the item.
     * @return A new appendable ItemBuilder Worker instance based on the given Material.
     */
    public Worker newItem(Material material) {
        return new Worker(new ItemStack(material, 1));
    }

    /**
     * Create a new ItemBuilder Worker
     * @param material The material for the item.
     * @param amount The amount of items the stack should represent.
     * @return A new appendable ItemBuilder Worker instance based on the given Material.
     */
    public Worker newItem(Material material, int amount) {
        return new Worker(new ItemStack(material, amount));
    }

    public Worker editItem(ItemStack stack) {
        return new Worker(stack);
    }

    /**
     * Create a new AttributeModifier for an ItemStack's ItemMeta.
     * @param name The name of the modifier.
     * @param amount The amount to add
     * @param scalar Whether or not it should add as a number or a magnitude.
     * @return A new AttributeModifier.
     */
    public AttributeModifier add(String name, double amount, boolean scalar) {
        if (scalar) {
            return new AttributeModifier(name, amount, AttributeModifier.Operation.ADD_SCALAR);
        }
        return new AttributeModifier(name, amount, AttributeModifier.Operation.ADD_NUMBER);
    }

    /**
     * Create a new AttributeModifier for an ItemStack's ItemMeta.
     * @param name The name of the modifier
     * @param amount The amount to multiply by
     * @return A new AttributeModifier.
     */
    public AttributeModifier multiply(String name, double amount) {
        return new AttributeModifier(name, amount, AttributeModifier.Operation.MULTIPLY_SCALAR_1);
    }

    protected final class Worker {
        private final ItemStack stack;
        private final ItemMeta meta;

        /**
         * @param stack The item to work from.
         */
        public Worker(ItemStack stack) {
            this.stack = stack;
            this.meta = stack.getItemMeta();
        }

        /**
         * Adds lore to the item. New lines should be separate Strings.
         * @param lore The item lore to add.
         * @return An appendable worker instance.
         */
        public final Worker addLore(String... lore) {
            meta.setLore(Arrays.asList(lore));
            stack.setItemMeta(meta);
            return this;
        }

        /**
         * Change the item's name
         * @param customName The new name of the item.
         * @return An appendable worker instance.
         */
        public final Worker setName(String customName) {
            meta.setDisplayName(customName);
            stack.setItemMeta(meta);
            return this;
        }

        /**
         * Add an enchantment to the item
         * @param enchantment The enchantment to add
         * @param level The level of the enchantment. This is non-restrictive.
         * @return An appendable worker instance.
         */
        public final Worker addEnchant(Enchantment enchantment, int level) {
            meta.addEnchant(enchantment, level, true);
            stack.setItemMeta(meta);
            return this;
        }

        /**
         * Add an attribute to the item.
         * @param attribute The attribute to add.
         * @param modifier The type of attribute modifier to use.
         * @return An appendable worker instance.
         */
        public final Worker addAttribute(Attribute attribute, AttributeModifier modifier) {
            meta.addAttributeModifier(attribute, modifier);
            stack.setItemMeta(meta);
            return this;
        }

        /**
         * Add item flags to an item.
         * @param flags Any amount of ItemFlags to add to the item.
         * @return An appendable worker instance.
         */
        public final Worker addItemFlags(ItemFlag... flags) {
            meta.addItemFlags(flags);
            stack.setItemMeta(meta);
            return this;
        }

        /**
         * Set the amount of items this stack should represent.
         * @param amount The amount of items this stack should represent.
         * @return An appendable worker instance.
         */
        public final Worker setAmount(int amount) {
            stack.setAmount(amount);
            return this;
        }

        public final Worker setType(Material material) {
            stack.setType(material);
            return this;
        }

        public final Worker setUnbreakable(boolean unbreakable) {
            meta.setUnbreakable(unbreakable);
            return this;
        }

        /**
         * @return The final item.
         */
        public final ItemStack get() {
            return stack;
        }
    }
}
