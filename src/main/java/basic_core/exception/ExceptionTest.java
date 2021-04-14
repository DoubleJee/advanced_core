package basic_core.exception;

import basic_core.grammar.Lambda;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class ExceptionTest {


    /**
     *  检查型异常，难以（无法）避免的异常，编译器会检查，如果没处理则无法编译，报错，强制的
     *  对于结果不可控
     *
     *  除了Error和RuntimeException类型以外的异常都是检查型异常（如直接继承Exception的类，含Exception）
     */
    static void checkException() throws ClassNotFoundException, IllegalAccessException, InstantiationException, FileNotFoundException {
        // 这个类存不存在，我们不确定，结果是随机的，不是显而易见的，不可控的，因此无法不好去避免
        // 这个方法会抛出检查型异常，我们必须处理
        Class<?> aClass = Class.forName("basic_core.grammar.Lambda");
        Lambda lambda = (Lambda) aClass.newInstance();
        System.out.println(lambda);

        // 我们不知道文件是不存在，无法通过代码避免，结果是随机的，不可控的，无法避免
        // 严谨的写代码，也不可避免
        FileOutputStream fileOutputStream = new FileOutputStream("C://1.txt");
    }


    /**
     * 非检查型异常：可以避免的异常，编译器不会检查，没有处理也可以编译，不强制的
     * 对于结果可控
     */
    static void noCheckException(){
        System.out.println(1);
        for (int i = 0; i < 20; i++) {
            // java.lang.OutOfMemoryError
            long [] a = new long[10_0000_0000];
        }
        System.out.println(2);

        // 确保传入的是数值字符串
        Integer i = new Integer("abc");
        // java.lang.NullPointerException
        System.out.println(i);

        // 我们只要确保有实例对象，就可以避免，结果不是随机的，是显而易见的，可控的
        // 严谨的写代码可以避免的
        StringBuilder sb = null;
        // java.lang.NullPointerException
        sb.append("love");
    }


    /**
     * 不管是检查时异常还是非检查时异常，只要在抛出异常的时候，没有主动去处理它，都会导致Java程序终止运行
     * 因为异常没有得到处理最终会抛给JVM，如果异常抛给了JVM，会导致Java程序终止运行
     */


    /**
     *  try-catch-final 捕获处理异常
     *  final在try或者catch执行后一定会执行，并且在return、break、continue之前执行
     */
    static void catchHandleException(){
        System.out.println(1);
        try {
            Object obj = "abc";
            Double d = (Double) obj;

            Class cls = Class.forName("basic_core.grammar.Lambda");
            Lambda lambda = (Lambda) cls.newInstance();
            System.out.println(lambda);
        } catch (ClassCastException e) {
            System.out.println("抛出了ClassCastException异常");
        } catch (ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        } catch (Exception e){
            System.out.println("Exception");
        } finally {
            System.out.println("final在try或者catch执行后一定会执行，并且在return、break、continue之前执行");
        }
        System.out.println(2);

    }


    /**
     * throws：将异常抛给上层方法，也是一种处理异常的方式
     */
    static void throwsHandleException() throws ClassNotFoundException {
        // 将这个代码可能会出现的异常上抛
        Class abc = Class.forName("Abc");
    }


    static void throwExceptionSetAge(int age) {
        if (age <= 0){
            // 抛出一个异常来警示
            throw new WrongAgeException(age);
        }
    }


    // main方法上抛到JVM，会导致Java程序终止运行
    public static void main(String[] args) throws ClassNotFoundException {
//        checkException();
//        noCheckException();
//        catchHandleException();
//        throwsHandleException();
        throwExceptionSetAge(-1);
    }
}
