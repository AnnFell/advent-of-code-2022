package org.example.puzzledays;

import org.example.puzzledays.day12.Point;
import org.example.utils.PuzzleDay;

import java.util.ArrayList;
import java.util.List;

/*
 * Sabqponm
 * abcryxxl
 * accszExk
 * acctuvwj
 * abdefghi
 */

public class Day12 extends PuzzleDay {
    List<Point> mapList = new ArrayList<>();
    int mapX;
    int mapY;
    Point start;
    Point end;

    public Day12() {
        super(true, false, true);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        parseMapToList(input);
        printMapList(null);

        // starting route

        lookForShortestPath(null, null);

        for (List<Point> route : foundRoutes) {
            if (route.size() == smallestFoundRoute) {
                System.out.println(route.size());
                System.out.println(route);
            }
        }
        for (int i = 0; i < mapX * mapY; i++) {
            // routes copy
            List<List<Point>> currRoutes = new ArrayList<>(routes);
            for (List<Point> route : currRoutes) {
                lookForShortestPath(route, route.get(route.size() - 1));
            }
        }
        return 0;
    }

    List<List<Point>> routes = new ArrayList<>();
    List<List<Point>> foundRoutes = new ArrayList<>();
    long smallestFoundRoute = Integer.MAX_VALUE;

    private void lookForShortestPath(List<Point> route, Point atPoint) {
        // begin at start
        if (atPoint == null) {
            atPoint = start;
        }
        if (route == null) {
            route = new ArrayList<>();
            route.add(atPoint);
            routes.add(route);
        }
//        System.out.println("-----------------------------------------------");
//        printMapList(route);
//        System.out.println("== " + routes.indexOf(route) + " at " + atPoint);

        List<Point> neighboursToVisit = getNeighboursToVisit(route, atPoint);
//        System.out.println(neighboursToVisit);

        // if route is not a dead end:
        if (!neighboursToVisit.isEmpty()) {
            for (int i = 0; i < neighboursToVisit.size(); i++) {
                Point neighbour = neighboursToVisit.get(i);

                // check for stop conditions:
                // stop when the end, -1, is reached
                if (neighbour.getValue() == 36) {
                    route.add(neighbour);
                    foundRoutes.add(route);
                    routes.remove(route);
                    System.out.println("found one!");
                    System.out.println(route.size());
                    printMapList(route);
                    if (smallestFoundRoute > route.size()) {
                        smallestFoundRoute = route.size();
                    }
                    return;
                }

                // optimize: stop searching if there is already a complete route that is shorter than the current one
                if (route.size() + 1 > smallestFoundRoute) {
                    return;
                }

                // continue searching. If route splits, start a new route
                if (i == 0) {
                    route.add(neighboursToVisit.get(i));
                    // do not immediately go in depth!
                } else {
                    List<Point> newRoute = new ArrayList<>(route);
                    newRoute.add(neighbour);
                    routes.add(newRoute);
                }
            }
        }
    }

    private List<Point> getNeighboursToVisit(List<Point> routeSoFar, Point currPoint) {
        int currX = currPoint.getX();
        int currY = currPoint.getY();
        int currValue = currPoint.getValue();

        // move up, down, left or right
        Point down = getPointWithIndex(currX, currY + 1);
        Point up = getPointWithIndex(currX, currY - 1);
        Point left = getPointWithIndex((currX - 1), currY);
        Point right = getPointWithIndex((currX + 1), currY);
        Object[] check = new Object[]{down, up, left, right};

        List<Point> neighbours = new ArrayList<>();
        // not null
        // not visited
        // at most one higher
        for (Object x : check) {
            if (x != null) {
                Point point = (Point) x;
                if ((!routeSoFar.contains(x)
                        && point.getValue() - currValue <= 1)) {
                    neighbours.add(point);
                }
            }
        }

        return neighbours;
    }

    private Point getPointWithIndex(int x, int y) {
        if (y < 0 || y > mapY - 1
                || x < 0 || x > mapX - 1) {
            return null;
        }
        return mapList.get(x + (y * mapX));
    }

    @Override
    public long getSolutionPartTwo(ArrayList<String> input) {
        return 0;
    }

    private void printMapList(List<Point> highlight) {
        for (int i = 0; i < mapList.size(); i++) {
            if (i % mapX == 0) {
                System.out.println();
            }
            Point point = mapList.get(i);
            if (highlight != null && highlight.contains(point)) {
                System.out.format("\u001B[42m %2d \033[0m", point.getValue());
            } else {
                System.out.format(" %d ", point.getValue());
            }
        }
        System.out.print("\n\n");
    }

    private void parseMapToList(ArrayList<String> input) {
        mapX = input.get(0).length();
        mapY = input.size();

        Point point;
        for (int y = 0; y < mapY; y++) {
            for (int x = 0; x < mapX; x++) {
                char data = input.get(y).charAt(x);
                int value = Character.getNumericValue(data);

                if (data == 'S') {
                    start = new Point(x, y, 9);
                    point = start;
                } else if (data == 'E') {
                    end = new Point(x, y, 36);
                    point = end;
                } else {
                    point = new Point(x, y, value);
                }
                mapList.add(point);
            }
        }

    }
}