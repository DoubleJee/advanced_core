package datastructure.other.skip_list;

import datastructure.AssertTool;
import datastructure.TimeTool;
import datastructure.other.map.TreeMap;

public class SkipListTest {

    public static void main(String[] args) {
        SkipList<Integer, Integer> skipList = new SkipList<>();

        test(skipList);
        test(skipList, 30, 10);
        // 与红黑树性能测试对比
        timeTest();
    }

    static void test(SkipList<Integer, Integer> sl) {
        for (int i = 0; i < 20; i++) {
            sl.put(i, i);
        }
        System.out.println(sl);

        for (int i = 0; i < 19; i++) {
            sl.remove(i);
            System.out.println(sl);
        }
    }

    static void timeTest() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        SkipList<Integer, Integer> list = new SkipList<>();
        int count = 100_0000;
        int delta = 10;

        // 和红黑树性能差不多
        TimeTool.checkTime("SkipList" , () -> {
            test(list, count, delta);
        });

        TimeTool.checkTime("TreeMap" , () -> {
            treeTest(map, count, delta);
        });
    }

    static void test(SkipList<Integer, Integer> sl, int count, int delta) {
        for (int i = 0; i < count; i++) {
            sl.put(i, i + delta);
        }

        for (int i = 0; i < count; i++) {
            AssertTool.test(sl.get(i) == i + delta);
        }

        AssertTool.test(sl.size() == count);

        for (int i = 0; i < count; i++) {
            AssertTool.test(sl.remove(i) == i + delta);
        }

        AssertTool.test(sl.size() == 0);
    }

    static void treeTest(TreeMap<Integer, Integer> treeMap, int count, int delta) {
        for (int i = 0; i < count; i++) {
            treeMap.put(i, i + delta);
        }

        for (int i = 0; i < count; i++) {
            AssertTool.test(treeMap.get(i) == i + delta);
        }

        AssertTool.test(treeMap.size() == count);

        for (int i = 0; i < count; i++) {
            AssertTool.test(treeMap.remove(i) == i + delta);
        }

        AssertTool.test(treeMap.size() == 0);
    }

}
