package basic_core.grammar;

// 外部类， 最外部的类是顶级类
public class OuterClass {

    private static final String CLASS_NAME = "OuterClass";

    private int age;

    public void show(InnerClass innerClass){
        System.out.println("年龄：" + age + "，身高：" + innerClass.height);
    }


    /**
     *  非静态嵌套类（内部类），属于实例级的东西，通过外部类实例才可以创建，跟外部类实例挂钩，先有外部类实例才有内部类实例
     *  可用访问外部类实例的所有成员（即使被声明为private），反之亦然，享有本类private权限符范围
     *  不能定义静态成员(方法，属性)，除了编译时常量
     */
    public class InnerClass {

//        private static String field;

        private int height;

        // 内部类实例 可以访问 外部类实例的变量
        public void show(){
            System.out.println("年龄：" + OuterClass.this.age + "，身高：" + height);
        }

    }


    /**
     *  静态嵌套类，行为上就是普通的顶级类，只是定义代码写在了其他类中
     *  有一个特殊权限，就是可以访问外部类的所有成员，享有本类private权限符范围
     */
    public static class StaticNestedClass {

        private static String TYPE;

        static {
            TYPE = "静态嵌套类";
        }

        // 访问外部类的private常量
        public void call(){
            System.out.println("属于" + CLASS_NAME + "的" + TYPE);
        }

    }


    /**
     *  局部类，定义在代码块中的类，只能访问final或者有效final局部变量，享有本类private权限符范围
     */
    public void local() {
        // 有效final局部变量，就是只赋过一次值的局部变量
        int weight = 100;
        // final局部变量
        final char province = '皖';

        class Test{
            public void call(){
                System.out.println("省份：" + province + "，年龄：" + age + "，体重：" + weight);
            }
        }

        Test test = new Test();
        test.call();
    }

    /**
     *  匿名类，匿名实现接口或抽象类或派生类，只能访问final或者有效final局部变量，享有本类private权限符范围
     */
    public void anonymity(){
        int age = 10;
        new Runnable() {
            {

            }

            @Override
            public void run() {
                System.out.println("我的年龄是：" + age);
            }

        }.run();
    }

    @FunctionalInterface
    public interface Task {
        void execute();
    }

    public static void test(Task task) {
        long startTime = System.currentTimeMillis();
        task.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("-- ------------任务运行时间" + (endTime - startTime) + "ms--------------");

    }



    public static void main(String[] args) {
        // 内部类
        OuterClass outerClass = new OuterClass();
        outerClass.age = 18;
        InnerClass innerClass = outerClass.new InnerClass();
        innerClass.height = 180;
        outerClass.show(innerClass);
        innerClass.show();

        // 静态嵌套类
        StaticNestedClass staticNestedClass = new StaticNestedClass();
        staticNestedClass.call();

        // 局部类
        outerClass.local();

        // 匿名类
        outerClass.anonymity();

        // 匿名内部类 用来做代码传递
        test(new Task() {
            @Override
            public void execute() {
                String str = "";
                int count = 100;
                for (int i = 0; i < count; i++) {
                    str += i;
                }
            }
        });

        // 局部类和匿名内部类功能很相似，只会编译一个Class
    }

}