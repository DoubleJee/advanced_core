package datastructure.hash;


import java.util.HashMap;
import java.util.Map;

public class HashFunction {

    /**
     * 字符串：字符从头到尾计算 每个字符 + (前一个字符 * n)
     * 例如 jack的hash值 = [(j * n + a) * n + c] * n + k   =  j * n^3 + a * n^2 + c * n^1 + k * n^0
     * n定义为31最佳
     */
    public static int hashCode(String string){
        int size = string.length();
        int hashCode = 0;
        for (int i = 0; i < size; i++) {
            char c = string.charAt(i);
            hashCode = hashCode * 31 + c;
        }
        return hashCode;
    }

    // 整数直接拿整数值做hash值
    public static int hashCode(int num){
        return num;
    }

    // 浮点数，将浮点数的bit，按照整数读，读到的整数值为hash值
    public static int hashCode(float f){
        return Float.floatToIntBits(f);
    }

    // long长整数，将高32bit和低32bit，异或运算，然后将32bit读成整数，作为hash值
    public static int hashCode(long num){
        // int强转会将long的低32位bit保留转换
        return (int) (num ^ (num >>> 32));
    }

    // double长浮点数，将double的bit，按照long整数读，然后按照long的方式转换为hash值
    public static int hashCode(double d){
        long num = Double.doubleToLongBits(d);
        return hashCode(num);
    }


    public static void main(String[] args) {

        int a = 110;
        float b = 10.6f;
        long c = 156;
        double d = 10.9;
        String rose = "rose";
        System.out.println(hashCode(a));
        System.out.println(hashCode(b));
        System.out.println(hashCode(c));
        System.out.println(hashCode(d));
        System.out.println(hashCode(rose));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        System.out.println(Integer.hashCode(a));
        System.out.println(Float.floatToIntBits(b));
        System.out.println(Long.hashCode(c));
        System.out.println(Double.hashCode(d));
        System.out.println(rose.hashCode());


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        // 取模优化
        System.out.println(-112133123 & 15);


        Person p1 = new Person(18,178.5,"gzz");
        Person p2 = new Person(18,178.5,"gzz");

        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p1.equals(p2));

        Map<Object,String> map = new HashMap<>();
        map.put(p1,"gzz");
        map.put(p2,"jack");
        System.out.println(map);
    }
}
