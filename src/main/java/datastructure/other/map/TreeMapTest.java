package datastructure.other.map;

public class TreeMapTest {

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("a", 11);
        map.put("b", 12);
        map.put("c", 13);
        map.put("c", 14);
        map.put("c",null);
        System.out.println(map.get("a"));
        System.out.println(map.containsKey("a"));
        System.out.println(map.containsKey("c"));
        System.out.println(map.containsValue(13));
        System.out.println(map.containsValue(14));
        System.out.println(map.containsValue(null));

        map.traversal(new Map.Visitor<String, Integer>() {
            @Override
            public boolean stop() {
                return false;
            }

            @Override
            public void visit(String key, Integer value) {
                System.out.println("key:[" + key + "],value:[" + value + "]");
            }
        });

    }
}
