package y2020;

import common.InputHandler;

import java.util.Set;
import java.util.stream.Collectors;

public class Day06 {

	public static void main(String[] args) {
		System.out.printf("Part 1: %d\n", partOne(InputHandler.asString(2020,6).split("\n\n")));
		 System.out.printf("Part 2: %d", partTwo(InputHandler.asString(2020,6).split("\n\n")));
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
		int sum = 0;
		
		for (String s : in) {
			String[] lines = s.split("\n");
			Set<Integer> chars = lines[0].chars().boxed().collect(Collectors.toSet());
			for (String line : lines) {
				chars.retainAll(line.chars().boxed().collect(Collectors.toSet()));
			}
			sum += chars.size();
		}
		return sum;
	}
}
