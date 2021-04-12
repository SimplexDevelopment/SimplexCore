package io.github.simplexdev.simplexcore.crafting;

import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class RBImpl {
    private final RecipeBuilder builder;
    private final SimplexModule<?> plugin;
    private final ItemBuilder bldr;

    public RBImpl(SimplexModule<?> plugin) {
        this.plugin = plugin;
        this.builder = new RecipeBuilder(plugin);
        this.bldr = new ItemBuilder(plugin);

        chainMailBoots();
        customWand();
    }

    public void chainMailBoots() {
        ItemStack is = bldr.createNoBounds(Material.CHAINMAIL_BOOTS);
        builder.of(is, "chainmail_boots", true)
                .addIngredient('c', Material.CHAIN)
                .addIngredient('a', Material.AIR)
                .setShape("aaa", "cac", "cac")
                .create();
    }

    public void customWand() {
        ItemStack is = bldr.newItem(Material.BLAZE_ROD)
                .setName("Magic Wand")
                .addLore("This wand is magical.")
                .addEnchant(Enchantment.KNOCKBACK, 10)
                .addAttribute(Attribute.GENERIC_ATTACK_KNOCKBACK,
                        bldr.multiply("generic_attack_knockback", 24.0))
                .get();
        builder.of(is, "magic_wand", true)
                .addIngredient('b', Material.BLAZE_ROD)
                .addIngredient('n', Material.NETHER_STAR)
                .addIngredient('a', Material.AIR)
                .setShape("aan", "aba", "baa")
                .create();
    }
}
