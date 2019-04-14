package design_pattern.decorator.decorator.impl;

import design_pattern.decorator.Beverage;
import design_pattern.decorator.decorator.CondimentDecorator;

/**
 * 摩卡调味料装饰
 */
public class Mocha extends CondimentDecorator {

    private Beverage beverage;

    public Mocha(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getName() {
        return this.beverage.getName() + "，摩卡";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.20;
    }
}
