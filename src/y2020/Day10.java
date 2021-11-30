package y2020;

import common.InputHandler;

import java.util.List;
import java.util.stream.Collectors;

public class Day10 {

    static int partOne() {
        List<Integer> adapters = InputHandler.asIntStream(2020,10).sorted().collect(Collectors.toList());
        adapters.add(0, 0);

        int ones = 0;
        int threes = 1;

        for (int i = 0; i < adapters.size() - 1; i++) {
            int diff = adapters.get(i + 1) - adapters.get(i);
            if (diff == 1)
                ones++;
            else
                threes++;
        }
        return ones * threes;
    }

    static long partTwo() {
        List<Integer> adapters = InputHandler.asIntStream(2020,10).sorted().collect(Collectors.toList());
        int max = adapters.get(adapters.size() - 1) + 3;
        adapters.add(max);
        adapters.add(0, 0);

        long[] dp = new long[max + 1];
        dp[0] = 1;

        for (int i = 0; i < adapters.size(); i++) {
            int adapter = adapters.get(i);

            for (int j = i + 1; j < adapters.size() && j <= i + 3; j++) {
                if (adapters.get(j) - adapter <= 3) {
                    dp[adapters.get(j)] += dp[adapter];
                }
            }
        }

        return dp[max];
    }

    public static void main(String[] args) {
        System.out.println(partOne());
        System.out.println(partTwo());
    }
}
