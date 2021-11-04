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




        // 爬虫判重
        String[] urls = {};
        BloomFilter<String> strBf = new BloomFilter<>(1_0000_0000, 0.001);

        for (String url : urls) {
            // 判断有没有爬过
            if (strBf.contains(url)) continue;
            // 爬这个url

            // 放进布隆过滤器
            strBf.put(url);
        }
    }
}
