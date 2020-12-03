package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day03 {

	public static int trees(String[] input, int right, int down) {
		int position = 0;
		int trees = 0;

		for (int i = 0; i < input.length; i += down) {
			if (input[i].charAt(position) == '#')
				trees++;
			position = (position + right) % input[i].length();
		}
		return trees;
	}

	public static void main(String[] args) throws IOException {
		String[] input = Files.readAllLines(Paths.get("input/Day3.txt")).toArray(new String[0]);

		int part1 = trees(input, 3, 1);
		long part2 = (long)trees(input, 1, 1) * trees(input, 5, 1) * trees(input, 7, 1) * trees(input, 1, 2) * part1;
		System.out.println(part1);
		System.out.println(part2);
	}
}
