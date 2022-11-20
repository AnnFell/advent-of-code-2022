package org.example.utils;

import org.example.utils.shared.TargetType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class InputConverterTest {

    @Test
    void Test_inputToIntArray_CaseInteger() {
        ArrayList<String> input = new ArrayList<>();
        input.add("123");
        input.add("456");
        input.add("789");
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(123);
        expected.add(456);
        expected.add(789);

        List<Integer> result = InputConverter.convertToListOfIntegers(input);

        assert(result.size() == 3);
        assert(expected.get(0).equals(result.get(0)));
        assert(expected.get(1).equals(result.get(1)));
        assert(expected.get(2).equals(result.get(2)));
    }

    @Test
    void Test_inputToListOfIntArrays_CaseInteger() {
        ArrayList<String> input = new ArrayList<>();
        input.add("123");
        input.add("456");
        input.add("789");
        List<Integer> expected1 = Arrays.asList(1,2,3);
        List<Integer> expected2 = Arrays.asList(4,5,6);
        List<Integer> expected3 = Arrays.asList(7,8,9);


        List<List<Integer>> result = InputConverter.convertToListOfListsWithType(input, TargetType.INTEGER);

        assert (result.size() == 3);
        assert(expected1.equals(result.get(0)));
        assert(expected2.equals(result.get(1)));
        assert(expected3.equals(result.get(2)));
    }

    @Test
    void Test_inputToListOfIntArrays_CaseCharacter() {
        ArrayList<String> input = new ArrayList<>();
        input.add("abc");
        input.add("DEF");
        input.add("123");
        List<Character> expected1 = Arrays.asList('a','b','c');
        List<Character> expected2 = Arrays.asList('D','E','F');
        List<Character> expected3 = Arrays.asList('1','2','3');

        List<List<Character>> result = InputConverter.convertToListOfListsWithType(input, TargetType.CHARACTER);

        assert (result.size() == 3);
        assert(expected1.equals(result.get(0)));
        assert(expected2.equals(result.get(1)));
        assert(expected3.equals(result.get(2)));
    }
}