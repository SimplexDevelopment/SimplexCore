package io.github.simplexdev.api;

import org.bukkit.Color;
import org.bukkit.Particle;

public interface IParticleEffect {
    Particle[] getParticles();

    Color[] getParticleColors();

    Float getSize();

    Long getDuration();
}
