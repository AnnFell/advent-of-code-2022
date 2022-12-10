package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;

public class Day10 extends PuzzleDay {
    public Day10() {
        super(true, true, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        long x = 1;
        int cycle = 0;

        long interestingCycleStrength = 0;

        for (String line : input) {
            cycle++;
            interestingCycleStrength += cycleStrengthCheck(x, cycle);

            if (!line.equals("noop")) {
                cycle++;
                interestingCycleStrength += cycleStrengthCheck(x, cycle);
                int amount = Integer.parseInt(line.split(" ")[1]);
                x += amount;
            }
        }
        return interestingCycleStrength;
    }

    private long cycleStrengthCheck(long x, int cycle) {
        if (cycle == 20 || cycle == 60 || cycle == 100
                || cycle == 140 || cycle == 180 || cycle == 220) {
            return (x * cycle);
        }
        return 0;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        long x = 1;
        int cycle = 0;

        for (String line : input) {
            cycle++;
            printCycle(x, cycle);

            if (!line.equals("noop")) {
                cycle++;
                printCycle(x, cycle);
                int amount = Integer.parseInt(line.split(" ")[1]);
                x += amount;
            }
        }
        // FCJAPJRE in console
        return 0;
    }

    private void printCycle(long x, int cycle) {
        String pixel = ".";
        if ((cycle % 40) >= x && (cycle % 40) <= x + 2) {
            pixel = "#";
        }
        System.out.print(pixel);
        if (cycle % 40 == 0) {
            System.out.print("\n");
        }
    }
}
