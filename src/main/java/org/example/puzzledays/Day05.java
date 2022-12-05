package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day05 extends PuzzleDay {
    List<List<String>> stacks = new ArrayList<>();

    public Day05() {
        super(true, true, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        this.setStacks();
        boolean isPastStackData = false;

        for (String line : input) {

            if (isPastStackData) {
                // read in move, amount and destination
                int[] instruction = getInstruction(line);
                // do it
                while (instruction[0] > 0) {
                    moveTopCrate(instruction[1], instruction[2]);
                    instruction[0]--;
                }
            }

            if (line.length() == 0) {
                isPastStackData = true;
            }
        }

        // print tops of stack
        System.out.println("String solution part one: ");
        for (List<String> stack : stacks) {
            System.out.print(stack.get(stack.size() - 1));
        }
        System.out.println();
        return 0;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        this.setStacks();
        boolean isPastStackData = false;

        for (String line : input) {

            if (isPastStackData) {
                // read in move, amount and destination
                int[] instruction = getInstruction(line);
                // do it
                moveCrates(instruction[0], instruction[1], instruction[2]);
            }

            if (line.length() == 0) {
                isPastStackData = true;
            }
        }

        // print tops of stack
        System.out.print("String solution part two: ");
        for (List<String> stack : stacks) {
            System.out.print(stack.get(stack.size() - 1));
        }
        System.out.println();
        return 0;
    }

    private void moveTopCrate(int stackSource, int stackTarget) {
        // get crate
        List<String> stackFrom = stacks.get(stackSource - 1);
        String crate = stackFrom.get(stackFrom.size() - 1);
        // move crate
        stacks.get(stackTarget - 1).add(crate);
        // remove crate
        stackFrom.remove(stackFrom.size() - 1);
    }

    private void moveCrates(int amount, int stackSource, int stackTarget) {
        // get crates
        List<String> stackFrom = stacks.get(stackSource - 1);
        List<String> cratesToMove = stackFrom.subList(stackFrom.size() - (amount), stackFrom.size());
        // move crate
        stacks.get(stackTarget - 1).addAll(cratesToMove);
        // remove crates
        while (amount > 0) {
            stackFrom.remove(stackFrom.size() - 1);
            amount--;
        }
    }

    private int[] getInstruction(String line) {
        int fromIndex = line.indexOf(" from ");
        int toIndex = line.indexOf(" to ");
        int[] instruction = new int[3];
        instruction[0] = Integer.parseInt(line.substring(5, fromIndex));
        instruction[1] = Integer.parseInt(line.substring(fromIndex + 6, toIndex));
        instruction[2] = Integer.parseInt(line.substring(toIndex + 4));
        return instruction;
    }

    private void setStacks() {
        this.stacks.clear();
        if (getUseTestInput()) {
            List<String> stack1 = new ArrayList<>(Arrays.asList("Z", "N"));
            List<String> stack2 = new ArrayList<>(Arrays.asList("M", "C", "D"));
            List<String> stack3 = new ArrayList<>(List.of("P"));
            this.stacks.add(stack1);
            this.stacks.add(stack2);
            this.stacks.add(stack3);
        } else {
            List<String> stack1 = new ArrayList<>(Arrays.asList("R", "G", "H", "Q", "S", "B", "T", "N"));
            List<String> stack2 = new ArrayList<>(Arrays.asList("H", "S", "F", "D", "P", "Z", "J"));
            List<String> stack3 = new ArrayList<>(Arrays.asList("Z", "H", "V"));
            List<String> stack4 = new ArrayList<>(Arrays.asList("M", "Z", "J", "F", "G", "H"));
            List<String> stack5 = new ArrayList<>(Arrays.asList("T", "Z", "C", "D", "L", "M", "S", "R"));
            List<String> stack6 = new ArrayList<>(Arrays.asList("M", "T", "W", "V", "H", "Z", "J"));
            List<String> stack7 = new ArrayList<>(Arrays.asList("T", "F", "P", "L", "Z"));
            List<String> stack8 = new ArrayList<>(Arrays.asList("Q", "V", "W", "S"));
            List<String> stack9 = new ArrayList<>(Arrays.asList("W", "H", "L", "M", "T", "D", "N", "C"));
            this.stacks.add(stack1);
            this.stacks.add(stack2);
            this.stacks.add(stack3);
            this.stacks.add(stack4);
            this.stacks.add(stack5);
            this.stacks.add(stack6);
            this.stacks.add(stack7);
            this.stacks.add(stack8);
            this.stacks.add(stack9);
        }
    }
}
