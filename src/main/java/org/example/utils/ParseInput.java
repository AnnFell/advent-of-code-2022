package org.example.utils;

import java.util.ArrayList;

public class ParseInput {
    public static int[] inputToIntArray(ArrayList<String> input) {
        int[] intArray = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            intArray[i] = Integer.parseInt(input.get(i));
        }
        return intArray;
    }

    public static ArrayList<int[]> inputToListOfIntArrays(ArrayList<String> input) {
        ArrayList<int[]> list = new ArrayList<>();
        for (String line : input) {
            int[] dataLine = new int[line.length()];
            for (int i = 0; i < line.length(); i++) {
                dataLine[i] = Character.getNumericValue(line.charAt(i));
            }
            list.add(dataLine);
        }
        return list;
    }

}
