package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.List;

public class Day13 extends PuzzleDay {
    List<String[][]> pairs = new ArrayList<>();

    public Day13(){
        super(true, false, true);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        parseInput(input);

        return 0;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        return 0;
    }

    private void parseInput(ArrayList<String> input){
        for (int i = 0; i < input.size();i+=3) {
               String[][] pair = new String[2][];
               pair[0] = input.get(i).split(",");
               pair[1] = input.get(i+1).split(",");
               pairs.add(pair);
        }
    }
}
