package io.github.simplexdev.api;

import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;
import org.bukkit.Particle;
import java.util.Set;

public interface IParticleEffect {
    Particle getParticle();

    Color getParticleColor();

    Set<PotionEffect> getEffects();

    Float getSize();

    Integer getDuration();
}
