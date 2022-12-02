package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.List;

public class Day02 extends PuzzleDay {
    public Day02() {
        super(true, true, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        List<String> matches = new ArrayList<>();
        for (String line : input) {
            String newline;
            newline = line.replace("X", "A");
            newline = newline.replace("Y", "B");
            newline = newline.replace("Z", "C");
            matches.add(newline);
        }

        long score = 0;

        for (String match : matches) {
            String you = match.substring(match.length() - 1);
            String elf = match.substring(0, 1);

            score += getChoiceValue(you);
            score += getMatchResultValue(elf, you);
        }

        return score;
    }

    private int getMatchResultValue(String elf, String you) {
        if (elf.equals(you)) {
            return 3;
        } else if (you.equals("A") && elf.equals("C") ||
                you.equals("B") && elf.equals("A") ||
                you.equals("C") && elf.equals("B")) {
            return 6;
        } else {
            return 0;
        }
    }

    private int getChoiceValue(String you){
        return switch (you) {
            case "A" -> 1;
            case "B" -> 2;
            case "C" -> 3;
            default -> 0;
        };
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        long score = 0;

        for (String line : input) {
            String elf = line.substring(0, 1);

            String instruction = line.substring(line.length() - 1);
            String you = switch (instruction) {
                case "X" -> getLose(elf);
                case "Y" -> elf;
                case "Z" -> getWin(elf);
                default -> "";
            };

            score += getChoiceValue(you);
            score += getMatchResultValue(elf, you);
        }

        return score;
    }

    private String getWin(String opponent) {
        return switch (opponent) {
            case "A" -> "B";
            case "B" -> "C";
            case "C" -> "A";
            default -> "";
        };
    }

    private String getLose(String opponent) {
        return switch (opponent) {
            case "A" -> "C";
            case "B" -> "A";
            case "C" -> "B";
            default -> "";
        };
    }
}
