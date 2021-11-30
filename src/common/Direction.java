package common;

import java.util.HashMap;
import java.util.Map;

public enum Direction {
    N(0,0, -1),
    NE(45,1, -1),
    E(90,1, 0),
    SE(135,1, 1),
    S(180,0, 1),
    SW(225,-1, 1),
    W(270,-1, 0),
    NW(315,-1, -1);

    public final int degrees;
    public final int x;
    public final int y;
    private static Map<Integer, Direction> map = new HashMap<>();
    private static final Direction[] allDirections = {N, NE, E, SE, S, SW, W, NW};

    private Direction(int degrees, int x, int y) {
        this.degrees = degrees;
        this.x = x;
        this.y = y;
    }

    static {
        for (Direction direction : Direction.values()) {
            map.put(direction.degrees, direction);
        }
    }

    public Direction rotateLeft(int degrees) {
        return map.get(Math.floorMod(this.degrees - degrees, 360));
    }

    public Direction rotateRight(int degrees) {
        return map.get(Math.floorMod(this.degrees + degrees, 360));
    }

    public static Direction[] directions() {
        return allDirections;
    }
}
