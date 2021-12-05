package io.github.simplexdev.simplexcore.crafting;

import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.Utilities;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.jetbrains.annotations.Contract;
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

    /**
     * Create a new RecipeBuilder Worker.
     * @param result The resulting item.
     * @param recipeName The name of the recipe.
     *                   This should be separated by "_" where spaced would be used.
     * @param isShaped Whether or not the recipe is shaped or shapeless.
     * @return A new appendable RecipeBuilder Worker instance based on the given parameters.
     */
    @Contract("_, _, _ -> new")
    public @NotNull Worker newRecipe(ItemStack result, String recipeName, boolean isShaped) {
        return new Worker(result, recipeName, isShaped);
    }

    protected final class Worker {
        private final Map<Character, Material> ingredients = new HashMap<>();
        private final ItemStack stack;
        private final NamespacedKey key;
        private final boolean shaped;
        private final List<Material> materials = new ArrayList<>();
        private String[] shape = {"", "", ""};

        /**
         * @param stack The item to build a recipe for. This may be an item with custom metadata,
         *              or even an item without a native crafting recipe.
         * @param name The recipe name; it should be all lowercase,
         *              separated by underscores where spaced would be used.
         * @param isShaped If the recipe is shaped or shapeless.
         */
        public Worker(ItemStack stack, String name, boolean isShaped) {
            this.stack = stack;
            this.key = new NamespacedKey(plugin, name);
            this.shaped = isShaped;
        }

        private @NotNull ShapelessRecipe nosha() {
            ShapelessRecipe recipe = new ShapelessRecipe(key, stack);
            if (materials.size() > 9 || materials.isEmpty()) return recipe;
            materials.forEach(recipe::addIngredient);
            materials.clear();
            return recipe;
        }

        private @NotNull ShapedRecipe sha() {
            ShapedRecipe recipe = new ShapedRecipe(key, stack);
            if (ingredients.isEmpty()) return recipe;
            recipe.shape(shape);
            ingredients.forEach(recipe::setIngredient);
            ingredients.clear();
            return recipe;
        }

        /**
         * Creates the recipe and registers it with Bukkit.
         */
        public void create() {
            plugin.getServer().addRecipe(shaped ? sha() : nosha());
        }

        /**
         * This is for shaped crafting. Please use {@link Worker#addIngredients(Material...)}
         * for shapeless crafting.
         *
         * @param identifier The specific identifier
         * @param ingredient The ingredient represented by the identifier.
         * @return An appendable worker instance.
         */
        public Worker addIngredient(@NotNull Character identifier, @NotNull Material ingredient) {
            ingredients.put(identifier, ingredient);
            return this;
        }

        /**
         * Sets the shape for the crafting recipe. This is not required if the recipe is shapeless.
         * @param top The top three slots represented by "___" where any specific letter
         *               is represented by the relative ingredient identifier.
         * @param middle The middle three slots represented by "___" where any specific letter
         *               is represented by the relative ingredient identifier.
         * @param bottom The bottom three slots represented by "___" where any specific letter
         *               is represented by the relative ingredient identifier.
         * @return An appendable worker instance.
         */
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
         * This is for shapeless crafting. Please use {@link Worker#addIngredient(Character, Material)}
         * for shaped crafting.
         *
         * @param ingredients any number (up to nine) of ingredients required to craft this recipe.
         * @return An appendable worker instance.
         */
        public Worker addIngredients(Material... ingredients) {
            Utilities.forEach(ingredients, materials::add);
            return this;
        }
    }
}
