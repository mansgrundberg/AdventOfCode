import common.InputHandler

fun partOne(input: List<String>): Pos {
    val carts = getCarts(input);
    val tracks = getTracks(input);

    while (true) {
        carts.sorted().forEach { cart ->
            cart.move(tracks);
            if (carts.any { it.pos.collidesWith(cart.pos) })
                return cart.pos;
        }
    }
}

data class Pos(val x: Int, val y: Int) {

    fun collidesWith(other: Pos): Boolean {
        return this !== other && this.x == other.x && this.y == other.y
    }
}

enum class Direction(val x: Int, val y: Int, val c: Char) {
    UP(0, -1, '^'),
    DOWN(0, 1, 'v'),
    LEFT(-1, 0, '<'),
    RIGHT(1, 0, '>');

    fun turnLeft(): Direction = when (this) {
        UP -> LEFT
        DOWN -> RIGHT
        LEFT -> DOWN
        RIGHT -> UP
    }

    fun turnRight(): Direction = when (this) {
        UP -> RIGHT
        DOWN -> LEFT
        LEFT -> UP
        RIGHT -> DOWN
    }

    companion object {
        fun fromChar(c: Char) = values().firstOrNull() { it.c == c }
    }
}

enum class Action {
    LEFT,
    FORWARD,
    RIGHT;

    fun nextAction(): Action = when (this) {
        LEFT -> FORWARD
        FORWARD -> RIGHT
        RIGHT -> LEFT
    }
}

data class Cart(var pos: Pos, var direction: Direction) : Comparable<Cart> {
    private var action = Action.LEFT

    fun move(tracks: List<List<Char>>) {
        pos = Pos(pos.x + direction.x, pos.y + direction.y)
        when (tracks[pos.y][pos.x]) {
            '+' -> turnAtCrossing()
            '\\' -> direction = when (direction) {
                Direction.UP, Direction.DOWN -> direction.turnLeft()
                else -> direction.turnRight()
            }
            '/' -> direction = when (direction) {
                Direction.LEFT, Direction.RIGHT -> direction.turnLeft()
                else -> direction.turnRight()
            }
        }
    }

    fun turnAtCrossing() {
        direction = when (action) {
            Action.LEFT -> direction.turnLeft()
            Action.RIGHT -> direction.turnRight()
            Action.FORWARD -> direction
        }
        action = action.nextAction()
    }

    override fun compareTo(other: Cart) =
            if (pos.y != other.pos.y) pos.y.compareTo(other.pos.y) else pos.x.compareTo(other.pos.x)
}

fun getCarts(input: List<String>) =
        input.mapIndexed { y, s ->
            s.mapIndexed { x, c ->
                c.toCart(x, y)
            }.filterNotNull()
        }.flatten();


fun getTracks(input: List<String>) =
        input.map { row ->
            row.toCharArray().map {
                when (it) {
                    '<', '>' -> '-'
                    '^', 'v' -> '|'
                    else -> it
                }
            }
        }

fun Char.toCart(x: Int, y: Int) = Direction.fromChar(this)?.let { Cart(Pos(x, y), it) }


fun main() {
    println(partOne(InputHandler.asString(2018, 13).split("\n")));
}
