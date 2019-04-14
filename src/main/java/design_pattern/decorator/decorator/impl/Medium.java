package design_pattern.decorator.decorator.impl;

import design_pattern.decorator.Beverage;
import design_pattern.decorator.decorator.CupDecorator;

public class Medium extends CupDecorator {
    private Beverage beverage;

    public Medium(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getName() {
        return beverage.getName() + "，中杯";
    }

    @Override
    public double cost() {
        return beverage.cost() + 0.5;
    }
}
