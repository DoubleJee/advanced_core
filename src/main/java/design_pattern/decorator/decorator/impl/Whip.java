package design_pattern.decorator.decorator.impl;

import design_pattern.decorator.Beverage;
import design_pattern.decorator.decorator.CondimentDecorator;

/**
 * 奶油调味料
 */
public class Whip extends CondimentDecorator {

    private Beverage beverage;

    public Whip(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getName() {
        return beverage.getName() + "，奶油";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.30;
    }
}
