package design_pattern.factory;

import design_pattern.factory.pizza.Pizza;

public abstract class PizzaStore {
    /**
     * 下单制作披萨
     * @return 披萨
     */
    public final Pizza orderPizza(String type){
        Pizza pizza = createPizza(type);
        pizza.prepare();
        pizza.bake();
        pizza.cut();
        pizza.box();

        return pizza;
    }

    /**
     * 工厂方法
     */
    protected abstract Pizza createPizza(String type);





    public static void main(String[] args) {
        PizzaStore shanghaiPizzaStore = new ShanghaiPizzaStore();
        shanghaiPizzaStore.orderPizza("cheese");

        PizzaStore heFeiPizzaStore = new HeFeiPizzaStore();
        heFeiPizzaStore.orderPizza("sausage");
        //披萨商店是工厂方法模式，披萨调料是抽象工厂模式。
        // 披萨抽象商店，通过子类创建产品（披萨），披萨调料抽象工厂可能是创建一组产品（面团，酱汁）（具体调料工厂是创建产品组，每个组不同的产品）

        //工厂方法模式：定义了一个创建对象的接口（抽象类 or 接口），但由子类来决定实例化的具体类是哪一个。工厂方法让类把实例化推迟到子类。

        //抽象工厂模式：提供一个接口，用于创建相关或者依赖对象的家族，而不需要明确指定具体类


    }
}
