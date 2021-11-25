package days;

import helpers.Direction;
import helpers.InputHandler;

import java.awt.*;
import java.nio.CharBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class Day11 {

    static int partOne(char[][] grid) {
        return solve(grid, 4, true);
    }

    static int partTwo(char[][] grid) {
        return solve(grid, 5, false);
    }

    static int solve(char[][] grid, int limit, boolean onlyAdjacent) {
        char[][] temp;

        do {
            temp = deepCopy(grid);
            grid = changeSeats(grid, limit, onlyAdjacent);
        } while (!Arrays.deepEquals(grid, temp));

        return countOccupied(grid);
    }


    private static char[][] changeSeats(char[][] grid, int limit, boolean onlyAdjacent) {
        char[][] next = new char[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int count = nbrOfOccupiedSeats(grid, new Point(i, j), onlyAdjacent);
                next[i][j] = switch (grid[i][j]) {
                    case 'L' -> count == 0 ? '#' : 'L';
                    case '#' -> count >= limit ? 'L' : '#';
                    default -> '.';
                };
            }
        }
        return next;
    }

    private static int nbrOfOccupiedSeats(char[][] grid, Point seat, boolean onlyAdjacent) {
        int nbrOfOccupiedSeats = 0;
        for (Direction dir : Direction.directions()) {
            Point p = new Point(seat.x + dir.x, seat.y + dir.y);
            while (!outOfBounds(grid, p)) {
                if (onlyAdjacent || grid[p.x][p.y] != '.') {
                    if (grid[p.x][p.y] == '#')
                        nbrOfOccupiedSeats++;
                    break;
                }
                p.move(p.x + dir.x, p.y + dir.y);
            }
        }
        return nbrOfOccupiedSeats;
    }

    private static char[][] deepCopy(char[][] array) {
        return Arrays.stream(array).map(char[]::clone).toArray(char[][]::new);
    }

    private static int countOccupied(char[][] seats) {
        return (int) Arrays.stream(seats).map(CharBuffer::wrap).flatMapToInt(CharBuffer::chars).filter(i -> i == '#').count();
    }
    private static boolean outOfBounds(char[][] grid, Point p) {
        return (p.x < 0 || p.x >= grid.length || p.y < 0 || p.y >= grid[p.x].length);
    }

    public static void main(String[] args) {
        char[][] grid = InputHandler.as2dCharArray(11);
        System.out.println("Part 1:" + Day11.partOne(grid));
        System.out.println("Part 2:" + Day11.partTwo(grid));
    }
}
