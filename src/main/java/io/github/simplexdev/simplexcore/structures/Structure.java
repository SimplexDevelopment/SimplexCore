package io.github.simplexdev.simplexcore.structures;

import io.github.simplexdev.api.IStructure;
import io.github.simplexdev.simplexcore.math.Size;
import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.structures.block.NBTBlock;
import io.github.simplexdev.simplexcore.structures.exception.InvalidSchematic;
import io.github.simplexdev.simplexcore.structures.exception.SchematicNotLoaded;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.util.Vector;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Structure implements IStructure {
    private final SimplexModule<?> plugin;
    private File schematic;
    private short width, height, length = 0;
    private byte[] blockdatacontainer;

    private HashMap<Vector, NBTBlock> nbtBlocks = new HashMap<>();
    private HashMap<Integer, BlockData> blocks = new HashMap<>();

    public Structure(SimplexModule<?> plugin, File schematic) {
        this.plugin = plugin;
        this.schematic = schematic;
    }

    public void load() throws InvalidSchematic {

    }

    public void paste(Location loc, PasteType pasteType) throws SchematicNotLoaded {
        if (width == 0 || height == 0 || length == 0 || blocks.isEmpty()) {
            throw new SchematicNotLoaded("Schematic not loaded please load schematic first...");
        }
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
