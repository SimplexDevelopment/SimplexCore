package io.github.simplexdev.simplexcore.structures;

import io.github.simplexdev.simplexcore.module.SimplexModule;
import io.github.simplexdev.simplexcore.structures.block.NBTBlock;
import io.github.simplexdev.simplexcore.structures.exception.InvalidSchematic;
import io.github.simplexdev.simplexcore.structures.exception.SchematicNotLoaded;
import org.bukkit.Location;
import org.bukkit.block.data.BlockData;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.HashMap;
import java.util.Vector;

public final class Structure {
    public Plugin plugin;
    private File schematic;
    private short width = 0;
    private short height = 0;
    private short length = 0;
    private byte[] blockdatacontainer;

    private HashMap<Vector, NBTBlock> nbtBlocks = new HashMap<>();
    private HashMap<Integer, BlockData> blocks = new HashMap<>();

    public Structure(SimplexModule<?> plugin,File schematic)
    {
        this.plugin = plugin.getPlugin();
        this.schematic = schematic;
    }

    public void load() throws InvalidSchematic
    {

    }

    public void paste(Location loc,PasteType pasteType) throws SchematicNotLoaded
    {
        if (width == 0 || height == 0 || length == 0 || blocks.isEmpty()) {
            throw new SchematicNotLoaded("Schematic not loaded please load schematic first...");
        }


    }
}
