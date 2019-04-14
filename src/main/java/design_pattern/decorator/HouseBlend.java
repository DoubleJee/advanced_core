package design_pattern.decorator;

/**
 * 混合咖啡
 */
public class HouseBlend extends Beverage{

    public HouseBlend(){
        name = "混合咖啡";
    }

    @Override
    public double cost() {
        return 0.8;
    }
}
