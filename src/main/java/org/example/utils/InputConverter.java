package org.example.utils;

import org.example.utils.shared.TargetType;

import java.util.ArrayList;
import java.util.List;

public class InputConverter {

    public static List<Integer> convertToListOfIntegers(ArrayList<String> source) {
        List<Integer> result = new ArrayList<>();
        for (String line : source) {
            result.add(Integer.valueOf(line));
        }
        return result;
    }

    public static <T> List<List<T>> convertToListOfListsWithType(ArrayList<String> source, TargetType targetType) {
        List<List<T>> list = new ArrayList<>();

        for (String line : source) {
            List<T> dataLine = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                char dataSource = line.charAt(i);
                T dataTarget = convertCharTo(dataSource, targetType);
                dataLine.add(dataTarget);
            }
            list.add(dataLine);
        }
        return list;
    }

    private static <T> T convertCharTo(char source, TargetType targetType) {
        switch (targetType) {
            case INTEGER:
                Integer intValue = Character.getNumericValue(source);
                return (T) intValue;
            case CHARACTER:
                Character charValue = source;
                return (T) charValue;
            default:
                return null;
        }
    }

}
