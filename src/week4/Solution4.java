package week4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Wrong answer -> my logic is wrong
public class Solution4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Map<Integer, Integer> results = new HashMap<>();


        for (int k = 0; k < t; k++) {
            int n = in.nextInt();
            int[] times = new int[n];

            Map<Integer, ArrayList<Integer>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                graph.put(i, new ArrayList<>());
            }
            for (int i = 0; i < n; i++) {
                int cost = in.nextInt();
                times[i] = cost;
                int successors = in.nextInt();
                for (int j = 0; j < successors; j++) {
                    int currentSuccessor = in.nextInt() - 1;
                    graph.get(currentSuccessor).add(i);
                }

            }

            int result = times[n-1];
            for (int u : graph.get(n - 1)) {
                result += times[u];
            }
            results.put(k, result);

            if (k != t - 1) {
                in.nextLine();
            }
        }

        for (int k = 0; k < t; k++) {
            System.out.printf("Case #%d: %d\n", k + 1, results.get(k));
        }

    }

}
