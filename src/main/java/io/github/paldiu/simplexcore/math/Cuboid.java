package io.github.paldiu.simplexcore.math;

import org.bukkit.Location;

public class Cuboid {
    private final int x, y, z;

    public Cuboid() {
        this(3, 3, 3);
    }

    public Cuboid(int xSize, int ySize, int zSize) {
        this.x = xSize;
        this.y = ySize;
        this.z = zSize;
    }

    public Cuboid(Size size) {
        this(size.getX(), size.getY(), size.getZ());
    }

    public void generate(Location location) {
        int t1 = location.getBlockX();
        int t2 = location.getBlockY();
        int t3 = location.getBlockZ();
    }
}
