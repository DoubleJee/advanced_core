package design_pattern.template;

public abstract class CaffeineBeverage {


    /**
     * 模板方法封装算法
     */
    public final void prepareRecipe(){
        //封装了做成饮料的算法（步骤），不可重写的，具体步骤由子类决定
        boilWater();
        brew();
        pourInCup();
        if(customerWantsCondiments()){
            addCondiments();
        }
    }


    /**
     * 煮开水
     */
    public void boilWater(){
        System.out.println("把水煮沸");
    }

    /**
     * 浸泡
     */
    public abstract void brew();

    /**
     * 放入杯子
     */
    public void pourInCup(){
        System.out.println("倒入杯子");
    }

    /**
     * 加入调料
     */
    public abstract void addCondiments();

    /**
     * 钩子方法 hook 子类可以不重写
     */
    public boolean customerWantsCondiments(){
        return true;
    }

}
