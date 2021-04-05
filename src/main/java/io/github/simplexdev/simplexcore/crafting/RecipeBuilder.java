package io.github.simplexdev.simplexcore.crafting;

import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.Utilities;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class RecipeBuilder {
    private final SimplexModule<?> plugin;

    public RecipeBuilder(SimplexModule<?> plugin) {
        this.plugin = plugin;
    }

    public final Worker of(ItemStack result, String recipeName, boolean isShaped) {
        return new Worker(result, recipeName, isShaped);
    }

    private final class Worker {
        private final Map<Character, Material> ingredients = new HashMap<>();
        private final ItemStack stack;
        private final NamespacedKey key;
        private final boolean shaped;
        private final List<Material> materials = new ArrayList<>();
        private String[] shape = {"", "", ""};

        public Worker(ItemStack stack, String name, boolean isShaped) {
            this.stack = stack;
            this.key = new NamespacedKey(plugin, name);
            this.shaped = isShaped;
        }

        private ShapelessRecipe nosha() {
            ShapelessRecipe recipe = new ShapelessRecipe(key, stack);
            if (materials.size() > 9 || materials.isEmpty()) return recipe;
            materials.forEach(recipe::addIngredient);
            materials.clear();
            return recipe;
        }

        private ShapedRecipe sha() {
            ShapedRecipe recipe = new ShapedRecipe(key, stack);
            if (ingredients.isEmpty()) return recipe;
            recipe.shape(shape);
            ingredients.forEach(recipe::setIngredient);
            ingredients.clear();
            return recipe;
        }

        public void make() {
            plugin.getServer().addRecipe(shaped ? sha() : nosha());
        }

        /**
         * This is for shaped crafting.
         *
         * @param identifier The specific identifier
         * @param ingredient The ingredient represented by the identifier.
         * @return
         */
        public Worker addIngredient(@NotNull Character identifier, @NotNull Material ingredient) {
            ingredients.put(identifier, ingredient);
            return this;
        }

        public Worker setShape(@Nullable String top, @Nullable String middle, @Nullable String bottom) {
            String a = "";
            String b = "";
            String c = "";
            if (top != null) {
                a = top;
            }
            if (middle != null) {
                b = middle;
            }
            if (bottom != null) {
                c = bottom;
            }

            shape = new String[]{a, b, c};
            return this;
        }

        /**
         * This is for shapeless crafting.
         *
         * @param ingredients any number (up to nine) of ingredients required to craft this recipe.
         * @return An appendable instance of this class.
         */
        public Worker addIngredients(Material... ingredients) {
            Utilities.forEach(ingredients, materials::add);
            return this;
        }
    }
}
