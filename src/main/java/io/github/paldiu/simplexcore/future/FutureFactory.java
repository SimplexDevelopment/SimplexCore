package io.github.paldiu.simplexcore.future;

import java.util.LinkedList;
import java.util.concurrent.CompletionStage;

public class FutureFactory<T> {
    private final T object;
    private final LinkedList<CompletionStage<T>> tree = new LinkedList<>();

    public FutureFactory(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
