package org.example.adventofcode;

import org.example.puzzledays.*;
import org.example.utils.FileScanner;
import org.example.utils.PuzzleDay;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        boolean runAll = true;
        int workingOnDay = 1;

        ArrayList<PuzzleDay> puzzleDays = new ArrayList<>();
        puzzleDays.add(new Day01());
        puzzleDays.add(new Day02());

        if (runAll) {
            printAllResults(puzzleDays);
        } else {
            printResultsOfDay(puzzleDays, workingOnDay);
        }
    }

    private static void printAllResults(ArrayList<PuzzleDay> days) throws FileNotFoundException {
        for (int i = 0; i < days.size(); i++) {
            int currentDay = i + 1;
            printResultsOfDay(days, currentDay);
        }
    }

    private static void printResultsOfDay(ArrayList<PuzzleDay> days, int workingDay) throws FileNotFoundException {
        PuzzleDay currentDay = days.get(workingDay - 1);

        if (currentDay.isPartOneSolved() || currentDay.isPartTwoSolved()) {
            System.out.println("\u001B[1mResult for Day " + workingDay + "\u001B[0m");

            ArrayList<String> puzzleInput = FileScanner.getPuzzleInput(workingDay, currentDay.getUseTestInput());
            if (currentDay.isPartOneSolved()) {
                System.out.printf("Part one: " +
                        "the solution is %d%n", currentDay.getSolutionPartOne(puzzleInput));
            }

            if (currentDay.isPartTwoSolved()) {
                System.out.printf("Part two: " +
                        "the solution is %d%n", currentDay.getSolutionPartTwo(puzzleInput));
            }
            System.out.println("------");
        }
    }
}