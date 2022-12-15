package org.example.puzzledays.day12;

import lombok.Getter;

@Getter
public class Point {
    int x;
    int y;
    int value;
    int id;

    public Point(int x, int y, int value, int mapX) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.id = (this.y * mapX) + x;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

//    @Override
//    public int compareTo(Object o) {
//        Point other = (Point) o;
//        if (this.x == other.x && this.y == other.y) {
//            return 0;
//        } else if (this.x > other.x || this.y > other.y) {
//            return 1;
//        } else {
//            return -1;
//        }
//    }
}
