package days;

import static helpers.InputHandler.*;

import java.util.Arrays;

public class Day08 {
	private static int accumulator;

	public static void main(String[] args) {
		System.out.println("Part 1: " + partOne());
		System.out.println("Part 2: " + partTwo());
	}

	static int partOne() {
		compute(asStringArray(8));
		return accumulator;
	}

	static int partTwo() {
		String[] input = asStringArray(8);

		for (int i = 0; i < input.length; i++) {
			if (!input[i].contains("acc")) {
				String[] instructions = changeInstruction(input, i);
				if (compute(instructions) >= instructions.length)
					return accumulator;
			}
		}
		return 0;
	}

	static int compute(String[] in) {
		accumulator = 0;
		boolean[] visited = new boolean[in.length];

		int i = 0;
		while (i < in.length && !visited[i]) {
			visited[i] = true;
			String op = in[i].substring(0, 4);
			int nbr = Integer.parseInt(in[i].substring(4));
			i += operation(op.trim(), nbr);
		}
		return i;
	}

	static String[] changeInstruction(String[] instructions, int index) {
		String[] copy = Arrays.copyOf(instructions, instructions.length);
		String opposite = instructions[index].contains("jmp") ? "nop " : "jmp ";
		opposite += instructions[index].substring(4);
		copy[index] = opposite;
		return copy;
	}

	static int operation(String op, int value) {
		switch (op) {
		case "acc":
			accumulator = accumulator + value;
			return 1;
		case "jmp":
			return value;
		case "nop":
			return 1;
		default:
			return 0;
		}
	}
}
