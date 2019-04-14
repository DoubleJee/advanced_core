package design_pattern.decorator.decorator;

import design_pattern.decorator.Beverage;
//饮料调料装饰
public abstract class CondimentDecorator extends Beverage {
    public abstract String getName();
}
