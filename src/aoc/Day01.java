package aoc;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Day01 {

	// 2Sum
	public int part1(int[] arr, int sum) {
		int l = 0;
		int r = arr.length - 1;
		while (l < r) {
			if (arr[l] + arr[r] == sum) {
				return (arr[l] * arr[r]);
			} else if (arr[l] + arr[r] < sum) {
				l++;
			} else
				r--;
		}
		return -1;
	}

	// 3Sum
	public int part2(int[] arr, int sum) {
		int l;
		int r;
		for (int i = 0; i < arr.length - 2; i++) {
			l = i + 1;
			r = arr.length - 1;
			while (l < r) {
				if (arr[i] + arr[l] + arr[r] == sum) {
					return (arr[i] * arr[l] * arr[r]);
				} else if (arr[i] + arr[l] + arr[r] < sum) {
					l++;
				} else
					r--;
			}
		}
		return -1;
	}

	public static void main(String[] args) throws IOException {
		int[] arr = Files.readAllLines(Paths.get("input/Day1.txt")).stream().mapToInt(Integer::valueOf).toArray();
		int sum = 2020;
		Day01 prog = new Day01();

		Arrays.sort(arr);
		System.out.println(prog.part1(arr, sum));
		System.out.println(prog.part2(arr, sum));
	}
}
