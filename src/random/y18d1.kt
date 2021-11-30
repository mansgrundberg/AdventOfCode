import common.InputHandler

fun partOne(input: List<Int>): Int {
    return input.sum();
}

fun partTwo(input: List<Int>): Int {
    var freq = mutableSetOf(0);
    var sum = 0;

    while (true) {
        input.forEach {
            sum += it;
            if (freq.contains(sum)) return sum else freq.add(sum);
        }
    }
}

fun main() {
    val input = InputHandler.asIntList(2018, 1);
    println(partOne(input));
    println(partTwo(input));
}