package org.example.puzzledays;

import org.example.puzzledays.day09.Knot;
import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.List;

public class Day09 extends PuzzleDay {
    String[][] map;
    int mapSizeX;
    int mapSizeY;
    int startX;
    int startY;

    public Day09() {
        super(true, true, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        buildMap(input);
        return getSolution(2, false, input);
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        buildMap(input);
        return getSolution(10, false, input);
    }

    private void buildMap(ArrayList<String> input) {
        // find out map size and start position
        int xMax = 0;
        int xMin = 0;
        int yMax = 0;
        int yMin = 0;

        int currX = 0;
        int currY = 0;

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

        // initialize empty map
        map = new String[mapSizeY][mapSizeX];
    }

    private long getSolution(int numberOfKnots, boolean printSteps, ArrayList<String> input) {
        // ------------ set start position ------------
        if (printSteps) System.out.println("=== init ===");
        List<Knot> allKnots = new ArrayList<>();
        for (int i = 0; i < numberOfKnots; i++) {
            if (i == 0) {
                allKnots.add(new Knot(startX, startY, mapSizeX, null));
            } else {
                allKnots.add(new Knot(startX, startY, mapSizeX, allKnots.get(i - 1)));
            }
        }

        if (printSteps) printMap(allKnots);

        // ------------ move per instruction ------------
        for (String line : input) {
            String[] instruction = line.split(" ");
            int amount = Integer.parseInt(instruction[1]);

            if (printSteps) System.out.println("=== " + line + " ===");

            while (amount > 0) {
                amount--;

                // move first knot according to instructions
                Knot firstKnot = allKnots.get(0);
                int newX = firstKnot.getPositionX();
                int newY = firstKnot.getPositionY();
                switch (instruction[0]) {
                    case "R" -> newX++;
                    case "L" -> newX--;
                    case "U" -> newY++;
                    case "D" -> newY--;
                }
                firstKnot.setPosition(newX, newY);

                // move tail when needed
                for (int i = 0; i < allKnots.size(); i++) {
                    if (i != 0) {
                        allKnots.get(i).checkForMove();
                    }
                }

                if (amount == 0) {
                    if (printSteps) printMap(allKnots);
                }
            }

        }

        return allKnots.get(numberOfKnots - 1).getNumberOfVisitedPositions();
    }

    private void printMap(List<Knot> allKnots) {

        for (int y = mapSizeY - 1; y >= 0; y--) {
            for (int x = 0; x < mapSizeX; x++) {
                String point = ".";

                for (int i = 0; i < allKnots.size(); i++) {
                    Knot currKnot = allKnots.get(i);
                    if (x == currKnot.getPositionX()
                            && y == currKnot.getPositionY()) {
                        point = "" + i;
                        break;
                    }
                }

                System.out.print(point);
            }
            System.out.print("\n");
        }
        System.out.println();
    }
}
