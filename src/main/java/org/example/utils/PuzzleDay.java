package org.example.utils;

import java.util.ArrayList;

public abstract class PuzzleDay {
    private boolean partOneSolved;
    private boolean partTwoSolved;
    private boolean useTestInput;

    protected PuzzleDay(boolean isPartOneSolved, boolean isPartTwoSolved, boolean useTestInput){
        this.partOneSolved = isPartOneSolved;
        this.partTwoSolved = isPartTwoSolved;
        this.useTestInput  = useTestInput;
    }

    public boolean isPartOneSolved() {
        return partOneSolved;
    }

    public boolean isPartTwoSolved() {
        return partTwoSolved;
    }

    public boolean getUseTestInput() {
        return useTestInput;
    }

    public abstract long getSolutionPartOne(ArrayList<String> input);

    public abstract long getSolutionPartTwo(ArrayList<String> input);
}
