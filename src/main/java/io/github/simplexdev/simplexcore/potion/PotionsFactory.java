package io.github.simplexdev.simplexcore.potion;

import io.github.simplexdev.api.IPotionEffect;
import io.github.simplexdev.simplexcore.utils.Utilities;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;

import java.util.stream.Collectors;

public final class PotionsFactory {
    private PotionsFactory() {

    }

    public static void applyEffect(LivingEntity entity, PotionEffect... effect) {
        entity.addPotionEffects(Utilities.stream(effect).collect(Collectors.toSet()));
    }

    public static void applyEffect(LivingEntity entity, IPotionEffect... effect) {
        Utilities.forEach(effect, sim -> sim.getEffect().apply(entity)); // Interesting, not how it will be done in the end though.
    }
}
