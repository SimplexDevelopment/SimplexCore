package io.github.simplexdev.simplexcore.particle;

import io.github.simplexdev.api.IParticleEffect;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public final class ParticleFactory {
    private final SimplexModule<?> plugin;
    private float size = 2F;
    private int duration = 20 * 15;
    private Particle particle;
    private Color color;
    private final Set<PotionEffect> particleEffects = new HashSet<>();

    @Nullable
    private BlockData blockData;
    @Nullable
    private ItemStack itemData;
    @Nullable
    private Particle.DustOptions dustOptions;
    @Nullable
    private Double xDirection, yDirection, zDirection;
    @Nullable
    private Double red, green, blue;

    public ParticleFactory(SimplexModule<?> plugin) {
        this.plugin = plugin;
    }

    @NotNull
    public ParticleFactory setSize(float size) {
        this.size = size;
        return this;
    }

    @NotNull
    public ParticleFactory setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    @NotNull
    public ParticleFactory setParticle(Particle particle) {
        this.particle = particle;
        return this;
    }

    @NotNull
    public ParticleFactory setParticleColor(Particle particle, Color color) {
        this.color = color;
        return this;
    }

    @NotNull
    public ParticleFactory setParticleEffects(PotionEffect... effects) {
        particleEffects.addAll(Arrays.asList(effects));
        return this;
    }

    @NotNull
    public <T> ParticleFactory setData(T particleData) {
        if (particleData instanceof BlockData) {
            this.blockData = (BlockData) particleData;
            return this;
        } else if (particleData instanceof ItemStack) {
            this.itemData = (ItemStack) particleData;
            return this;
        } else if (particleData instanceof Particle.DustOptions) {
            this.dustOptions = (Particle.DustOptions) particleData;
            return this;
        } else {
            plugin.getLogger().warning("Unknown particle data; ignoring.");
            return this;
        }
    }

    public ParticleFactory setDirectionalValues(double xDirection, double yDirection, double zDirection) {
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.zDirection = zDirection;
        return this;
    }

    @NotNull
    public ParticleFactory setRGBValues(double red, double green, double blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    @NotNull
    public IParticleEffect create() {
        return new IParticleEffect() {
            @Override
            public Particle getParticle() {
                return particle;
            }

            @Override
            public Color getParticleColor() {
                return color;
            }

            @Override
            public Set<PotionEffect> getEffects() {
                return particleEffects;
            }

            @Override
            public Float getSize() {
                return size;
            }

            @Override
            public Integer getDuration() {
                return duration;
            }
        };
    }

    public void spawnParticleCloud(IParticleEffect effect, @NotNull Location location) {
        World world = location.getWorld();
        if (world == null) return;
        AreaEffectCloud cloud = (AreaEffectCloud) world.spawnEntity(location, EntityType.AREA_EFFECT_CLOUD);
        cloud.setDuration(effect.getDuration());
        cloud.setParticle(effect.getParticle());
        cloud.setColor(effect.getParticleColor());
        cloud.setRadius(size);
        effect.getEffects().forEach(potionEffect -> {
            cloud.addCustomEffect(potionEffect, true);
        });
        cloud.setPersistent(true);
    }

    public void spawnDirectionalParticle(Particle particle, @NotNull Location location, double xDirection, double yDirection, double zDirection) {
        World world = location.getWorld();
        if (world == null) return;

        world.spawnParticle(particle, location, 0, xDirection, yDirection, zDirection);
    }

    public void spawnRedstoneParticle(@NotNull Location location, int count, Particle.DustOptions options) {
        World world = location.getWorld();
        if (world == null) return;

        world.spawnParticle(Particle.REDSTONE, location, count, options);
    }

    @Contract("_, _ -> new")
    public static Particle.@NotNull DustOptions options(@NotNull IParticleEffect effect, float size) {
        return new Particle.DustOptions(effect.getParticleColor(), size);
    }

    @Contract("_, _, _, _ -> new")
    public static Particle.@NotNull DustOptions options(int red, int green, int blue, float size) {
        if (red > 255) {
            red = 255;
        }
        if (green > 255) {
            green = 255;
        }
        if (blue > 255) {
            blue = 255;
        }
        return new Particle.DustOptions(Color.fromRGB(red, green, blue), size);
    }

    public void spawnParticle(IParticleEffect effect, @NotNull Location location, int count) {
        World world = location.getWorld();
        if (world == null) return;

        if (ValuedTypes.dust.contains(effect.getParticle())) {
            world.spawnParticle(effect.getParticle(), location, count, dustOptions);
        }

        if (ValuedTypes.directional.contains(effect.getParticle())) {
            if (xDirection == null) {
                xDirection = 1.0;
            }
            if (yDirection == null) {
                yDirection = 1.0;
            }
            if (zDirection == null) {
                zDirection = 1.0;
            }
            world.spawnParticle(effect.getParticle(), location, 0, xDirection, yDirection, zDirection);
        }

        if (ValuedTypes.item.contains(effect.getParticle())) {
            world.spawnParticle(effect.getParticle(), location, count, itemData);
        }

        if (ValuedTypes.material.contains(effect.getParticle())) {
            world.spawnParticle(effect.getParticle(), location, count, blockData);
        }

        if (ValuedTypes.spell.contains(effect.getParticle())) {
            if (red == null || red > 255.0) {
                this.red = 255.0;
            }
            if (green == null || green > 255.0) {
                this.green = 255.0;
            }
            if (blue == null || blue > 255.0) {
                this.blue = 255.0;
            }

            world.spawnParticle(effect.getParticle(), location, count, red, green, blue);
        }
    }

    public SimplexModule<?> getPlugin() {
        return plugin;
    }

    private static final class ValuedTypes {
        public static final List<Particle> dust = new ArrayList<>(){{
            add(Particle.REDSTONE);
        }};
        public static final List<Particle> item = new ArrayList<>(){{
            add(Particle.ITEM_CRACK);
        }};
        public static final List<Particle> material = new ArrayList<>(){{
            add(Particle.BLOCK_CRACK);
            add(Particle.BLOCK_DUST);
            add(Particle.FALLING_DUST);
        }};
        public static final List<Particle> spell = new ArrayList<>(){{
            add(Particle.SPELL_MOB);
            add(Particle.SPELL_MOB_AMBIENT);

        }};
        public static final List<Particle> directional = new ArrayList<>(){{
            add(Particle.EXPLOSION_NORMAL);
            add(Particle.FIREWORKS_SPARK);
            add(Particle.WATER_BUBBLE);
            add(Particle.WATER_WAKE);
            add(Particle.CRIT);
            add(Particle.CRIT_MAGIC);
            add(Particle.SMOKE_NORMAL);
            add(Particle.SMOKE_LARGE);
            add(Particle.PORTAL);
            add(Particle.ENCHANTMENT_TABLE);
            add(Particle.FLAME);
            add(Particle.CLOUD);
            add(Particle.DRAGON_BREATH);
            add(Particle.END_ROD);
            add(Particle.DAMAGE_INDICATOR);
            add(Particle.TOTEM);
            add(Particle.SPIT);
            add(Particle.SQUID_INK);
            add(Particle.BUBBLE_POP);
            add(Particle.BUBBLE_COLUMN_UP);
            add(Particle.NAUTILUS);
            add(Particle.CAMPFIRE_COSY_SMOKE);
            add(Particle.CAMPFIRE_SIGNAL_SMOKE);
            add(Particle.SOUL_FIRE_FLAME);
            add(Particle.SOUL);
            add(Particle.REVERSE_PORTAL);
        }};
    }
}
