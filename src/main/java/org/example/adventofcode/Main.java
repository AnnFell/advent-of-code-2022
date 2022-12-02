package org.example.adventofcode;

import org.example.puzzledays.*;
import org.example.utils.FileScanner;
import org.example.utils.PuzzleDay;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {
    public static final String BLACK_BRIGHT = "\033[0;90m";
    private static final String BOLD = "\u001B[1m";
    private static final String RESET = "\033[0m";

    public static void main(String[] args) throws FileNotFoundException {
        boolean runAll = false;
        int workingOnDay = 2;

        ArrayList<PuzzleDay> puzzleDays = new ArrayList<>();
        puzzleDays.add(new Day01());
        puzzleDays.add(new Day02());
        puzzleDays.add(new Day03());

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
            System.out.println(BOLD + "Result for Day " + workingDay + RESET);

            ArrayList<String> puzzleInput = FileScanner.getPuzzleInput(workingDay, currentDay.getUseTestInput());

            if (currentDay.isPartOneSolved()) {
                long startTime = System.nanoTime();
                System.out.printf("Part one: " +
                        "the solution is %d%n", currentDay.getSolutionPartOne(puzzleInput));
                long elapsedTime = System.nanoTime()-startTime;
                printElapsedTime(elapsedTime);
            }

            if (currentDay.isPartTwoSolved()) {
                long startTime = System.nanoTime();
                System.out.printf("Part two: " +
                        "the solution is %d%n", currentDay.getSolutionPartTwo(puzzleInput));
                long elapsedTime = System.nanoTime()-startTime;
                printElapsedTime(elapsedTime);
            }
            System.out.println("------");
        }
    }

    private static void printElapsedTime(long elapsedNanoTime){
        double milliseconds = ((double)elapsedNanoTime)/1000000;
        System.out.println(BLACK_BRIGHT + milliseconds + "ms" + RESET);
    }
}