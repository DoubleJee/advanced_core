package datastructure.hash;

import datastructure.AssertTool;
import datastructure.hash.model.Key;
import datastructure.hash.model.Person;
import datastructure.hash.model.SubKey1;
import datastructure.hash.model.SubKey2;
import datastructure.other.map.Map;

public class HashMapTest {

    static void test() {
        Person p1 = new Person(18, 178.5, "gzz");
        Person p2 = new Person(18, 178.5, "gzz");

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p1.equals(p2));

        Map<Object, Integer> map = new HashMap<>();
        map.put(p1, 1);
        map.put(p2, 2);
        map.put("jack", 3);
        map.put("rose", 4);
        map.put("jack", 5);
        map.put(null, 6);
//        System.out.println(map.get(p1));
//        System.out.println(map.get("jack"));
//        System.out.println(map.get("rose"));
//        System.out.println(map.get(null));
//
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
//
//        System.out.println("map size:" + map.size());
//        System.out.println(map.remove(p1));
//        System.out.println(map.remove("jack"));
//        System.out.println(map.remove(null));
//        System.out.println(map.get("jack"));
//        System.out.println(map.get("rose"));
//        System.out.println("map size:" + map.size());

        map.traversal(new Map.Visitor<Object, Integer>() {
            @Override
            public boolean stop() {
                return false;
            }

            @Override
            public void visit(Object key, Integer value) {
                System.out.println("key:" + key + ",value:" + value);
            }
        });

        System.out.println(map.containsKey(p1));
        System.out.println(map.containsKey(p2));
        System.out.println(map.containsKey(null));
        System.out.println(map.containsKey("jack"));
        System.out.println(map.containsKey("close"));

        System.out.println(map.containsValue(null));
        System.out.println(map.containsValue(6));
        System.out.println(map.containsValue(7));
    }

    static void test1() {
        Map<Key, Integer> map = new HashMap<>();
        for (int i = 1; i <= 19; i++) {
            map.put(new Key(i), i);
        }

        map.put(new Key(4), 100);
        System.out.println(map.size());
        System.out.println(map.get(new Key(4)));
        System.out.println(map.get(new Key(18)));
        System.out.println(map.get(new Key(122)));
    }

    static void test2(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            map.put(new Key(i), i + 5);
        }
        AssertTool.test(map.size() == 20);
        AssertTool.test(map.get(new Key(4)) == 4);
        AssertTool.test(map.get(new Key(5)) == 10);
        AssertTool.test(map.get(new Key(6)) == 11);
        AssertTool.test(map.get(new Key(7)) == 12);
        AssertTool.test(map.get(new Key(8)) == 8);
    }

    static void test3(HashMap<Object, Integer> map) {
        map.put(null, 1); // 1
        map.put(new Object(), 2); // 2
        map.put("jack", 3); // 3
        map.put(10, 4); // 4
        map.put(new Object(), 5); // 5
        map.put("jack", 6);
        map.put(10, 7);
        map.put(null, 8);
        map.put(10, null);
        AssertTool.test(map.size() == 5);
        AssertTool.test(map.get(null) == 8);
        AssertTool.test(map.get("jack") == 6);
        AssertTool.test(map.get(10) == null);
        AssertTool.test(map.get(new Object()) == null);
        AssertTool.test(map.containsKey(10));
        AssertTool.test(map.containsKey(null));
        AssertTool.test(map.containsValue(null));
        AssertTool.test(map.containsValue(1) == false);
    }

    static void test4(HashMap<Object, Integer> map) {
        map.put("jack", 1);
        map.put("rose", 2);
        map.put("jim", 3);
        map.put("jake", 4);
        map.remove("jack");
        map.remove("jim");
            for (int i = 1; i <= 10; i++) {
                map.put("test" + i, i);
            map.put(new Key(i), i);
        }
        for (int i = 5; i <= 7; i++) {
            AssertTool.test(map.remove(new Key(i)) == i);
        }
        for (int i = 1; i <= 3; i++) {
            map.put(new Key(i), i + 5);
        }
        AssertTool.test(map.size() == 19);
        AssertTool.test(map.get(new Key(1)) == 6);
        AssertTool.test(map.get(new Key(2)) == 7);
        AssertTool.test(map.get(new Key(3)) == 8);
        AssertTool.test(map.get(new Key(4)) == 4);
        AssertTool.test(map.get(new Key(5)) == null);
        AssertTool.test(map.get(new Key(6)) == null);
        AssertTool.test(map.get(new Key(7)) == null);
        AssertTool.test(map.get(new Key(8)) == 8);
        map.traversal(new Map.Visitor<Object, Integer>() {

            @Override
            public boolean stop() {
                return false;
            }

            public void visit(Object key, Integer value) {
                System.out.println(key + "_" + value);
            }
        });
    }

    static void test5(HashMap<Object, Integer> map) {
        for (int i = 1; i <= 20; i++) {
            map.put(new SubKey1(i), i);
        }
        map.put(new SubKey2(1), 5);
        AssertTool.test(map.get(new SubKey1(1)) == 5);
        AssertTool.test(map.get(new SubKey2(1)) == 5);
        AssertTool.test(map.size() == 20);
    }

    public static void main(String[] args) {
        test2(new HashMap<>());
        test3(new HashMap<>());
        test4(new HashMap<>());
        test5(new HashMap<>());

    }
}
