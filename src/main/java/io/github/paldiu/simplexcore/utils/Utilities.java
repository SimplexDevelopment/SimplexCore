package io.github.paldiu.simplexcore.utils;

import io.github.paldiu.simplexcore.functional.Validate;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Utilities {
    private static <T> Stream<T> feStr(T[] array) {
        return Arrays.stream(array);
    }

    private static <T> Set<T> feCol(Stream<T> stream) {
        return stream.collect(Collectors.toSet());
    }

    public static <T> void primitiveFE(T[] array, Consumer<? super T> action) {
        feCol(feStr(array)).forEach(action);
    }

    public static <T> void smartFE(Collection<T> collection, Consumer<? super T> action) {
        collection.forEach(action);
    }

    public static <K, V> void mapFE(Map<K, V> map, BiConsumer<K, V> actions) {
        map.forEach(actions);
    }

    public static boolean isValid(Validate validate) {
        return validate.isValid();
    }
}
