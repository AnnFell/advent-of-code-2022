package org.example.puzzledays;

import org.example.puzzledays.day22.Tuple;
import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day22 extends PuzzleDay {
    public Day22() {
        super(true, false, false);
    }

    char[] map;
    int mapSizeX;
    int mapSizeY;
    Tuple position = new Tuple(-1, 0);
    int facing = 0;
    char facingChar = '>';

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        String directions = input.get(input.size() - 1);
        input.remove(input.size() - 1);
        input.remove(input.size() - 1);

        parseMap(input);

        String regex = "(\\d+|\\w)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(directions);

        while (matcher.find()) {
            for (int i = 0; i < matcher.groupCount(); i++) {
                String instruction = matcher.group(i);

                if (instruction.equals("L") || instruction.equals("R")) {
                    /* R 0 - D 1 - L 2 - U 3
                     * Turn right = +1
                     * Turn left = -1
                     * */
                    int direction = instruction.equals("R") ? 1 : -1;
                    facing = facing + direction < 0 ? 3 : (facing + direction) % 3;
                    setFacingChar();

                } else {

                    int amount = Integer.parseInt(instruction);
                    while (amount > 0) {
                        boolean isSuccesful = move();
                        if (isSuccesful) {
                            amount--;
                        } else {
                            break;
                        }
                    }
                }


            }
        }

        printMap();
        System.out.format("Position: X %s, Y %s, facing: %s\n", position.getX()+1, position.getY()+1, facing);

        return (1000L * (position.getY() + 1)) + (4L * (position.getX() + 1)) + facing;
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        return 0;
    }

    private boolean move(Tuple... point) {
        int newX = position.getX();
        int newY = position.getY();

        if (point.length > 0) {
            newX = point[0].getX();
            newY = point[0].getY();
        }

        // Use facing R 0 - D 1 - L 2 - U 3
        switch (facing) {
            case 0 -> newX = (newX + 1) % mapSizeX;
            case 1 -> newY = (newY + 1) % mapSizeY;
            case 2 -> newX = newX - 1 < 0 ? mapSizeX - 1 : newX - 1;
            case 3 -> newY = newY - 1 < 0 ? mapSizeY - 1 : newY - 1;
        }

        int indexOfNewPoint = newY * mapSizeX + newX;
        char pointValue = map[indexOfNewPoint];

        switch (pointValue) {
            case '#':
                return false;
            case '.':
            case '>':
            case 'v':
            case '<':
            case '^':
                position.setX(newX);
                position.setY(newY);
                map[indexOfNewPoint] = facingChar;
                return true;
            case ' ':
                return move(new Tuple(newX, newY));
            default:
                System.out.println("Did not find recognized character");
                return false;
        }
    }

    private void setFacingChar() {
        // Use facing R 0 - D 1 - L 2 - U 3
        switch (facing) {
            case 0 -> facingChar = '>';
            case 1 -> facingChar = 'v';
            case 2 -> facingChar = '<';
            case 3 -> facingChar = '^';
        }
        int indexOfNewPoint = position.getY() * mapSizeX + position.getX();
        map[indexOfNewPoint] = facingChar;
    }

    private void parseMap(ArrayList<String> input) {
        String longestMapLine = input.stream().max(Comparator.comparingInt(String::length)).orElse("");
        mapSizeX = longestMapLine.length();
        mapSizeY = input.size();
        map = new char[mapSizeY * mapSizeX];
        Arrays.fill(map, ' ');

        int x = 0;
        int y = 0;
        for (String line : input) {
            char[] chars = line.toCharArray();
            for (char point : chars) {
                // mark starting point
                if (y == 0 && position.getX() == -1 && point == '.') {
                    position.setX(x);
                    position.setY(0);
                }
                // add value to map
                map[y + x] = point;
                x++;
            }
            y += mapSizeX;
            x = 0;
        }

    }

    private void printMap() {
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                int index = (y * mapSizeX) + x;
                System.out.print(map[index]);
            }
            System.out.println();
        }
        System.out.println();
    }

}