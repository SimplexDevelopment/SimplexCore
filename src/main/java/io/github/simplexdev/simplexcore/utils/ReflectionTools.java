package io.github.simplexdev.simplexcore.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;

public final class ReflectionTools {
    @NotNull
    public static Reflections reflect(Class<?> loadFrom) {
        return new Reflections(loadFrom.getName());
    }

    @NotNull
    public static Set<Class<?>> getAnnotatedClasses(Class<?> loadFrom, Class<? extends Annotation> annotation) {
        return new Reflections(loadFrom.getName()).getTypesAnnotatedWith(annotation);
    }

    @Nullable
    public static <T> Field getField(Class<T> cls, String name) {
        try {
            return asAccessible(cls.getField(name));
        } catch (NoSuchFieldException ignored) {
            return null;
        }
    }

    @Nullable
    public static <T> Field getDeclaredField(Class<T> cls, String name) {
        try {
            return asAccessible(cls.getDeclaredField(name));
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    @Nullable
    public static <T> Constructor<T> getConstructor(Class<T> cls, Class<?>... initializers) {
        try {
            return asAccessible(cls.getConstructor(initializers));
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

    @Nullable
    public static <T> Constructor<T> getDeclaredConstructor(Class<T> cls, Class<?>... initializers) {
        try {
            return asAccessible(cls.getDeclaredConstructor(initializers));
        } catch (NoSuchMethodException ignored) {
            return null;
        }
    }

    @Nullable
    public static <T> T initConstructor(Constructor<? extends T> constructor, Object... initializers) {
        try {
            return constructor.newInstance(initializers);
        } catch (ReflectiveOperationException e) {
            return null;
        }
    }

    @Nullable
    public static <T> Method getMethod(Class<T> clazz, String name, Class<?>... params) {
        try {
            return asAccessible(clazz.getMethod(name, params));
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    @Nullable
    public static <T> Method getDeclaredMethod(Class<T> clazz, String name, Class<?>... params) {
        try {
            return asAccessible(clazz.getDeclaredMethod(name, params));
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }

    @NotNull
    @Contract(pure = true)
    public static <T extends AccessibleObject> T asAccessible(T object) {
        object.setAccessible(true);
        return object;
    }
}
