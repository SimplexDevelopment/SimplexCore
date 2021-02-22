package io.github.paldiu.simplexcore.potion;

import org.bukkit.Color;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This interface is intended for creating custom potion effects.
 */
public interface IPotionEffect {
    @NotNull
    PotionEffect getEffect();

    @NotNull
    PotionEffectType getType();

    @NotNull
    String getName();

    @NotNull
    Long getDuration();

    @Nullable
    Particle getParticleType();

    @Nullable
    Color getParticleColor();

    @NotNull
    NamespacedKey getNamespacedKey();
}
