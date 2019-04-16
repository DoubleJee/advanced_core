package design_pattern.factory.facotry;

import design_pattern.factory.ingredient.Dough;
import design_pattern.factory.ingredient.Sauce;
//披萨调料抽象工厂
public interface PizzaIngredientFactory {
    //面团
    Dough createDough();
    //酱汁
    Sauce createSauce();
}
