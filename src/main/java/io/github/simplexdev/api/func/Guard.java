package io.github.simplexdev.api.func;

@FunctionalInterface
public interface Guard {
    void verify();

    default Guard after(Guard clause) {
        clause.verify();
        return this;
    }
}
