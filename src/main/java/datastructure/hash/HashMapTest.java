package datastructure.hash;

import datastructure.other.map.Map;

public class HashMapTest {

    public static void main(String[] args) {
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
        System.out.println(map.get(p1));
        System.out.println(map.get("jack"));
        System.out.println(map.get("rose"));
        System.out.println(map.get(null));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>");

        System.out.println("map size:" + map.size());
        System.out.println(map.remove(p1));
        System.out.println(map.remove("jack"));
        System.out.println(map.remove(null));
        System.out.println(map.get("jack"));
        System.out.println(map.get("rose"));
        System.out.println("map size:" + map.size());

    }
}
