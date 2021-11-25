package helpers;

public enum Direction {
    N(0, -1),
    NE(1, -1),
    E(1, 0),
    SE(1, 1),
    S(0, 1),
    SW(-1, 1),
    W(-1, 0),
    NW(-1, -1);

    public final int x;
    public final int y;
    private static final Direction[] directions = {N, NE, E, SE, S, SW, W, NW};

    private Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Direction[] directions() {
        return directions;
    }
}
