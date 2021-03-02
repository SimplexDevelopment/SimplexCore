package io.github.simplexdev.simplexcore.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;
import java.util.Set;

public final class ReflectionTools {
    public ReflectionTools() {
    }

    @NotNull
    public Reflections reflect(Class<?> loadFrom) {
        return new Reflections(loadFrom.getName());
    }

    @NotNull
    public Set<Class<?>> getAnnotatedClasses(Class<?> loadFrom, Class<? extends Annotation> annotation) {
        return new Reflections(loadFrom.getName()).getTypesAnnotatedWith(annotation);
    }

    @Nullable
    public Field getField(Class<?> cls, String name) {
        try {
            Field field = cls.getField(name);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException ignored) {
            return null;
        }
    }

    @Nullable
    public Constructor<?> getConstructor(Class<?> cls, String name, Class<?>... initializers) {
        try {
            return cls.getDeclaredConstructor(initializers);
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

    @Nullable
    public Wrapper<?> initConstructor(Constructor<?> constructor, Object... initializers) {
        try {
            return Wrapper.of(constructor.newInstance(initializers));
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }

    @Nullable
    public Method getMethod(Class<?> clazz, String name, Class<?>... params) {
        try {
            return Wrapper.of(clazz.getMethod(name, params))
                    .perform(obj -> obj.setAccessible(true))
                    .get();
        } catch (NoSuchMethodException e) {
            return null;
        }
    }
}
