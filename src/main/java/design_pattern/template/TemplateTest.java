package design_pattern.template;

import design_pattern.template.impl.Coffee;
import design_pattern.template.impl.Tea;

public class TemplateTest {

    public static void main(String[] args) {
        Tea tea = new Tea();
        tea.prepareRecipe();
        Coffee coffee = new Coffee();
        coffee.prepareRecipe();

        //模板方法模式：在一个方法中定义一个算法的骨架，而将一些步骤延迟到子类中，模板方法使得于子类可以在不改变算法结构的情况下，重新定义算法中的某些步骤。
    }
}
