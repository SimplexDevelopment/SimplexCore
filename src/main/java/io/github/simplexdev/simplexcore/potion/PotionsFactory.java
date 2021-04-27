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

public final class PotionsFactory {
    private final Player player;
    private final Map<Player, ICompoundEffect> map = new HashMap<>();

    public PotionsFactory(Player player) {
        this.player = player;
    }

    public static ICompoundEffect compoundEffect(SimplexModule<?> plugin, String name, int duration, int amplifier, PotionEffectType... effects) {
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

    public void applyCompoundEffect(ICompoundEffect effect) {
        effect.getEffects().forEach(item -> {
            item.apply(player);
        });
        map.put(player, effect);
    }

    public boolean hasPotionEffect(ICompoundEffect effect) {
        return (map.containsKey(player) && map.get(player).equals(effect));
    }
}
