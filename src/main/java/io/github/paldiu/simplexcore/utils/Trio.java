package io.github.paldiu.simplexcore.utils;

public class Trio<A, B, C> {
    private final A primary;
    private final B secondary;
    private final C tertiary;

    public Trio(A primary, B secondary, C tertiary) {
        this.primary = primary;
        this.secondary = secondary;
        this.tertiary = tertiary;
    }

    public A getPrimary() {
        return primary;
    }

    public B getSecondary() {
        return secondary;
    }

    public C getTertiary() {
        return tertiary;
    }
}
