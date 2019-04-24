package design_pattern.compound.factory;

import design_pattern.compound.Quackable;
import design_pattern.compound.impl.DuckCall;
import design_pattern.compound.impl.MallardDuck;
import design_pattern.compound.impl.RedheadDuck;
import design_pattern.compound.impl.RubberDuck;

public class DuckFactory extends AbstractDuckFactory {
    @Override
    public Quackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    public Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    @Override
    public Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    public Quackable createRubberDuck() {
        return new RubberDuck();
    }
}
