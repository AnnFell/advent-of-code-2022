package org.example.puzzledays;

import org.example.puzzledays.day11.Monkey;
import org.example.utils.PuzzleDay;

import java.util.ArrayList;

public class Day11 extends PuzzleDay {
    public Day11() {
        super(true, false, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        Monkey[] monkeys = parseInput(input);

        for (int i = 1; i <= 20; i++) {
            for (Monkey monkey : monkeys) {
                for (int itemIndex = 0; itemIndex < monkey.getItems().size(); itemIndex++) {
                    int[] toThrow = monkey.inspection(itemIndex);
                    monkeys[toThrow[0]].catchItem(toThrow[1]);
                }
                monkey.clearItemsAfterRound();
            }
            System.out.println("After round " + i);
            for (int x = 0; x < monkeys.length; x++) {
                System.out.println("Monkey " + x + ": " + monkeys[x].getInspections());
            }
        }

        int highest = 0;
        int secondHighest = 0;
        for (Monkey monkey : monkeys) {
            int inspectionAmount = monkey.getInspections();
            if (highest < inspectionAmount) {
                secondHighest = highest;
                highest = inspectionAmount;
            }
        }

        return (long) highest * secondHighest;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        return 0;
    }

    private Monkey[] parseInput(ArrayList<String> input) {
        int numberOfMonkeys = (input.size() + 1) / 7;
        Monkey[] monkeys = new Monkey[numberOfMonkeys];

        int currentMonkey = 0;
        String numberCheck = "[^0-9]";
        for (int i = 0; i < input.size(); i++) {
            String[] items = input.get(i + 1).split(": ")[1].split(", ");
            int testValue = Integer.parseInt(input.get(i + 3).replaceAll(numberCheck, ""));
            int trueThrow = Integer.parseInt(input.get(i + 4).replaceAll(numberCheck, ""));
            int falseThrow = Integer.parseInt(input.get(i + 5).replaceAll(numberCheck, ""));

            String operationType = input.get(i + 2).substring(23, 24);
            String checkOperationValue = input.get(i + 2).replaceAll(numberCheck, "");
            // if operationvalue is "old", use -1:
            int operationValue = checkOperationValue.length() < 1 ? -1 : Integer.parseInt(checkOperationValue);

            monkeys[currentMonkey] = new Monkey(items, operationType, operationValue, testValue, trueThrow, falseThrow);
            currentMonkey++;
            i += 6;
        }
        return monkeys;
    }
}
