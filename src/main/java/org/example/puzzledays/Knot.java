package org.example.puzzledays;

import java.util.HashSet;

public class Knot {
    Knot parent;
    private int positionX;
    private int positionY;
    private final HashSet<Integer> uniqueVisitedPositions = new HashSet<>();
    private final int MAPSIZE_X;

    public Knot(int positionX, int positionY, int MAPSIZE_X, Knot parent) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.MAPSIZE_X = MAPSIZE_X;
        this.parent = parent;
        addVisitedPosition(positionX, positionY);
    }

    public void checkForMove() {
        int dx = positionX - parent.positionX;
        int dy = positionY - parent.positionY;

        int newX = this.positionX;
        int newY = this.positionY;

        if (Math.abs(dx) == 1 && Math.abs(dy) > 1
                || Math.abs(dx) > 1 && Math.abs(dy) == 1) {
            if (dx <= -1 && dy >= 1) {
                newX++;
                newY--;
            }
            if (dx >= 1 && dy >= 1) {
                newY--;
                newX--;
            }
            if (dx <= -1 && dy <= -1) {
                newY++;
                newX++;
            }
            if (dx >= 1 && dy <= -1) {
                newY++;
                newX--;
            }

        } else if (Math.abs(dx) > 1 || Math.abs(dy) > 1) {
            if (dx < -1) {
                newX++;
            }
            if (dx > 1) {
                newX--;
            }
            if (dy < -1) {
                newY++;
            }
            if (dy > 1) {
                newY--;
            }
        }

        if (newX != this.positionX || newY != this.positionY) {
            setPosition(newX, newY);
        }
    }

    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
        addVisitedPosition(x, y);
    }

    private void addVisitedPosition(int x, int y) {
        uniqueVisitedPositions.add((x + (y * MAPSIZE_X)));
    }

    public int getNumberOfVisitedPositions() {
        return uniqueVisitedPositions.size();
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

}
