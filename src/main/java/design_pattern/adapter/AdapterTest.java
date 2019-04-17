package design_pattern.adapter;

import design_pattern.adapter.duck.*;
import design_pattern.adapter.duck.impl.MallardDuck;
import design_pattern.adapter.duck.impl.WildTurkey;
import design_pattern.adapter.plug.*;
import design_pattern.adapter.plug.impl.ComputerThreePlug;
import design_pattern.adapter.plug.impl.IPhoneTwoPlug;

public class AdapterTest {
    public static void main(String[] args) throws InterruptedException {
        Duck duck = new MallardDuck();
        duck.quack();
        duck.fly();
        Turkey turkey = new WildTurkey();
        turkey.gobble();
        turkey.fly();
        //目标对象是鸭子 -> 适配器 -> 被适配者是火鸡，客户看着是在对鸭子访问，但是适配给火鸡给操作了
        //客户 -> 鸭子 -> 适配器 -> 火鸡
        Duck turkeyAdapter = new TurkeyAdapter(turkey);
        turkeyAdapter.quack();
        turkeyAdapter.fly();
        //目标对象是火鸡 -> 适配器 -> 被适配者是鸭子，客户看着是在对火鸡访问，但是适配给了鸭子给操作了
        //客户 -> 火鸡 -> 鸭子
        Turkey duckAdapter = new DuckAdapter(duck);
        duckAdapter.gobble();
        duckAdapter.fly();

        Thread.sleep(50);


        TwoPlug twoPlug = new IPhoneTwoPlug();
        ThreePlug threePlug = new ComputerThreePlug();
        twoPlug.twoPlugDisPlay();
        threePlug.threePlugDisPlay();

        //两孔插头 -> 适配器 -> 三孔插头。目标对象是两孔插头，被适配者是三孔插头
        //客户 看着是在用两孔插头，但是适配给了三孔插头来操作
        TwoPlug needTwoPlug = new ThreePlugAdapter(threePlug);
        needTwoPlug.twoPlugDisPlay();




        //适配器模式：将一个类的接口，转换成客户期望的另一个接口，适配器可以让原本不兼容的类可以合作无间

        /**
         *  外观模式：提供了一个统一的接口，用来访问子系统中的一群接口。外观定义了一个高层简化接口，让子系统更容易使用。
         *  简化接口，隐藏复杂。定义一个接口，调用其他子系统复杂的接口，完成一个功能。
         */


    }
}
