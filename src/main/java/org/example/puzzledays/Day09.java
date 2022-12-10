package org.example.puzzledays;

import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.HashSet;

public class Day09 extends PuzzleDay {
    String[][][] map;
    int mapSizeX;
    int mapSizeY;
    int startX;
    int startY;

    public Day09() {
        super(true, false, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        // ------------ build the map ------------
        // find out map size and start position
        int xMax = 0;
        int xMin = 0;
        int yMax = 0;
        int yMin = 0;

        int currX = 0;
        int currY = 0;

        int layers = 1;

        for (String line : input) {
            String[] instruction = line.split(" ");
            int amount = Integer.parseInt(instruction[1]);
            switch (instruction[0]) {
                case "R" -> currX += amount;
                case "L" -> currX -= amount;
                case "U" -> currY += amount;
                case "D" -> currY -= amount;
            }
            xMax = Math.max(currX, xMax);
            xMin = Math.min(currX, xMin);
            yMax = Math.max(currY, yMax);
            yMin = Math.min(currY, yMin);
        }

        mapSizeX = Math.abs(xMin) + xMax + 1;
        mapSizeY = Math.abs(yMin) + yMax + 1;
        startX = Math.abs(xMin);
        startY = Math.abs(yMin);

        System.out.println("X " + xMin + " " + xMax);
        System.out.println("Y " + yMin + " " + yMax);
        System.out.println("Map size X " + mapSizeX + " Y " + mapSizeY);
        System.out.println("Start at X " + startX + " Y " + startY);

        // initialize empty map
        map = new String[mapSizeY][mapSizeX][layers];

        // ------------ set start position ------------
        HashSet<Integer> uniqueTailPositions = new HashSet<>();
        int headX = startX;
        int headY = startY;

        int tailX = startX;
        int tailY = startY;

        System.out.println("\n=== init ===\n");
        uniqueTailPositions.add(tailX + (tailY * mapSizeX));
//        printMap(startX, startY, tailX, tailY);

        // ------------ move the head ------------
        int lastHX;
        int lastHY;

        for (String line : input) {
            String[] instruction = line.split(" ");
            int amount = Integer.parseInt(instruction[1]);

            System.out.println("\n=== " + line + " ===\n");

            while (amount > 0) {
                amount--;
                lastHX = headX;
                lastHY = headY;

                switch (instruction[0]) {
                    case "R" -> headX++;
                    case "L" -> headX--;
                    case "U" -> headY++;
                    case "D" -> headY--;
                }

                // move tail when needed
                int dx = tailX - headX;
                int dy = tailY - headY;

                if (Math.abs(dx) == 1 && Math.abs(dy) > 1
                        || Math.abs(dx) > 1 && Math.abs(dy) == 1) {
                    // Head moved diagonally out of reach, use last known head position
                    tailX = lastHX;
                    tailY = lastHY;
                } else if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
                    if (dx < -1) {
                        tailX++;
                    }
                    if (dx > 1) {
                        tailX--;
                    }
                    if (dy < -1) {
                        tailY++;
                    }
                    if (dy > 1) {
                        tailY--;
                    }
                }

                // add to tail positions
                uniqueTailPositions.add(tailX + (tailY * mapSizeX));

//                printMap(headX, headY, tailX, tailY);
            }

        }

        return uniqueTailPositions.size();
    }

    private void printMap(int headX, int headY, int tailX, int tailY) {

        for (int y = mapSizeY - 1; y >= 0; y--) {
            for (int x = 0; x < mapSizeX; x++) {
                if (headX == x && headY == y) {
                    System.out.print("H");
                } else if (tailX == x && tailY == y) {
                    System.out.print("T");
                } else {
                    System.out.print(".");
                }
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        return 0;
    }
}
