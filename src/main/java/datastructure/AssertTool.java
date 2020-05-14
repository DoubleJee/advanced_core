package datastructure;

public class AssertTool {

    public static void test(boolean result){
        try {
            if (!result) throw new Exception("测试未通过");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
