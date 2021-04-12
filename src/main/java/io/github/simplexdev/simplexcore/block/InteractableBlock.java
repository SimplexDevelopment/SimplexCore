package io.github.simplexdev.simplexcore.block;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class InteractableBlock {
    private final Block block;
    private final Location location;
    private final Material material;
    
    public InteractableBlock(Location location) {
        this.block = location.getBlock();
        this.location = block.getLocation();
        this.material = block.getType();
    }

    public InteractableBlock(Block block) {
        this.block = block;
        this.location = block.getLocation();
        this.material = block.getType();
    }

    public void test() {

    }
}
