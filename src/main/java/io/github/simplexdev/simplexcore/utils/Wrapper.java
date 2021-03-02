package io.github.simplexdev.simplexcore.utils;

import io.github.simplexdev.api.func.VoidSupplier;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public final class Wrapper<T> {
    protected T bean;

    private Wrapper(T bean) {
        this.bean = bean;
    }

    public void set(T bean) {
        this.bean = bean;
    }

    public T get() {
        return bean;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(get()).toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Wrapper<?>)) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        return new EqualsBuilder().append(((Wrapper<?>) obj).get(), get()).isEquals();
    }

    @Override
    public String toString() {
        return get().toString();
    }

    public static <T> Wrapper<T> of(T object) {
        return new Wrapper<>(object);
    }

    public final <P> P as(Function<? super Wrapper<T>, P> function) {
        return function.apply(this);
    }

    public final <R> Wrapper<R> flatMap(Function<? super T, ? extends Wrapper<? extends R>> function) {
        return new Wrapper<>(function.apply(get()).get());
    }

    public final <R> Wrapper<R> map(Function<? super T, ? extends R> function) {
        return new Wrapper<>(function.apply(get()));
    }

    public final Wrapper<T> perform(Consumer<? super T> consumer) {
        consumer.accept(get());
        return this;
    }

    public final Wrapper<T> then(VoidSupplier supplier) {
        supplier.get();
        return this;
    }

    public final Wrapper<T> filter(Predicate<? super T> predicate) {
        return predicate.test(get()) ? this : null;
    }
}
