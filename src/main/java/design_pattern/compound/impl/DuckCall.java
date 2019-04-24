package design_pattern.compound.impl;

import design_pattern.compound.Quackable;
import design_pattern.compound.observer.Observable;
import design_pattern.compound.observer.Observer;
import design_pattern.compound.observer.QuackObservable;

public class DuckCall implements Quackable {

    private QuackObservable quackObservable;

    public DuckCall() {
        quackObservable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("噶叽");
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        quackObservable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        quackObservable.notifyObservers();
    }
}
