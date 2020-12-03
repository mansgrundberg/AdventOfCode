package aoc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Day02 {

	public static boolean isValidPassword1(int min, int max, char c, String password) {
		int counter = 0;
		for (char x : password.toCharArray()) {
			if (x == c)
				counter++;
		}

		return (counter >= min && counter <= max);
	}
	
	public static boolean isValidPassword2(int pos1, int pos2, char c, String password) {
		return (password.charAt(pos1-1) == c) ^ (password.charAt(pos2-1) == c);
	}

	public static void main(String[] args) throws IOException {
		String[] lines = Files.readAllLines(Paths.get("input/Day2.txt")).toArray(new String[0]);
		
		// part 1
		int counter = 0;
		for (String line : lines) {
			String[] parts = line.split("[-:' ']+");
			if (isValidPassword1(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2].charAt(0), parts[3].trim()))
				counter++;
		}
		System.out.println(counter);
		
		// part 2
		counter = 0;
		for (String line : lines) {
			String[] parts = line.split("[-:' ']+");
			if (isValidPassword2(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), parts[2].charAt(0), parts[3]))
				counter++;
		}
		System.out.println(counter);
	}
}
