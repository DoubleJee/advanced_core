package design_pattern.factory.pizza;

import design_pattern.factory.ingredient.Dough;
import design_pattern.factory.ingredient.Sauce;

public abstract class Pizza {

    protected String name = "未知的披萨";

    /**
     * 面团
     */
    protected Dough dough;

    /**
     * 酱料
     */
    protected Sauce sauce;

    /**
     * 准备制作
     */
    public abstract void prepare();

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
        System.out.println(name + " " + dough.name + "，" + sauce.name  + "。 完工！已经包装好了。");
    }
}
