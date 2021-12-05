package io.github.simplexdev.api.func;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface VoidSupplier {
    void get();

    default VoidSupplier after(@NotNull VoidSupplier supplier) {
        supplier.get();
        return this;
    }
}
