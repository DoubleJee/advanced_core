package basic_core.exception;

// 不严格要求开发者去处理的异常 非检查型异常
public class WrongAgeException extends RuntimeException {

    public WrongAgeException(int age){
        super("错误年龄：" + age + "，年龄应该 > 0");
    }
}
