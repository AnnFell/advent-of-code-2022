package org.example.adventofcode;

import org.example.utils.FileScanner;
import org.example.utils.PuzzleDay;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String BLACK_BRIGHT = "\033[0;90m";
    private static final String BOLD = "\u001B[1m";
    private static final String RESET = "\033[0m";

    private static List<Class<? extends PuzzleDay>> puzzleDays;

    public static void main(String[] args) throws FileNotFoundException {
        boolean runAll = true;
        int workingOnDay = 2;

        puzzleDays = findAllClassesUsingReflectionsLibrary();

        if (runAll) {
            printAllResults();
        } else {
            runSolutionsOfDay(workingOnDay);
        }
    }

    private static void printAllResults() throws FileNotFoundException {
        for (int i = 0; i < puzzleDays.size(); i++) {
            int currentDay = i + 1;
            runSolutionsOfDay(currentDay);
        }
    }

    private static void runSolutionsOfDay(int workingDay) throws FileNotFoundException {
        PuzzleDay currentDay = getPuzzleDay(workingDay);
        if (currentDay == null) {
            return;
        }

        if (currentDay.isPartOneSolved() || currentDay.isPartTwoSolved()) {
            System.out.println(BOLD + "Result for Day " + workingDay + RESET);

            ArrayList<String> puzzleInput = FileScanner.getPuzzleInput(workingDay, currentDay.getUseTestInput());

            if (currentDay.isPartOneSolved()) {
                printResult("one", currentDay.getSolutionPartOne(puzzleInput));
            }

            if (currentDay.isPartTwoSolved()) {
                printResult("two", currentDay.getSolutionPartTwo(puzzleInput));
            }
            System.out.println("------");
        }
    }

    private static void printResult(String part, Long solution) {
        long startTime = System.nanoTime();
        System.out.printf("Part %s: " +
                "the solution is %d%n", part, solution);
        long elapsedTime = System.nanoTime() - startTime;
        double milliseconds = ((double) elapsedTime) / 1000000;
        System.out.println(BLACK_BRIGHT + milliseconds + "ms" + RESET);
    }

    private static PuzzleDay getPuzzleDay(int workingDay) {
        int getDay = workingDay - 1;
        PuzzleDay day = null;
        try {
            day = puzzleDays.get(getDay).getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InvocationTargetException |
                 InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return day;
    }

    private static List<Class<? extends PuzzleDay>> findAllClassesUsingReflectionsLibrary() {
        Reflections reflections = new Reflections("org.example.puzzledays", new SubTypesScanner(false));
        return new ArrayList<>(reflections.getSubTypesOf(PuzzleDay.class));
    }
}