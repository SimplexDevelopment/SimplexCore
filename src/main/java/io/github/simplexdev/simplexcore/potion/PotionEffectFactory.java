package io.github.simplexdev.simplexcore.potion;

import io.github.simplexdev.api.ICompoundEffect;
import io.github.simplexdev.simplexcore.listener.SimplexListener;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.utils.Utilities;
import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class PotionEffectFactory {
    private final Player player;
    private final Map<Player, ICompoundEffect> map = new HashMap<>();

    public PotionEffectFactory(Player player) {
        this.player = player;
    }

    /**
     * Creates a new compound effect with the specified parameters.
     * @param plugin The plugin to use when registering a new NamespacedKey.
     * @param name The effect name
     * @param duration The duration of the effect
     * @param amplifier the amplifier of the effect
     * @param effects The {@link PotionEffectType}(s) you want to be included.
     * @return A new compound effect.
     */
    public static @NotNull ICompoundEffect compoundEffect(SimplexModule<?> plugin, String name, int duration, int amplifier, PotionEffectType... effects) {
        List<PotionEffect> list = new ArrayList<>();

        Utilities.forEach(effects, effect -> {
            list.add(effect.createEffect(duration, amplifier));
        });

        return new ICompoundEffect() {
            @Override
            public @NotNull List<PotionEffect> getEffects() {
                return list;
            }

            @Override
            public @NotNull String getName() {
                return name;
            }

            @Override
            public @NotNull Integer getDuration() {
                return duration;
            }

            @Override
            public @Nullable Particle getParticleType() {
                return null;
            }

            @Override
            public @Nullable Color getParticleColor() {
                return null;
            }

            @Override
            public @NotNull NamespacedKey getNamespacedKey() {
                return new NamespacedKey(plugin, name);
            }
        };
    }

    /**
     * Creates a new {@link PotionEffect}.
     * @param type The type of potion effect
     * @param duration How long the effect should last
     * @param amplifier How strong the potion is.
     * @return A new {@link PotionEffect}.
     */
    public static @NotNull PotionEffect potionEffect(@NotNull PotionEffectType type, int duration, int amplifier) {
        return type.createEffect(duration, amplifier);
    }

    /**
     * Applies the compound effect to the defined player.
     * @param effect The {@link ICompoundEffect} to apply.
     */
    public void applyCompoundEffect(@NotNull ICompoundEffect effect) {
        effect.getEffects().forEach(player::addPotionEffect);
        map.put(player, effect);
    }

    /**
     * Checks if a player currently has a compound effect.
     * @param effect The {@link ICompoundEffect} to look for
     * @return Whether the player has the compound effect.
     */
    public boolean hasPotionEffect(ICompoundEffect effect) {
        return (map.containsKey(player) && map.get(player).equals(effect));
    }
}
