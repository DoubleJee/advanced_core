package design_pattern.compound.observer;

//鸭子观察者
public interface QuackObservable {
    void registerObserver(Observer observer);
    void notifyObservers();
}
