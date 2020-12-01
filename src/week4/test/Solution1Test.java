package week4.test;


import org.junit.Test;
import week4.Solution1;

import java.util.*;

public class Solution1Test {
    @Test
    public void TestPQ() {
        int[] visited = new int[4];
        int[] distance = {1, 0, 3, 4};
        Comparator<Integer> cm = Comparator.comparingInt(index -> distance[index]);
        PriorityQueue<Integer> heap = new PriorityQueue<>(cm);
        heap.add(0);
        heap.add(1);
        heap.add(2);
        heap.add(3);
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());

    }


    @Test
    public void testCode() {

        Map<Integer, ArrayList<int[]>> graph = new HashMap<>();
        for (int i = 0; i < 3; i++) {
            graph.put(i, new ArrayList<int[]>());
        }
        int[] input = {1, 2, 1, 2, 3, 2};
        for (int i = 0, j = 0; j < 2; j++) {
            int first = input[i++] - 1;
            int second = input[i++] - 1;
            int distance = input[i++];
            graph.get(first).add(new int[]{second, distance});
        }
        week4.Solution1.dijkstra(3, 0, graph);

    }

}
