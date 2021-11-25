package helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class InputHandler {

    public static String asString(int day) {
        return readInput(day);
    }

    public static String[] asStringArray(int day) {
        return asStringStream(day).toArray(String[]::new);
    }

    public static int[] asIntArray(int day) {
    	return asStringStream(day).mapToInt(Integer::parseInt).toArray();
    }

    public static long[] asLongArray(int day) {
    	return asStringStream(day).mapToLong(Long::parseLong).toArray();
    }

    public static char[][] as2dCharArray(int day) {
        return asStringStream(day).map(String::toCharArray).toArray(char[][]::new);
    }

    public static Stream<Integer> asIntStream(int day) {
		return asStringStream(day).map(Integer::parseInt);
    }

    public static Stream<String> asStringStream(int day) {
        return Arrays.stream(readInput(day).split("\n"));
    }

    private static String readInput(int day) {
        String file = String.format("input/day%d.txt", day);
        try {
            return Files.readString(Paths.get(file));
        } catch (IOException e) {
            System.out.println("Failed reading input");
        }

        throw new IllegalArgumentException("Failed reading input");
    }

}
