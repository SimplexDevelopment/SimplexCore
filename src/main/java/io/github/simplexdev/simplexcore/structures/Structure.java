package io.github.simplexdev.simplexcore.structures;

import io.github.simplexdev.api.IStructure;
import io.github.simplexdev.simplexcore.math.Size;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.io.File;
import java.util.Map;

public class Structure implements IStructure {
    private final SimplexModule<?> plugin;

    public Structure(SimplexModule<?> plugin) {
        this.plugin = plugin;
    }

    @Override
    public NamespacedKey getNamespacedKey() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Location getLocation() {
        return null;
    }

    @Override
    public World getWorld() {
        return null;
    }

    @Override
    public boolean shouldGenerateNaturally() {
        return false;
    }

    @Override
    public void generate(Location location, World world) {

    }

    @Override
    public void generate(Location location, World world, boolean generateNaturally) {

    }

    @Override
    public Size getApproximateSize() {
        return null;
    }

    @Override
    public Block[] getBlocks() {
        return new Block[0];
    }

    @Override
    public Map<Block, Location> getBlockLocations() {
        return null;
    }

    @Override
    public File getStructureFile() {
        return null;
    }

    public SimplexModule<?> getPlugin() {
        return plugin;
    }
    // TODO: Write this
}
