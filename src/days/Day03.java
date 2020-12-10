package days;

import java.io.IOException;
import static helpers.InputHandler.*;

public class Day03 {

	public static int trees(char[][] in, int right, int down) {
		int position = 0;
		int trees = 0;

		for (int i = 0; i < in.length; i += down) {
			if (in[i][position] == '#')
				trees++;
			position = (position + right) % in[i].length;
		}
		return trees;
	}

	public static void main(String[] args) throws IOException {
		char[][] input = as2dCharArray(3);

		int part1 = trees(input, 3, 1);
		long part2 = (long) trees(input, 1, 1) * trees(input, 5, 1) * trees(input, 7, 1) * trees(input, 1, 2)
				* part1;
		System.out.println("Part 1: " + part1);
		System.out.println("Part 2: " + part2);
	}
}
