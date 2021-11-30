package y2020;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static common.InputHandler.*;

public class Day05 {

	private static int partOne(char[] chars, int i, int lo, int hi) {
		if (lo == hi - 1) {
			return (chars[i] == 'F' || chars[i] == 'L') ? lo : hi;
		}

		int mid = (lo + hi) / 2;
		if (chars[i] == 'F' || chars[i] == 'L') {
			return partOne(chars, i + 1, lo, mid);
		} else {
			return partOne(chars, i + 1, mid + 1, hi);
		}
	}

	public static int partTwo(List<Integer> ids) {
		Collections.sort(ids);

		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i) + 1 != ids.get(i + 1))
				return ids.get(i) + 1;
		}
		throw new IllegalArgumentException(":(");
	}

	public static void main(String[] args) throws IOException {
		String[] in = asStringArray(2020,5);
		List<Integer> ids = new ArrayList<>();

		for (String line : in) {
			int row = partOne(line.substring(0, 7).toCharArray(), 0, 0, 127);
			int col = partOne(line.substring(7).toCharArray(), 0, 0, 7);
			int id = (row * 8) + col;
			ids.add(id);
		}

		System.out.println("Part 1: " + ids.stream().mapToInt(Integer::valueOf).max().getAsInt());
		System.out.println("Part 2: " + partTwo(ids));
	}
}
