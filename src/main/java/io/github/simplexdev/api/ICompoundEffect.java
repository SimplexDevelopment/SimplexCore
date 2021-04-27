package io.github.simplexdev.api;

import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * This interface is intended for creating custom potion effects.
 */
public interface ICompoundEffect {
    @NotNull
    List<PotionEffect> getEffects();

    @NotNull
    String getName();

    @NotNull
    Integer getDuration();

    @Nullable
    Particle getParticleType();

    @Nullable
    Color getParticleColor();

    @NotNull
    NamespacedKey getNamespacedKey();
}
