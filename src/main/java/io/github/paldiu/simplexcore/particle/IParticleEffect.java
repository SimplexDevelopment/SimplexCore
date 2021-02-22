package io.github.paldiu.simplexcore.particle;

import org.bukkit.Color;
import org.bukkit.Particle;

public interface IParticleEffect {
    Particle[] getParticles();

    Color[] getParticleColors();

    Float getSize();

    Long getDuration();
}
