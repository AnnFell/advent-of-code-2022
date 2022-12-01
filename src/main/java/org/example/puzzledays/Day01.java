package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 extends PuzzleDay {
    public Day01() {
        super(true, true, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        //Sort inventory per elf
        List<List<Long>> inventory = new ArrayList<>();
        List<Long> currentElfInventory = new ArrayList<>();

        for (String line : input) {
            if (line.isBlank()) {
                inventory.add(new ArrayList<>(currentElfInventory));
                currentElfInventory.clear();
            } else {
                currentElfInventory.add(Long.parseLong(line));
            }
        }
        inventory.add(currentElfInventory);

        //Find elf with most calories
        long caloriesHighest = 0;

        for (List<Long> elf : inventory) {
            long elfCalories = elf.stream().mapToLong(Long::intValue).sum();
            if (elfCalories > caloriesHighest) {
                caloriesHighest = elfCalories;
            }
        }

        return caloriesHighest;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        //Calories per elf
        List<Long> inventory = new ArrayList<>();
        List<Long> currentElfInventory = new ArrayList<>();

        for (String line : input) {
            if (line.isBlank()) {
                inventory.add(currentElfInventory.stream().mapToLong(Long::intValue).sum());
                currentElfInventory.clear();
            } else {
                currentElfInventory.add(Long.parseLong(line));
            }
        }
        inventory.add(currentElfInventory.stream().mapToLong(Long::intValue).sum());

        //Get 3 highest values
        Collections.sort(inventory);
        Collections.reverse(inventory);
        return inventory.get(0)+inventory.get(1)+inventory.get(2);
    }
}
