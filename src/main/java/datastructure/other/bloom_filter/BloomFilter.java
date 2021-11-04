package datastructure.other.bloom_filter;

// 布隆过滤器
public class BloomFilter<T> {

    // 二进制向量长度      （一共有多少个二进制位）
    private int bitSize;

    // 哈希函数个数
    private int hashSize;

    // 二进制向量
    private long[] bits;


    /**
     * @param n 数据规模
     * @param p 误判率 取值范围(0, 1)，为0.xxx
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0 || p >= 1) {
            throw new IllegalArgumentException("wrong n or p");
        }

        // 方法的默认底数是常数e
        double ln2 = Math.log(2);
        // 二进制向量长度， m = -( (n * lnp) / (ln2 ^ 2) )
        bitSize = (int) -((n * Math.log(p)) / (ln2 * ln2));
        // 哈希函数个数， k = m / n * ln2
        hashSize = (int) (bitSize / n * ln2);
        // bits数组长度     （就是 ceil(bitSize / Long.Size)， ceil就是向上取整，可以使用我们分页求页数的方式来求得长度，pageCount = (total + pageSize - 1) / pageSize 分页求页数公式也就是除法结果向上取整）
        bits = new long[(bitSize + Long.SIZE - 1)  / Long.SIZE];
    }

    /**
     * 添加元素
     */
    public void put(T value) {
        nullCheck(value);

        // 取得value的hashcode
        int hash1 = value.hashCode();
        // 配合hashcode做二次计算
        int hash2 = hash1 >>> 16;

        // k次hash计算
        for (int i = 1; i <= hashSize; i++) {
            // 通过i变动参与计算，实现多个hash函数效果
            int combinedHash = hash1 + (i * hash2);
            // 如果计算出的组合hash是负数，取反变为正数  （为了取模算出索引）
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 算出索引
            int index = combinedHash % bitSize;
            // 设置索引位置的二进制为1
            setBit(index);
        }
    }

    /**
     * 查找元素
     */
    public boolean contains(T value) {
        nullCheck(value);
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashSize; i++) {
            int combinedHash = hash1 + (i * hash2);
            if (combinedHash < 0) {
                combinedHash = ~combinedHash;
            }
            // 算出索引
            int index = combinedHash % bitSize;
            // 获取索引位置的二进制，如果为0，则一定不存在
            if (!getBit(index)) return false;
        }
        // 可能存在
        return true;
    }

    /**
     * 设置index位置的二进制为1
     */
    private void setBit(int index) {
        // bit所在数组下标
        int arrayIndex = bits.length - 1 - (index / Long.SIZE);
        // bit所在Long位置
        int bitIndex = index % Long.SIZE;
        bits[arrayIndex] |= 1 << bitIndex;
    }

    /**
     * 获取index位置的二进制
     * @return true为1，false为0
     */
    private boolean getBit(int index) {
        // bit所在数组下标
        int arrayIndex = bits.length - 1 - (index/ Long.SIZE);
        // bit所在Long位置
        int bitIndex = index % Long.SIZE;
        return (bits[arrayIndex] & (1 << bitIndex)) != 0;
    }

    // null检查
    private void nullCheck(T value) {
        if (value == null) throw new IllegalArgumentException(" value must not be null.");
    }

}
