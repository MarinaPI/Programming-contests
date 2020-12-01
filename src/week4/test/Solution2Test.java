package week4.test;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2Test {
    @Test
    public void TestFromMinutesToHoursConversion() {
       int input = 110;
       int hours = input/60;
       int minutes = input % 60;
       if (minutes < 10) {
           String.format("%d:%d0", hours, minutes);
       } else {
           String.format("%d:%d0", hours, minutes);
       }


    }

}
