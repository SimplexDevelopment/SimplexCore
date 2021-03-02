package io.github.simplexdev.simplexcore.utils;

import io.github.simplexdev.api.func.Path;
import io.github.simplexdev.simplexcore.ban.BanType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SplittableRandom;
import java.util.function.Consumer;
import java.util.stream.Stream;

public final class Utilities {
    private static final SplittableRandom random = new SplittableRandom();
    private static final SplittableRandom numbers = new SplittableRandom();
    private static final List<Character> list = new ArrayList<>() {{
        add('0');
        add('1');
        add('2');
        add('3');
        add('4');
        add('5');
        add('6');
        add('7');
        add('8');
        add('9');
        add('-');
    }};

    //Utility class should not be instantiated.
    private Utilities() {
        throw new AssertionError();
    }

    public static <T> void forEach(T[] array, Consumer<? super T> action) {
        for (T obj : array) {
            action.accept(obj);
        }
    }

    public static <T> Stream<T> stream(T[] array) {
        return Arrays.stream(array);
    }

    public static String generateBanId(BanType type) {
        final String charList = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz";
        final String numList = "0123456789";
        final int length = charList.length();
        final int lng = numList.length();

        final StringBuilder sb = new StringBuilder();

        sb.append(type.getPrefix());

        for (int x = 0; x < 4; x++) {
            sb.append(charList.charAt(random.nextInt(length - 1)));
            sb.append(numList.charAt(numbers.nextInt(lng - 1)));
        }

        sb.setCharAt(2, capitalize(sb.charAt(2)));
        return sb.toString();
    }

    private static char capitalize(char character) {
        if (list.contains(character)) {
            return character;
        }

        String temp = String.valueOf(character).toUpperCase();
        return temp.charAt(0);
    }

    public static Path pathway(String pathway) {
        return () -> pathway;
    }
}