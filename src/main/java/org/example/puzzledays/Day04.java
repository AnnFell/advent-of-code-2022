package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.List;

public class Day04 extends PuzzleDay {
    public Day04() {
        super(true, true, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        long check = 0;

        for (String line : input) {
            String[] elves = line.split(",");
            int[] elf1 = getElfStartAndEndChore(elves[0]);
            int[] elf2 = getElfStartAndEndChore(elves[1]);

            if (elf1[0] >= elf2[0] && elf1[1] <= elf2[1] || elf2[0] >= elf1[0] && elf2[1] <= elf1[1]) {
                check++;
            }
        }

        return check;
    }

    private int[] getElfStartAndEndChore(String notation) {
        String[] choresString = notation.split("-");
        int[] result = new int[2];
        result[0] = Integer.parseInt(choresString[0]);
        result[1] = Integer.parseInt(choresString[1]);
        return result;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        long check = 0;

        for (String line : input) {

            String[] elves = line.split(",");
            List<Integer> elf1 = getElfChoreArray(elves[0]);
            List<Integer> elf2 = getElfChoreArray(elves[1]);

            List<Integer> result = elf1.stream().filter(elf2::contains).toList();

            if (result.size() > 0) {
                check++;
            }
        }
        return check;
    }

    private List<Integer> getElfChoreArray(String notation) {
        List<Integer> result = new ArrayList<>();
        String[] choresString = notation.split("-");
        int start = Integer.parseInt(choresString[0]);
        int end = Integer.parseInt(choresString[1]);
        if (start == end) {
            result.add(start);
        } else {
            for (int i = start; i <= end; i++) {
                result.add(i);
            }
        }
        return result;
    }
}
