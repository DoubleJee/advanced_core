package design_pattern.factory.facotry.impl;

import design_pattern.factory.facotry.PizzaIngredientFactory;
import design_pattern.factory.ingredient.Dough;
import design_pattern.factory.ingredient.Sauce;
import design_pattern.factory.ingredient.impl.BeefSauce;
import design_pattern.factory.ingredient.impl.ThinDough;

//合肥披萨调料工厂
public class HeFeiPizzaIngredientFactory implements PizzaIngredientFactory {
    @Override
    public Dough createDough() {
        return new ThinDough();
    }

    @Override
    public Sauce createSauce() {
        return new BeefSauce();
    }
}
