package io.github.simplexdev.simplexcore.math;

import io.github.simplexdev.simplexcore.SimplexCorePlugin;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitTask;

import java.util.function.Consumer;

public final class Pyramid {

    private final int height;
    private Location location;
    private Material material;

    public Pyramid(int height, Location location, Material material) {

        this.height = height;
        this.location = location;
        this.material = material;
    }

    public Pyramid(Size size, Location location, Material material) {

        this.height = size.getY();
        this.location = location;
        this.material = material;
    }


    public void setLocation(Location location) {
        this.location = location;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public void generate() {

        Consumer<BukkitTask> consumer = bukkitTask -> {

            System.out.println(height % 2 == 0);

            if (height % 2 == 0)
                createEvenFull();

            else
                createOddFull();
        };

        SimplexCorePlugin.getInstance().getScheduler().runTaskLater(SimplexCorePlugin.getInstance(), consumer, 20L);
    }

    private void createOddFull() {
        int locX = location.getBlockX();
        int locY = location.getBlockY();
        int locZ = location.getBlockZ();

        int currentArea = 1;

        for (int y = height; y > 0; y--) {
            for (int x = -(int) (Math.floor(currentArea / (double) 2)); x <= (int) Math.floor(currentArea / (double) 2); x++) {
                for (int z = -(int) (Math.floor(currentArea / (double) 2)); z <= (int) Math.floor(currentArea / (double) 2); z++) {

                    Block block = location.getWorld().getBlockAt(locX + x, locY + y, locZ + z);

                    block.setType(material);

                }
            }
            currentArea += 2;
        }
    }

    private void createEvenFull() {
        createEvenSide(1);

        location.setX(location.getBlockX() + 1);
        createEvenSide(2);

        location.setZ(location.getBlockZ() + 1);
        createEvenSide(3);

        location.setX(location.getBlockX() - 1);
        createEvenSide(4);
    }

    private void createEvenSide(int quadrant) {
        int locX = location.getBlockX();
        int locY = location.getBlockY();
        int locZ = location.getBlockZ();

        int currentArea = 1;

        int x = currentArea;
        int z = currentArea;

        switch (quadrant) {
            case 1:
                x = +x;
                z = +z;

                break;
            case 2:
                x = -x;
                z = +z;

                break;
            case 3:

                x = -x;
                z = -z;
                break;
            case 4:

                x = +x;
                z = -z;

                break;
        }

        for (int y2 = locY + height; y2 > locY; y2--) {

            for (int x2 = x; x2 != 0; x2 = decreaseOrIncrease(x2)) {

                for (int z2 = z; z2 != 0; z2 = decreaseOrIncrease(z2)) {

                    Block block = location.getWorld().getBlockAt(locX + x2, y2, locZ + z2);
                    block.setType(material);
                }

            }

            x += x > 0 ? 1 : -1;
            z += z > 0 ? 1 : -1;
        }
    }

    private int decreaseOrIncrease(int x) {
        if (x > 0)
            x--;
        else if (x < 0)
            x++;

        return x;
    }
}