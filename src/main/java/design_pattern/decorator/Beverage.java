package design_pattern.decorator;

/**
 * 饮料
 */
public abstract class Beverage {

    String name = "未知的饮料";

    public String getName() {
        return name;
    }

    /**
     * 花费价格
     */
    public abstract double cost();
}
