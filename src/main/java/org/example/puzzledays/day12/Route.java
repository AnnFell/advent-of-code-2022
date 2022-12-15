package org.example.puzzledays.day12;

import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Route {
    private int lastX;
    private int lastY;
    private final int mapX;
    private int currValue;
    private int length;
    Set<Integer> visitedIds = new HashSet<>();

    public Route(int x, int y, int mapX, int value, Set<Integer> ids) {
        this(x, y, mapX, value);
        visitedIds.addAll(ids);
    }

    public Route(int x, int y, int mapX, int value) {
        lastX = x;
        lastY = y;
        this.mapX = mapX;
        currValue = value;
        visitedIds.add(y * mapX + x);
    }

    public void addVisited(int x, int y, int value) {
        visitedIds.add(y * mapX + x);
        lastX = x;
        lastY = y;
        currValue = value;
        length++;
    }


    public boolean hasVisited(int x, int y) {
        Integer id = y * mapX + x;
        return visitedIds.contains(id);
    }


}
