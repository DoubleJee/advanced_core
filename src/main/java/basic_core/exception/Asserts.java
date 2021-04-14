package basic_core.exception;

// 断言类
public class Asserts {

    static void test(boolean result){
        if (result) return;
//        throw new RuntimeException("条件不成立");

//        try {
//            throw new RuntimeException("条件不成立");
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//        }

        // 如果条件不成立，直接打印输出多少行出的错误
        System.err.println(new RuntimeException().getStackTrace()[1]);
    }


    public static void main(String[] args) {
        Asserts.test(add(1,2) == 3);
        Asserts.test(sub(2,1) == 1);

    }


    static int add(int a, int b){
        return a - b;
    }

    static int sub(int a, int b){
        return a + b;
    }
}
