package org.example.utils;

import java.util.ArrayList;
import java.util.List;

public class MatrixUtils {

    /**
     * Give back values of all neighbours of a position in a matrix.
     *
     * @param x      x-index of position
     * @param y      y-index of position
     * @param matrix
     * @param <T>
     * @return values of neighbours as a list, index corresponds to these positions:
     * | 0 | 1 | 2 |
     * | 3 | X | 4 |
     * | 5 | 6 | 7 |
     * Value of neighbour is null when neighbour does not exist.
     */
    public static <T> List<T> getAllNeighbours(int x, int y, List<List<T>> matrix) {
        List<T> result = new ArrayList<>(8);
        return result;
    }

    /**
     * Give back a neighbour of a position in a matrix.
     *
     * @param x                 x-index of position
     * @param y                 y-index of position
     * @param matrix
     * @param neighbourLocation top-left, top, top-right, left, right, bottom-left, bottom, bottom-right
     * @param <T>
     * @return
     */
    public static <T> T getNeighbour(int x, int y, List<List<T>> matrix, String neighbourLocation) {
        switch (neighbourLocation) {
            case "top-left":
                break;
            case "top":
                break;
            case "top-right":
                break;
            case "left":
                break;
            case "right":
                break;
            case "bottom-left":
                break;
            case "bottom":
                break;
            case "bottom-right":
                break;
        }
        return null;
    }

    /**
     * Give back the indexes of the neighbours of a position in a matrix.
     *
     * @param x                 x-index of position
     * @param y                 y-index of position
     * @param matrixRowCount
     * @param matrixColumnCount
     * @return list of [x,y] index of existing neighbours
     */
    public static List<int[]> getIndexOfNeighbours(int x, int y, int matrixRowCount, int matrixColumnCount) {
        List<int[]> result = new ArrayList<>();
        return result;
    }

}
