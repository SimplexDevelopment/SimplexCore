package io.github.simplexdev.simplexcore.math;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public final class Cuboid {
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

    public void generate(Location location, Material material) {
        Consumer<BukkitTask> task = bukkitTask -> {
            int t1 = location.getBlockX();
            int t2 = location.getBlockY();
            int t3 = location.getBlockZ();

            int a = t1 + x;
            int b = t2 + y;
            int c = t3 + z;

            for (int currentX = t1; currentX < a; currentX++) {
                for (int currentY = t2; currentY < b; currentY++) {
                    for (int currentZ = t3; currentZ < c; currentZ++) {
                        location.getWorld().getBlockAt(currentX, currentY, currentZ).setType(material);
                    }
                }
            }

        };

        SimplexCorePlugin.getInstance().getScheduler().runTaskLaterAsynchronously(SimplexCorePlugin.getInstance(), task, SimplexCorePlugin.getInstance().getTimeValues().SECOND());
    }
}
