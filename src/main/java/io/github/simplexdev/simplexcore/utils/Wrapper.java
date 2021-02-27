package io.github.simplexdev.simplexcore.utils;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public final class Wrapper<T> {
    protected T bean;

    public Wrapper(T bean) {
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
}
