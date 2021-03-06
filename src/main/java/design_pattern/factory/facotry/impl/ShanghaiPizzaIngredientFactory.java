package design_pattern.factory.facotry.impl;

import design_pattern.factory.facotry.PizzaIngredientFactory;
import design_pattern.factory.ingredient.Dough;
import design_pattern.factory.ingredient.Sauce;
import design_pattern.factory.ingredient.impl.ThickDough;
import design_pattern.factory.ingredient.impl.TomatoSauce;

//上海披萨调料工厂
public class ShanghaiPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThickDough();
    }

    @Override
    public Sauce createSauce() {
        return new TomatoSauce();
    }
}
