package io.github.simplexdev.simplexcore.math;

public enum Size {
    SMALL(1, 1, 1),
    MEDIUM(3, 3, 3),
    LARGE(5, 5, 5),
    EXTRA_LARGE(10, 10, 10),
    HUGE(20, 20, 20),
    ENORMOUS(50, 50, 50);

    int x, y, z;

    Size(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }
}
