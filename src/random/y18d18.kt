package k0tlin.random

import java.lang.IllegalStateException
import common.InputHandler

fun partOne(input: String): Int {
    return solve(input, 10)
}

data class Pos(val x: Int, val y: Int) {}

private fun Pos.neighbours(): List<Pos> {
    val neighbours = mutableListOf<Pos>();
    for (dx in -1..1)
        for (dy in -1..1)
            if (dx != 0 || dy != 0)
                neighbours.add(Pos(x + dx, y + dy));
    return neighbours;
}

private fun solve(input: String, minutes: Int): Int {
    var acres = input.split("\n").flatMapIndexed { x, it ->
        it.mapIndexed { y, c -> Pos(x, y) to c.toString() }
    }.toMap();

    for (i in 1..minutes) {
        acres = nextState(acres);
    }
    return acres.count { it.value == "|" } * acres.count { it.value == "#" };
}

private fun nextState(input: Map<Pos, String>): Map<Pos, String> {
    return input.mapValues {
        val trees = it.key.neighbours().count { input.get(it) == "|" };
        val lumberyards = it.key.neighbours().count { input.get(it) == "#" };
        when (it.value) {
            "." -> if (trees >= 3) "|" else ".";
            "|" -> if (lumberyards >= 3) "#" else "|";
            "#" -> if (lumberyards >= 1 && trees >= 1) "#" else ".";
            else -> throw IllegalStateException();
        }
    }
}

fun main() {
    println(partOne(InputHandler.asString(2018, 18)));
}