package datastructure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeTool {

    private static final SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss.SSS");

    public interface Task {
        void execute();
    }

    public static void checkTime(String taskName, Task task) {
        if (task == null) return;
        System.out.println(String.format("【%s】", taskName));
        System.out.println("开始：" + fmt.format(new Date()));
        long startTime = System.currentTimeMillis();
        task.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("结束：" + fmt.format(new Date()));
        System.out.println("耗时：" + (endTime - startTime) / 1000.0 + "秒");
        System.out.println("-----------------------------------------");
    }
}
