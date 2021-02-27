package io.github.simplexdev.simplexcore.particle;

import io.github.simplexdev.api.IParticleEffect;
import org.bukkit.*;

import java.util.*;

public class ParticleFactory {
    private float size = 2F;
    private long duration = 20L * 15L;
    private final Set<Particle> particleSet = new HashSet<>();
    private final Map<Particle, Color> particleColorMap = new HashMap<>();
    private final Set<Effect> particleEffects = new HashSet<>();

    public ParticleFactory() {
    }

    public ParticleFactory setSize(float size) {
        this.size = size;
        return this;
    }

    public ParticleFactory setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public ParticleFactory setParticles(Particle... particles) {
        Collections.addAll(particleSet, particles);
        return this;
    }

    public ParticleFactory setParticleColor(Particle particle, Color color) {
        particleColorMap.put(particle, color);
        return this;
    }

    /**
     * Sets a color for more than one particle.
     * @param color
     * @param particles
     * @return
     */
    public ParticleFactory setMultipleParticleColors(Color color, Particle... particles) {
        for (Particle particle : particles) {
            particleColorMap.put(particle, color);
        }
        return this;
    }

    public ParticleFactory setParticleEffects(Effect... effects) {
        particleEffects.addAll(Arrays.asList(effects));
        return this;
    }

    public IParticleEffect create() {
        return new IParticleEffect() {
            @Override
            public Set<Particle> getParticles() {
                return particleSet;
            }

            @Override
            public Map<Particle, Color> getParticleColors() {
                return particleColorMap;
            }

            @Override
            public Set<Effect> getEffects() {
                return particleEffects;
            }

            @Override
            public Float getSize() {
                return size;
            }

            @Override
            public Long getDuration() {
                return duration;
            }
        };
    }

    // TODO: Needs work :)
    public void spawnParticle(IParticleEffect effect, Location location) {
        World world = location.getWorld();
        effect.getParticles().forEach(particle -> {
           Color color = effect.getParticleColors().get(particle);
           world.spawnParticle(particle, location, 20);
        });
    }
}
