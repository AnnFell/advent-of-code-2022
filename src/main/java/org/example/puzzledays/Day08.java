package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.*;
import java.util.stream.Collectors;

public class Day08 extends PuzzleDay {
    final List<Integer> map = new ArrayList<>();
    int mapSizeX;
    int mapSizeY;

    public Day08() {
        super(true, true, true);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        parseInput(input);

        mapSizeX = input.get(0).length();
        mapSizeY = input.size();

        printMap(map, mapSizeX, mapSizeY);

        // check rows
        for (int y = 0; y < mapSizeY; y++) {

            @SuppressWarnings("DuplicatedCode") List<Integer> row = new ArrayList<>();
            for (int x = 0; x < mapSizeX; x++) {
                int currentPos = (y * mapSizeX) + x;
                row.add(map.get(currentPos));
            }

            List<Integer> foundTrees = checkTreeline(row);
            markTreesInRow(foundTrees, y);
        }

        // check columns
        for (int x = 0; x < mapSizeX; x++) {

            @SuppressWarnings("DuplicatedCode") List<Integer> column = new ArrayList<>();
            for (int y = 0; y < mapSizeY; y++) {
                int currentPos = (y * mapSizeX) + x;
                column.add(map.get(currentPos));
            }

            List<Integer> foundTrees = checkTreeline(column);
            markColumnsInRow(foundTrees, x);
        }

        // count al negative numbers in map
        return map.stream().filter(x -> x<0).count();
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        return 0;
    }

    private void markTreesInRow(List<Integer> xIndexes, int yIndex) {
        for (Integer x : xIndexes) {
            int mapIndex = (yIndex * mapSizeX) + x;
            if (map.get(mapIndex) > 0) {
                // mark only if not already marked
                map.set(mapIndex, map.get(mapIndex) * -1);
            }
        }
    }

    private void markColumnsInRow(List<Integer> yIndexes, int xIndex) {
        for (Integer y : yIndexes) {
            int mapIndex = (y * mapSizeX) + xIndex;
            if (map.get(mapIndex) > 0) {
                // mark only if not already marked
                map.set(mapIndex, map.get(mapIndex) * -1);
            }
        }
    }

    public List<Integer> checkTreeline(List<Integer> line) {
        line = line.stream().map(x -> x < 0 ? x * -1 : x).collect(Collectors.toList());
        List<Integer> foundTrees = new ArrayList<>();

        // find visible trees from left side
        int valueLeftHighest = -1;
        int indexLeftHighest = -1;
        for (int i = 0; i < line.size(); i++) {
            if (line.get(i) > valueLeftHighest) {
                valueLeftHighest = line.get(i);
                indexLeftHighest = i;
                foundTrees.add(indexLeftHighest);
            }
        }

        // find visible trees from the right side
        int valueRightHighest = -1;
        int indexRightHighest = -1;
        for (int i = line.size() -1; i > indexLeftHighest; i--) {
            if (line.get(i) > valueRightHighest) {
                valueRightHighest = line.get(i);
                indexRightHighest = i;
                foundTrees.add(indexRightHighest);
            }
        }
        return foundTrees;
    }

    private void parseInput(List<String> input) {
        for (String line : input) {
            // map to one higher because negative flag does not work for 0 values
            List<Integer> splitLine = Arrays.stream(line.split("")).mapToInt(Integer::parseInt).map(x -> x + 1).boxed().toList();
            map.addAll(splitLine);
        }
    }

    private void printMap(List<Integer> map, int mapSizeX, int mapSizeY) {
        System.out.println();
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                System.out.format("|%2d ", (map.get((y * mapSizeX) + x)-1));
            }
            System.out.println();
        }
        System.out.println();
    }
}
