package basic_core.grammar;

public class Lambda {

    static void test(Task task){
        long startTime = System.currentTimeMillis();
        task.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("--------------任务运行时间" + (endTime - startTime) + "ms--------------");
    }

    static void paramTest(Object param, ParamTask task){
        long startTime = System.currentTimeMillis();
        task.execute(param);
        long endTime = System.currentTimeMillis();
        System.out.println("--------------任务运行时间" + (endTime - startTime) + "ms--------------");
    }

    @FunctionalInterface
    interface Task {
        boolean execute();
    }

    @FunctionalInterface
    interface ParamTask {
        boolean execute(Object param);
    }

    @FunctionalInterface
    interface NumberTask {
        Integer test(Integer v1, Integer v2);
    }



    public static void main(String[] args) {

        /**
         * lambda表达式标准写法：
         *  (参数列表) -> {
         *        方法体;
         *  }
         */

        // lambda可以用来简化 函数式接口匿名类
        // lambda没有引入新的作用域


        test(() -> {
            String str = "";
            int count = 100;
            for (int i = 0; i < count; i++) {
                str += i;
            }
            return true;
        });



        paramTest("love", new ParamTask() {
            @Override
            public boolean execute(Object param) {
                System.out.println(param);
                return true;
            }
        });



//        paramTest("love", (String param) -> {
//        }

//        paramTest("love", (param) -> true);


        paramTest("love", param -> {
            System.out.println(param);
            return true;
        });


        NumberTask addNumberTask = (v1, v2) -> v1 + v2;
        System.out.println(addNumberTask.test(1,2));

        // Lambda中的内容仅仅只是调用某个方法，可以使用方法引用来简化
        NumberTask maxNumberTask = Math::max;
        System.out.println(maxNumberTask.test(5,7));


        // 特定类型任意实例 方法引用，    v1.compareTo(v2)
        NumberTask compareNumberTask = Integer::compareTo;



    }
}
