package org.example.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileScanner {
    public static ArrayList<String> getPuzzleInput(int dayNumber, boolean useTestInput) throws FileNotFoundException {
        // create path to input of correct day and type
        String path = String.format("%s/day%02d.txt", useTestInput ? "_test-input" : "_puzzle-input", dayNumber);

        // collect every line of puzzle input
        Scanner puzzleScanner = new Scanner(new File(path));
        ArrayList<String> allInputLines = new ArrayList<>();
        while (puzzleScanner.hasNextLine()) {
            allInputLines.add(puzzleScanner.nextLine());
        }
        puzzleScanner.close();

        return allInputLines;
    }
}