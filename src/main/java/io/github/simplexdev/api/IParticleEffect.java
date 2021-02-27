package io.github.simplexdev.api;

import org.bukkit.Color;
import org.bukkit.Effect;
import org.bukkit.Particle;

import java.util.Map;
import java.util.Set;

public interface IParticleEffect {
    Set<Particle> getParticles();

    Map<Particle, Color> getParticleColors();

    Set<Effect> getEffects();

    Float getSize();

    Long getDuration();
}
