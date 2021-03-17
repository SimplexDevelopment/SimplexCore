package io.github.simplexdev.api;

import io.github.simplexdev.simplexcore.math.Size;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.io.File;
import java.util.Map;

public interface IStructure {
    NamespacedKey getNamespacedKey();

    String getName();

    /**
     * Gets the location where the structure is supposed to generate.
     * @return Structure Location
     */
    Location getLocation();

    World getWorld();

    boolean shouldGenerateNaturally();

    void generate(Location location, World world);

    void generate(Location location, World world, boolean generateNaturally);

    /**
     * Gets the approximate size of the structure / schematic.
     * This is for natural generation of structures, to ensure it doesn't generate somewhere
     * which is occluded or otherwise populated. This will always round up to the nearest
     * related size, and never down.
     * @return The approximate {@link Size} of the structure.
     */
    Size getApproximateSize();

    Block[] getBlocks();

    Map<Block, Location> getBlockLocations();

    File getStructureFile();
}

