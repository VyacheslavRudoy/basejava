package ru.javawebinar.basejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        Integer[] array = new Integer[]{1, 2, 1, 3, 6, 6, 7, 5};
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(8);
        list.add(7);
        System.out.println(minValue(array));
        System.out.println(oddOrEven(list));
    }

    private static int minValue(Integer[] values) {
        Arrays.sort(values);
        int result = Stream.of(values)
                .distinct()
                .reduce(0, (a, b) -> 10 * a + b);
        return result;
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {
        int number = integers.stream().mapToInt(Integer::intValue)
                .sum() % 2;

        return integers
                .stream()
                .filter(n -> n % 2 != number)
                .collect(Collectors.toList());
    }
}
