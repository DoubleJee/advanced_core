package design_pattern.factory;

import design_pattern.factory.facotry.PizzaIngredientFactory;
import design_pattern.factory.facotry.impl.ShanghaiPizzaIngredientFactory;
import design_pattern.factory.pizza.Pizza;
import design_pattern.factory.pizza.impl.CheesePizza;
import design_pattern.factory.pizza.impl.SausagePizza;
import design_pattern.factory.pizza.impl.VeggiePizza;

public class ShanghaiPizzaStore extends PizzaStore {

    private PizzaIngredientFactory pizzaIngredientFactory;

    public ShanghaiPizzaStore(){
        pizzaIngredientFactory = new ShanghaiPizzaIngredientFactory();
    }

    /**
     * 创建披萨
     *
     * @param type 披萨类型
     * @return 披萨
     */
    @Override
    public Pizza createPizza(String type) {
        Pizza pizza = null;
        if(type.equals("cheese")){
            pizza = new CheesePizza(pizzaIngredientFactory);
        }else if(type.equals("sausage")){
            pizza = new SausagePizza(pizzaIngredientFactory);
        }else if(type.equals("veggie")){
            pizza = new VeggiePizza(pizzaIngredientFactory);
        }
        return pizza;
    }
}
