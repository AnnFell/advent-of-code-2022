package org.example.puzzledays;

import org.example.puzzledays.day12.Point;
import org.example.puzzledays.day12.Route;
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
        super(true, false, false);
    }

    @Override
    public long getSolutionPartOne(ArrayList<String> input) {
        parseMapToList(input);
//        printMapList(null);

        // starting route
        lookForShortestPath(null);

        for (int i = 0; i < mapX * mapY; i++) {
            // routes copy
            List<Route> currRoutes = new ArrayList<>(routes);
            for (Route route : currRoutes) {
                lookForShortestPath(route);
                if (foundRoutes.size() > 1) {
                    break;
                }
            }
            if (foundRoutes.size() > 1) {
                break;
            }
        }


        for (Route route : foundRoutes) {
            if (route.getLength() == smallestFoundRoute) {
                System.out.println(route.getLength());
            }
        }
        return smallestFoundRoute;
    }

    List<Route> routes = new ArrayList<>();
    List<Route> foundRoutes = new ArrayList<>();
    long smallestFoundRoute = Integer.MAX_VALUE;

    private void lookForShortestPath(Route route) {
        // begin at start
        if (route == null) {
            route = new Route(start.getX(), start.getY(), mapX, 9);
            routes.add(route);
        }
//        System.out.println("-----------------------------------------------");
//        printMapList(route);
//        System.out.println("== " + routes.indexOf(route) + " at " + atPoint);

        List<Point> neighboursToVisit = getNeighboursToVisit(route);
//        System.out.println(neighboursToVisit);

        // if route is not a dead end:
        if (!neighboursToVisit.isEmpty()) {
            for (Point neighbour : neighboursToVisit) {
                // check for stop conditions:
                // stop when the end, -1, is reached
                if (neighbour.getValue() == 36) {
                    route.addVisited(neighbour.getX(), neighbour.getY(), 36);
                    foundRoutes.add(route);
                    routes.remove(route);

                    System.out.println("found one!");
                    System.out.println(route.getLength());
                    printMapList(route);

                    if (smallestFoundRoute > route.getLength()) {
                        smallestFoundRoute = route.getLength();
                    }
                    return;
                }

                // optimize: stop searching if there is already a complete route that is shorter than the current one
                if (route.getLength() > smallestFoundRoute) {
                    routes.remove(route);
                    return;
                }

                // continue searching. If route splits, start a new route
                Route newRoute = new Route(neighbour.getX(), neighbour.getY(), mapX, neighbour.getValue(), route.getVisitedIds());
                routes.add(newRoute);
            }
        }
        routes.remove(route);
    }

    private List<Point> getNeighboursToVisit(Route routeSoFar) {
        int currX = routeSoFar.getLastX();
        int currY = routeSoFar.getLastY();
        int currValue = routeSoFar.getCurrValue();

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
                Integer id = point.getY() * mapX + point.getX();
                if ((!routeSoFar.getVisitedIds().contains(id)
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

    private void printMapList(Route highlight) {
        for (int i = 0; i < mapList.size(); i++) {
            if (i % mapX == 0) {
                System.out.println();
            }
            Point point = mapList.get(i);
            if (highlight != null && highlight.hasVisited(point.getX(), point.getY())) {
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
                    start = new Point(x, y, 9, mapX);
                    point = start;
                } else if (data == 'E') {
                    end = new Point(x, y, 36, mapX);
                    point = end;
                } else {
                    point = new Point(x, y, value, mapX);
                }
                mapList.add(point);
            }
        }

    }
}