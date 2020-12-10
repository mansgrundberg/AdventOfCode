package days;

import static helpers.InputHandler.*;

public class Day06 {

	public static void main(String[] args) {
		System.out.printf("Part 1: %d\n", partOne(asString(6).split("\n\n")));
		// System.out.printf("Part 2: %d", partTwo(asString(6).split("\n\n")));
	}

	static int partOne(String[] in) {
		int sum = 0;
		for (String s : in) {
			s = s.replaceAll("\n", "");
			sum += s.chars().distinct().count();
		}
		return sum;
	}

	static int partTwo(String[] in) {
		return 0;
	}
}
