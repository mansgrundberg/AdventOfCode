package aoc.y2020;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import static aoc.common.InputHandler.*;

public class Day04 {
	
	 private static final Map<String, String> fields = Map.of(
	            "byr", "^(19[2-9][0-9]|200[0-2])$",
	            "iyr", "^(201[0-9]|2020)$",
	            "eyr", "^(202[0-9]|2030)$",
	            "hgt", "^((1[5-8][0-9]|19[0-3])cm)|((59|6[0-9]|7[0-6])in)$",
	            "hcl", "^#[0-9a-f]{6}$",
	            "ecl", "^(amb|blu|brn|gry|grn|hzl|oth)$",
	            "pid", "^[0-9]{9}$"
	    );
	
	public static boolean isValidPartOne(String passport) {
		for (String field : fields.keySet()) {
			if (!passport.contains(field)) 
				return false;
		}
		return true;
	}
	
	public static boolean isValidPartTwo(String passport) {
		String[] parts = passport.replace('\n', ' ').trim().split(" ");
		return Arrays.stream(parts).map(s -> s.split(":")).allMatch(s -> validValue(s[0], s[1]));
	}
	
	private static boolean validValue(String key, String value) {
		if (!fields.containsKey(key)) {
			return true;
		}
		return value.matches(fields.get(key));
	}
	
	public static void main(String[] args) throws IOException {
		String[] in = asString(2020,4).split("\n\n");
		
		int part1 = 0;
		int part2 = 0;
		
		for (String passport : in) {
			if (isValidPartOne(passport)) {
				part1++;
				part2 = (isValidPartTwo(passport)) ? part2 + 1: part2;
			}
		}
		System.out.println("Part 1: " + part1 + "\nPart 2: " + part2);
	}

}
