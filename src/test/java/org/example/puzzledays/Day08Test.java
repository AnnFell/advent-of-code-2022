package org.example.puzzledays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {
    private Day08 day;

    @BeforeEach
    void init() {
        day = new Day08();
    }

    @Test
    void checkTreeline() {
        List<Integer> values = new ArrayList<>(Arrays.asList(7, 6, 4, 4, 3));
        List<Integer> values2 = new ArrayList<>(Arrays.asList(7, 6,8, 4, 4, 3, 2));


        List<Integer> result = day.checkTreeline(values);
        List<Integer> result2 = day.checkTreeline(values2);

        List<Integer> expected = new ArrayList<>(Arrays.asList(0, 4, 3, 1));
        List<Integer> expected2 = new ArrayList<>(Arrays.asList(0, 2, 6, 5, 3));

        System.out.println("Result: " + result);
        assertEquals(result.size(), expected.size(), "Arrays should be same size");
        assertEquals(result2.size(), expected2.size(), "Arrays should be same size");
        assertEquals(result.get(0), expected.get(0), "Should be equal to " + expected.get(0));
        assertEquals(result.get(1), expected.get(1), "Should be equal to "+ expected.get(1));
    }
}