package week4;

import java.util.*;

public class Solution3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        Map<Integer, String> results = new HashMap<>();

        for (int k = 0; k < t; k++) {
            int cities = in.nextInt();
            int roads = in.nextInt();
            int supermarkets = in.nextInt();
            int origin = in.nextInt() - 1;
            int destination = in.nextInt() - 1;
            String result = "";
            //trying to do an arrayList of cheap tuples <Node, Distance to node>
            Map<Integer, ArrayList<int[]>> graph = new HashMap<>();
            int[] vertexWeight = new int[cities];
            for (int i = 0; i < cities; i++) {
                graph.put(i, new ArrayList<int[]>());
            }

            if (supermarkets == 0) {
                result = "no";
            }

            //problem with multiple roads between cities?
            for (int i = 0; i < roads; i++) {
                int first = in.nextInt() - 1;
                int second = in.nextInt() - 1;
                int distance = in.nextInt();
                //maybe make them undirected
                graph.get(first).add(new int[]{second, distance});
            }

            for (int i = 0; i < supermarkets; i++) {
                int city = in.nextInt() - 1;
                int time = in.nextInt();
                if (vertexWeight[city] != 0 && vertexWeight[city] < time) {
                    vertexWeight[city] = time;
                } else if (vertexWeight[city] == 0) {
                    vertexWeight[city] = time;
                }
            }


            boolean[] visited = new boolean[cities];
            int[] distance = new int[cities];
            Comparator<Integer> cm = Comparator.comparingInt(index -> distance[index]);
            PriorityQueue<Integer> heap = new PriorityQueue<>(cm);
            int[] previous = new int[cities];
            for (int i = 0; i < cities; i++) {
                distance[i] = Integer.MAX_VALUE;
                previous[i] = -1;
            }
            distance[origin] = 0;
            for (int i = 0; i < cities; i++) {
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

            if (visited[destination]) {
                int smallestDistance = distance[destination];
                int currentNode = destination;
                int quickestMarket = Integer.MAX_VALUE;
                while (currentNode != -1) {
                    if (vertexWeight[currentNode] < quickestMarket) {
                        quickestMarket = vertexWeight[currentNode];
                    }
                    currentNode = previous[currentNode];
                }

//                int result = smallestDistance + quickestMarket;
                //TODO get the hours + minutes
                results.put(k, String.valueOf(result));
            } else {
                results.put(k, "impossible");
            }
            if (k != t - 1) {
                in.nextLine();
            }

        }
        for (int k = 0; k < t; k++) {
            //set to the minutes
            System.out.printf("Case #%d: %s\n", k + 1, results.get(k));
        }

    }

}
