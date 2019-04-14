package design_pattern.decorator.decorator.impl;

import design_pattern.decorator.Beverage;
import design_pattern.decorator.decorator.CupDecorator;

public class Big extends CupDecorator {
    private Beverage beverage;

    public Big(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getName() {
        return beverage.getName() + "，大杯";
    }

    @Override
    public double cost() {
        return beverage.cost() + 1;
    }
}
