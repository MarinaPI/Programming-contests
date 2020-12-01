package week4;

import java.util.*;

//I am assuming that they already got the shortest path and we only need to check if there is
// path to this node with equal length
public class Solution5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Map<Integer, String> results = new HashMap<>();

        for (int k = 0; k < t; k++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int pathLength = in.nextInt();
            int[] path = new int[pathLength];
            String result = "no";

            if(m > 0) {
                //trying to do an arrayList of cheap tuples <Node, Distance to node>
                Map<Integer, ArrayList<int[]>> graph = new HashMap<>();
                for (int i = 0; i < n; i++) {
                    graph.put(i, new ArrayList<int[]>());
                }
                for (int i = 0; i < pathLength; i++) {
                    path[i] = in.nextInt() - 1;
                }

                for (int i = 0; i < m; i++) {
                    int first = in.nextInt() - 1;
                    int second = in.nextInt() - 1;
                    int distance = in.nextInt();
                    graph.get(first).add(new int[]{second, distance});
                    graph.get(second).add(new int[]{first, distance});
                }
                //The shortest path finding
                boolean[] visited = new boolean[n];
                int[] distance = new int[n];
                int lenghtOfTheNewPath = 1;
                Comparator<Integer> comparator = Comparator.comparingInt(index -> distance[index]);
                PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);
                 int[] previous = new int[n];
                for (int i = 0; i < n; i++) {
                    distance[i] = Integer.MAX_VALUE;
                    // previous[i] = -1;
                }
                distance[0] = 0;
                for (int i = 0; i < n; i++) {
                    priorityQueue.add(i);
                }
                while (!priorityQueue.isEmpty()) {
                    int v = priorityQueue.poll();
                    if (!visited[v]) {
                        for (int[] u : graph.get(v)) {
                            if (distance[v] + u[1] < distance[u[0]]) {
                                distance[u[0]] = distance[v] + u[1];
                                //decrease key
                                priorityQueue.remove(u[0]);
                                priorityQueue.add(u[0]);
                                 previous[u[0]] = v;
                                //Idea is to find a path with the same length as the sortest one
                            } else if (u[0] == n - 1 && distance[v] + u[1] == distance[u[0]] && !visited[u[0]]) {
                                result = "yes";
                            }
                        }
                        visited[v] = true;
                    }
                }

                //check if the found shortest path is the same
                if (distance[n-1] < pathLength) {
                    result = "yes";
                }
            }
             
             results.put(k, result);
             
            if (k != t - 1) {
                in.nextLine();
            }
        }

        for (int k = 0; k < t; k++) {
            System.out.printf("Case #%d: %s\n", k + 1, results.get(k));
        }

    }

}

