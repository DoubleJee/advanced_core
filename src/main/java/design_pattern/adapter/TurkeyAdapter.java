package design_pattern.adapter;

//火鸡适配器 目标对象鸭子，被适配者火鸡
public class TurkeyAdapter implements Duck {

    private Turkey turkey;

    public TurkeyAdapter(Turkey turkey){
        this.turkey = turkey;
    }

    @Override
    public void quack() {
        turkey.gobble();
    }

    @Override
    public void fly() {
        for (int i = 0;i < 5;i++){
            turkey.fly();
        }
    }
}
