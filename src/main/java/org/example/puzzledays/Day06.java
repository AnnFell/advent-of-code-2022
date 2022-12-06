package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.HashSet;

public class Day06 extends PuzzleDay {
    public Day06() {
        super(true, true, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        System.out.println(" in day 6");
        int indexOfFirstPacketStart = -1;

        String[] bufferArray = input.get(0).split("");
        for (int i = 3; i < bufferArray.length; i++) {
            HashSet<String> uniqueCharsFound = new HashSet<>(4);
            for (int j = 0; j < 4; j++) {
                uniqueCharsFound.add(bufferArray[i-j]);
            }
            if (uniqueCharsFound.size() == 4) {
                indexOfFirstPacketStart = i + 1;
                break;
            }
        }
        return indexOfFirstPacketStart;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        int indexOfFirstPacketStart = -1;

        String[] bufferArray = input.get(0).split("");
        for (int i = 13; i < bufferArray.length; i++) {
            HashSet<String> uniqueCharsFound = new HashSet<>(4);
            for (int j = 0; j < 14; j++) {
                uniqueCharsFound.add(bufferArray[i-j]);
            }
            if (uniqueCharsFound.size() == 14) {
                indexOfFirstPacketStart = i + 1;
                break;
            }
        }
        return indexOfFirstPacketStart;
    }
}
