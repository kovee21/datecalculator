package com.example.demo.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DurationUtilTest {

    @Test
    void testRemoveFromListWhenZero() {
        var input = Arrays.asList("0", "1", "2", "0", "3");
        var expected = Arrays.asList("1", "2", "3");
        var result = DurationUtil.removeFromListWhenZero(input);
        assertEquals(expected, result);
    }

    @Test
    void testRemoveFromListWhenAllZeros() {
        var input = Arrays.asList("0", "0", "0");
        var expected = Arrays.asList();
        var result = DurationUtil.removeFromListWhenZero(input);
        assertEquals(expected, result);
    }

    @Test
    void testJoinerWithAnd() {
        var input = Arrays.asList("something", "something else", "something more");
        var expected = "something, something else and something more";
        var result = DurationUtil.joinerWithAnd(input);
        assertEquals(expected, result);
    }

    @Test
    void testJoinerWithAndSingleElement() {
        var input = Arrays.asList("something");
        var expected = "something";
        var result = DurationUtil.joinerWithAnd(input);
        assertEquals(expected, result);
    }

    @Test
    void testJoinerWithAndTwoElements() {
        var input = Arrays.asList("something", "something else");
        var expected = "something and something else";
        var result = DurationUtil.joinerWithAnd(input);
        assertEquals(expected, result);
    }

    @Test
    void testPluralizeSingle() {
        var expected = "1 something";
        var result = DurationUtil.pluralize(1, "something");
        assertEquals(expected, result);
    }

    @Test
    void testPluralizeMultiple() {
        var expected = "2 somethings";
        var result = DurationUtil.pluralize(2, "something");
        assertEquals(expected, result);
    }

    @Test
    void testPluralizeZero() {
        var expected = "0 something";
        var result = DurationUtil.pluralize(0, "something");
        assertEquals(expected, result);
    }
} 