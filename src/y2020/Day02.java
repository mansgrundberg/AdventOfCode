package y2020;

import common.InputHandler;

import java.io.IOException;

public class Day02 {
	
	public static void main(String[] args) throws IOException {
		long partOne = InputHandler.asStringStream(2020,2).map(Password::new).filter(Day02::partOne).count();
		long partTwo = InputHandler.asStringStream(2020,2).map(Password::new).filter(Day02::partTwo).count();

		System.out.printf("Part 1: %d\n", partOne);
		System.out.printf("Part 2: %d", partTwo);
	}
	
	public static boolean partOne(Password p) {
		int counter = 0;
		for (char x : p.chars) {
			if (x == p.c)
				counter++;
		}
		return (counter >= p.min && counter <= p.max);
	}

	public static boolean partTwo(Password p) {
		return (p.chars[p.min - 1] == p.c) ^ (p.chars[p.max - 1] == p.c);
	}

	private static class Password {
		public final int min;
		public final int max;
		public final char c;
		public final char[] chars;

		Password(String in) {
			String[] parts = in.split(" ");
			String[] minMax = parts[0].split("-");

			this.min = Integer.parseInt(minMax[0]);
			this.max = Integer.parseInt(minMax[1]);
			this.c = parts[1].charAt(0);
			this.chars = parts[2].toCharArray();
		}
	}
}
