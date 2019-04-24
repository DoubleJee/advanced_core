package design_pattern.compound;

import design_pattern.compound.observer.QuackObservable;

//鸭子叫
public interface Quackable extends QuackObservable {
    void quack();
}
