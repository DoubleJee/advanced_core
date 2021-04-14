package basic_core.exception;

// 希望开发者重视并且认真处理的异常 检查型异常
public class BugMemoryException extends Exception {

    public BugMemoryException(){
        super("会导致内存溢出！");
    }
}
