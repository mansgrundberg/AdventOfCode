package y2020;

import common.Direction;
import common.InputHandler;

import java.awt.*;

public class Day12 {

    public static int partOne() {
        return solve(InputHandler.asStringArray(2020, 12));
    }

    private static int solve(String[] input) {
        Direction facing = Direction.valueOf("E");
        Point coordinate = new Point(0, 0);
        for (String line : input) {
            String action = line.substring(0, 1);
            int value = Integer.parseInt(line.substring(1));
            navigate(action, value, facing, coordinate);
        }
        return Math.abs(coordinate.x - coordinate.y);
    }

    private static void navigate(String action, int value, Direction facing, Point coordinate) {
        switch (action) {
            case "N", "E", "S", "W" -> {
                Direction dir = Direction.valueOf(action);
                Point p = new Point(dir.x * value, dir.y * value);
                move(coordinate, p);
            }
            case "L" -> facing = facing.rotateLeft(value);
            case "R" -> facing = facing.rotateRight(value);
            case "F" -> move(coordinate, new Point(facing.x * value, facing.y * value));
        }
    }

    private static void move(Point coordinate, Point steps) {
        coordinate.move(coordinate.x + steps.x, coordinate.y + steps.y);
    }


    public static void main(String[] args) {
        System.out.println("Part 1: " + partOne());
    }
}
