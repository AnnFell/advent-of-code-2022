package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Day03 extends PuzzleDay {
    public Day03() {
        super(true, true, false);
    }

    private static final ArrayList<Character> LETTER_VALUES = new ArrayList<>(Arrays.asList(' ', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        long score = 0;

        for (String line : input) {
            char[] firstHalf = line.substring(0, (line.length() / 2)).toCharArray();
            String secondHalf = line.substring(line.length() / 2);

            char charFound = ' ';
            for (char x : firstHalf) {
                if (secondHalf.indexOf(x) != -1) {
                    charFound = x;
                }
            }

            score += LETTER_VALUES.indexOf(charFound);
        }
        return score;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        long score = 0;

        for (int i = 0; i < input.size(); i += 3) {
            char[] backpack1 = input.get(i).toCharArray();
            String backpack2 = input.get(i+1);
            String backpack3 = input.get(i+2);

            HashSet<Character> charsFound = new HashSet<>();
            for (char x : backpack1) {
                if (backpack2.indexOf(x) != -1) {
                    charsFound.add(x);
                }
            }
            char charFound = ' ';
            for (char letter : charsFound) {
                if (backpack3.indexOf(letter) != -1) {
                    charFound = letter;
                }
            }

            score += LETTER_VALUES.indexOf(charFound);
        }
        return score;
    }
}
