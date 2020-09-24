package datastructure.hash;

import datastructure.hash.model.Key;
import datastructure.hash.model.Person;
import datastructure.other.map.Map;

public class HashMapTest {

    static void test1() {
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

    public static void main(String[] args) {
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
}
