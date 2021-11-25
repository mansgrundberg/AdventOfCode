package aoc.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class InputHandler {

    public static String asString(int year, int day) {
        return readInput(year, day);
    }

    public static String[] asStringArray(int year, int day) {
        return asStringStream(year, day).toArray(String[]::new);
    }

    public static int[] asIntArray(int year, int day) {
    	return asStringStream(year, day).mapToInt(Integer::parseInt).toArray();
    }

    public static long[] asLongArray(int year, int day) {
    	return asStringStream(year, day).mapToLong(Long::parseLong).toArray();
    }

    public static char[][] as2dCharArray(int year, int day) {
        return asStringStream(year, day).map(String::toCharArray).toArray(char[][]::new);
    }

    public static Stream<Integer> asIntStream(int year, int day) {
		return asStringStream(year, day).map(Integer::parseInt);
    }

    public static Stream<String> asStringStream(int year, int day) {
        return Arrays.stream(readInput(year, day).split("\n"));
    }

    private static String readInput(int year, int day) {
        String file = String.format("resources/%d/day%d.txt", year, day);
        try {
            return Files.readString(Paths.get(file));
        } catch (IOException e) {
            System.out.println("Failed reading input");
        }

        throw new IllegalArgumentException("Failed reading input");
    }

}
