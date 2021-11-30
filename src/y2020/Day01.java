package y2020;

import common.InputHandler;

import java.io.IOException;
import java.util.Arrays;

public class Day01 {

	// 2Sum
	public static int partOne(int[] arr, int sum) {
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
	public static int partTwo(int[] arr, int sum) {
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
		int[] arr = InputHandler.asIntArray(2020,1);
		int sum = 2020;

		Arrays.sort(arr);
		System.out.println("Part 1: " + partOne(arr, sum));
		System.out.println("Part 2: " + partTwo(arr, sum));
	}
}
