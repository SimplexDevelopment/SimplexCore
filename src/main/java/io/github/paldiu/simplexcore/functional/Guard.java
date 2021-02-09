package io.github.paldiu.simplexcore.functional;

@FunctionalInterface
public interface Guard {
    void verify();

    default Guard after(Guard clause) {
        clause.verify();
        return this;
    }
}
