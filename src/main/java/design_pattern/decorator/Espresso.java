package design_pattern.decorator;

/**
 * 浓咖啡
 */
public class Espresso extends Beverage {

    public Espresso(){
        name = "浓咖啡";
    }

    @Override
    public double cost() {
        return 1.90;
    }
}
