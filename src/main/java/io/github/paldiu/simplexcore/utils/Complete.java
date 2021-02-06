package io.github.paldiu.simplexcore.utils;

@FunctionalInterface
public interface Complete<Void> {
    void toComplete(Void complete);
}
