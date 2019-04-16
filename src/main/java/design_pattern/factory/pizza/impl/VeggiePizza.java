package design_pattern.factory.pizza.impl;

import design_pattern.factory.facotry.PizzaIngredientFactory;
import design_pattern.factory.pizza.Pizza;

public class VeggiePizza extends Pizza {

    private PizzaIngredientFactory pizzaIngredientFactory;

    public VeggiePizza(PizzaIngredientFactory pizzaIngredientFactory){
        name = "素菜披萨";
        this.pizzaIngredientFactory = pizzaIngredientFactory;
    }

    /**
     * 准备制作
     */
    @Override
    public void prepare() {
        this.dough = pizzaIngredientFactory.createDough();
        this.sauce = pizzaIngredientFactory.createSauce();
        System.out.println(name + "，准备配料：" + dough.name + "，" + sauce.name);
    }
}
