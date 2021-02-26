package io.github.simplexdev.simplexcore.utils;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

public final class Trio<A, B, C> {
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(getPrimary())
                .append(getSecondary())
                .append(getTertiary())
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Trio<?, ?, ?>)) {
            return false;
        }

        if (obj.equals(this)) {
            return true;
        }

        Trio<?, ?, ?> trio = (Trio<?, ?, ?>) obj;
        return new EqualsBuilder().append(getPrimary(), trio.getPrimary())
                .append(getSecondary(), trio.getSecondary())
                .append(getTertiary(), trio.getTertiary())
                .isEquals();
    }

    @Override
    public String toString() {
        return getPrimary().toString() +
                "\n" +
                getSecondary().toString() +
                "\n" +
                getTertiary().toString();
    }
}
