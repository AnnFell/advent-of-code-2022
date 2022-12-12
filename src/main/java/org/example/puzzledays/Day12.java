package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;

/*
 * Sabqponm
 * abcryxxl
 * accszExk
 * acctuvwj
 * abdefghi
 */

record Coordinate(int x, int y){}

public class Day12 extends PuzzleDay {
    char[][] map;
    Coordinate start;
    Coordinate end;

    public Day12() {
        super(true, false, true);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        parseInput(input);
        printMap();
        return 0;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        return 0;
    }

    private void printMap() {
        System.out.println("========");
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[0].length; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
        System.out.println("========");
    }

    private void parseInput(ArrayList<String> input) {
        int mapX = input.get(0).length();
        int mapY = input.size();

        map = new char[mapY][];
        for (int y = 0; y < mapY; y++) {

            char[] row = input.get(y).toCharArray();
            map[y] = row;

            for (int x = 0; x < mapX; x++) {
                if (map[y][x] == 'S') {
                    start = new Coordinate(x, y);
                }
                if (map[y][x] == 'E') {
                    end = new Coordinate(x, y);
                }
            }

        }
    }
}
