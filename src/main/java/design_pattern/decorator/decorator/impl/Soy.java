package design_pattern.decorator.decorator.impl;

import design_pattern.decorator.Beverage;
import design_pattern.decorator.decorator.CondimentDecorator;

/**
 * 豆浆调味料
 */
public class Soy extends CondimentDecorator {

    private Beverage beverage;

    public Soy(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getName() {
        return beverage.getName() + "，大豆";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.10;
    }
}
