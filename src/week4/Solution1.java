package week4;

import javafx.util.Pair;

import java.util.*;


// Main Idea is to use Dijkstra and then tho find the length of the tree from 0 to n
public class Solution1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Map<Integer, Integer> results = new HashMap<>();

        for (int k = 0; k < t; k++) {
            int n = in.nextInt();
            int m = in.nextInt();
            //trying to do an arrayList of cheap tuples <Node, Distance to node>
            Map<Integer, ArrayList<int[]>> graph = new HashMap<>();
            for (int i = 0; i < n; i++) {
                graph.put(i, new ArrayList<int[]>());
            }
            for (int i = 0; i < m; i++) {
                int first = in.nextInt() - 1;
                int second = in.nextInt() - 1;
                int distance = in.nextInt();
                graph.get(first).add(new int[]{second, distance});
                graph.get(second).add(new int[]{first, distance});

            }
            int[] distances = dijkstra(n, 0, graph);
            results.put(k, distances[n - 1]);

            if (k != t - 1) {
                in.nextLine();
            }
        }

        for (int k = 0; k < t; k++) {
            System.out.printf("Case #%d: %d\n", k + 1, results.get(k));
        }

    }

    public static int[] dijkstra(int n, int start, Map<Integer, ArrayList<int[]>> graph) {
        boolean[] visited = new boolean[n];
        int[] distance = new int[n];
        Comparator<Integer> cm = Comparator.comparingInt(index -> distance[index]);
        PriorityQueue<Integer> heap = new PriorityQueue<>(cm);
        int[] previous = new int[n];
        for (int i = 0; i < n; i++) {
            distance[i] = Integer.MAX_VALUE;
            previous[i] = -1;
        }
        distance[start] = 0;
        for (int i = 0; i < n; i++) {
            heap.add(i);
        }
        while (!heap.isEmpty()) {
            int v = heap.poll();
            visited[v] = true;
            for (int[] u : graph.get(v)) {
                if (distance[v] + u[1] < distance[u[0]]) {
                    distance[u[0]] = distance[v] + u[1];
                    //decrease key by removing and adding
                    heap.remove(u[0]);
                    heap.add(u[0]);
                    previous[u[0]] = v;
                }
            }
        }

        return distance;
    }
}


//4
//3 2
//1 2 1
//2 3 2
//
//3 3
//1 2 1
//1 3 3
//2 3 1
//
//3 2
//1 2 1
//2 3 2
//
//3 2
//1 3 5
//2 3 4

//7
//4 4
//1 3 2
//1 4 2
//2 4 5
//3 4 3
//
//5 4
//1 3 2
//1 4 1
//2 5 5
//3 2 4
//
//5 5
//1 3 2
//1 5 2
//2 4 5
//2 5 2
//4 5 2
//
//3 2
//1 2 1
//2 3 5
//
//2 1
//1 2 3
//
//4 4
//1 2 3
//2 4 4
//1 4 1
//3 4 2
//
//5 6
//1 2 2
//1 4 5
//3 4 2
//2 4 1
//2 3 1
//4 5 5
