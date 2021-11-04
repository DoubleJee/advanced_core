package datastructure.other.bloom_filter;

public class BloomFilterTest {

    public static void main(String[] args) {
        BloomFilter<Integer> bf = new BloomFilter<>(1_0000, 0.001);

        for (int i = 0; i < 10; i++) {
            bf.put(i);
        }
        for (int i = 0; i < 20; i++) {
            System.out.println(bf.contains(i));
        }
    }
}
