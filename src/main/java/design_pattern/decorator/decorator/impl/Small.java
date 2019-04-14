package design_pattern.decorator.decorator.impl;

import design_pattern.decorator.Beverage;
import design_pattern.decorator.decorator.CupDecorator;

public class Small extends CupDecorator {

    private Beverage beverage;

    public Small(Beverage beverage){
        this.beverage = beverage;
    }

    @Override
    public String getName() {
        return beverage.getName() + "，小杯";
    }

    @Override
    public double cost() {
        return beverage.cost();
    }
}
