package io.github.paldiu.simplexcore.structures;

import io.github.paldiu.simplexcore.math.Size;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;

public interface IStructure {
    NamespacedKey getNamespacedKey();

    String getName();

    int getId();

    Location getLocation();

    World getWorld();

    boolean shouldGenerateNaturally();

    void generate(Location location, World world);

    void generate(Location location, World world, boolean generateNaturally);

    Size getApproximateSize();

    Block[] getBlocks();
}
