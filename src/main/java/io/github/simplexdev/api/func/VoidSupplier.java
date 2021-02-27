package io.github.simplexdev.api.func;

@FunctionalInterface
public interface VoidSupplier {
    void get();

    default VoidSupplier after(VoidSupplier supplier) {
        supplier.get();
        return this;
    }
}
