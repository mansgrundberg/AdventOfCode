package aoc.y2020;

import java.util.Arrays;
import java.util.HashSet;

import static aoc.common.InputHandler.*;

public class Day09 {

	public static void main(String[] args) {
		long[] input = asLongArray(2020,9);
		long nbr = partOne(input);
		System.out.println("Part 1: " + nbr);
		System.out.println("Part 2: " + partTwo(input, nbr));
	}

	static long partOne(long[] input) {
		long[] copy = Arrays.copyOfRange(input, 0, 25);
		int lo = 0;
		int hi = 25;

		while (sums(copy, input[hi])) {
			lo++;
			hi++;
			copy = Arrays.copyOfRange(input, lo, hi);
		}
		return input[hi];
	}

	static long partTwo(long[] input, long nbr) {
		int lo = 0;
		int hi = 1;
		long sum = input[lo] + input[hi];

		while (sum != nbr) {
			if (sum < nbr) {
				hi++;
				sum += input[hi];
			} else {
				sum -= input[lo];
				lo++;
			}
		}
		return weakness(input, lo, hi);
	}
	
	static boolean sums(long[] input, Long sum) {
		HashSet<Long> values = new HashSet<Long>();
		for (long i : input) {
			if (values.contains(sum - i))
				return true;
			values.add(i);
		}
		return false;
	}
	
	static long weakness(long[] input, int lo, int hi) {
		long[] sub = Arrays.copyOfRange(input, lo, hi + 1);
		Arrays.sort(sub);
		return sub[0] + sub[sub.length - 1];
	}
}
