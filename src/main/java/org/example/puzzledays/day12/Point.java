package org.example.puzzledays.day12;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Point implements Comparable {
    int x;
    int y;
    int value;

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }

    @Override
    public int compareTo(Object o) {
        Point other = (Point) o;
        if (this.x == other.x && this.y == other.y) {
            return 0;
        } else if (this.x > other.x || this.y > other.y) {
            return 1;
        } else {
            return -1;
        }
    }
}
