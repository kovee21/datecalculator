package com.example.demo.utils;

import java.util.List;
import java.util.stream.Collectors;

public class DurationUtil {

    public static List<String> removeFromListWhenZero(List<String> words) {
        return words.stream()
                .filter(word -> !word.startsWith("0"))
                .collect(Collectors.toList());
    }

    public static String joinerWithAnd(List<String> words) {
        if (words.size() == 1) {
            return words.get(0);
        }
        return String.join(", ", words.subList(0, words.size() - 1))
                + " and " + words.get(words.size() - 1);
    }

    public static String pluralize(long number, String word) {
        return number > 1 ? number + " " + word + "s" : number + " " + word;
    }
}

