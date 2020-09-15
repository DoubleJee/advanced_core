package datastructure.other.map;

// 映射（字典）
public interface Map<K, V> {

    int size();

    boolean isEmpty();

    void clear();

    V put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    void traversal(Visitor<K,V> visitor);

    class Node<K, V> implements Comparable<Node<K, V>> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Node<K, V> o) {
            return ((Comparable)key).compareTo(o.key);
        }
    }


    interface Visitor<K,V>{
        boolean stop();
        void visit(K key,V value);
    }

}
