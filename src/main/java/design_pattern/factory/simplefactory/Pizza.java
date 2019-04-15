package design_pattern.factory.simplefactory;

public abstract class Pizza {

    String name = "未知的披萨";

    /**
     * 准备制作
     */
    public void prepare(){
        System.out.println(name + "准备制作");
    }

    /**
     * 烘烤
     */
    public void bake(){
        System.out.println(name + "烘烤");
    }

    /**
     * 切片
     */
    public void cut(){
        System.out.println(name + "切片");
    }

    /**
     * 包装
     */
    public void box(){
        System.out.println(name + "包装");
    }
}
