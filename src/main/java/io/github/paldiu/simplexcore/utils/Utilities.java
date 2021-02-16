package io.github.paldiu.simplexcore.utils;

import io.github.paldiu.simplexcore.banning.BanType;
import io.github.paldiu.simplexcore.functional.Guard;
import io.github.paldiu.simplexcore.functional.Validate;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Utilities {
    //Utility class should not be instantiated.
    private Utilities() {
        throw new AssertionError();
    }

    public static <T> void forEach(T[] array, Consumer<? super T> action) {
        for (T obj : array) {
            action.accept(obj);
        }
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

    private static final SplittableRandom random = new SplittableRandom();
    private static final SplittableRandom numbers = new SplittableRandom();
    private static final List<Character> list = new ArrayList<>(){{
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

    private static char capitalize(char character) {
        if (list.contains(character)) {
            return character;
        }

        String temp = String.valueOf(character).toUpperCase();
        return temp.charAt(0);
    }
}
