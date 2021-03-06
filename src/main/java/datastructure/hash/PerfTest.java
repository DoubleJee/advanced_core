package datastructure.hash;

import datastructure.TimeTool;
import datastructure.other.map.Map;
import datastructure.other.map.TreeMap;

public class PerfTest {


    public static void main(String[] args) {

        TimeTool.checkTime("HashMap", () -> {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < 100000; i++) {
                map.put(i, i);
            }

            for (int i = 0; i < 100000; i++) {
                map.get(i);
            }

            for (int i = 0; i < 100000; i++) {
                map.remove(i);
            }
        });

        TimeTool.checkTime("TreeMap", () -> {
            Map<Integer, Integer> map = new TreeMap<>();
            for (int i = 0; i < 100000; i++) {
                map.put(i, i);
            }

            for (int i = 0; i < 100000; i++) {
                map.get(i);
            }

            for (int i = 0; i < 100000; i++) {
                map.remove(i);
            }
        });

    }
}
